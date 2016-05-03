package invent.net

import grails.converters.*

class SubasController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def catalogoService
    def commonService 
    def movimientosService

    def index = {
        redirect(action: "list", params: params)
    }
    
    def fillData = {
        log.info "--> Buscando datos del articulo para la suba ${params}"
        def ctlg = Catalogo.read( params.id )        
        
        render(contentType:"text/json") {
            data {
                valor = ctlg.valor
                cantidad = ctlg.cantidad
                articulo = ctlg.articulo.id
            }	
	}
    }
    
    def fillArt = {        
        log.info("-- Buscando catalogo proveedor.id: ${params.proveedor.id} bodega.id: ${params.bodega.id}")
              
        def model = null                
        if( params.proveedor.id && params.bodega.id ){
            
            def resultCatalog = catalogoService.listCatalogo( params.bodega.id, params.proveedor.id )
            if( resultCatalog.size > 0 ){
                resultCatalog*.discard()
                model = [ artList: resultCatalog]                
            }               
        }
       
        if (request.xhr) {          
          render( template:'cmbArticulos', model:model )
        }else {
           model
        }          
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        
        def criteria = Subas.createCriteria() 
        def listsubas = criteria.list( max:params.max, offset:params.offset ){   
            inList 'bodega', commonService.listWarehouses(1)  
            //maxResults(params.max)
            join 'articulo'
            join 'proveedor'
            join 'bodega'
            order 'id', 'desc'
        }       
        
        def ccant = Subas.createCriteria() 
        def listcnt = ccant.list{   
            inList 'bodega', commonService.listWarehouses(1)  
            join 'articulo'
            join 'proveedor'
            join 'bodega'
            order 'id', 'desc'
        }
        
        [subasInstanceList: listsubas, subasInstanceTotal: listcnt.size()]
    }
    
    def cancel = {
        def subasInstance = Subas.get(params.id)
        if (!subasInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subas.label', default: 'Subas'), params.id])}"            
        }else{
            subasInstance.estado = 'Inactiva'
            if (subasInstance.save(flush: true)) {
                flash.message = "Suba cancelada con exito!"
            }else{
                flash.message = "La Suba no pudo ser cancelada!"
            }           
        }
        redirect(action: "list")
    }

    def create = {
        def subasInstance = new Subas()
        subasInstance.properties = params
        
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
        
        return [subasInstance: subasInstance, listPrv:listprv, listBdg:listbdg]
    }

    def save = {
        def subasInstance = new Subas(params)
        def msg = false
        
        def dvalor    = new BigDecimal(params.valor)
        def dvalorAnt = new BigDecimal(params.valorAnterior)
        
        /*
        if( (params.tipo == 'Suba' && params.valor < params.valorAnterior) ||
            (params.tipo == 'Rebaja' && params.valor > params.valorAnterior)    ){                        
            subasInstance.errors.reject( "${message(code: 'subas.valor.deffer', args: [message(code: 'subas.label', default: 'Subas'), subasInstance.id])}"     )       
            msg = true
        }*/
        
        if( (params.tipo == 'Suba' && dvalor < dvalorAnt) ||
            (params.tipo == 'Rebaja' && dvalor > dvalorAnt)    ){                        
            subasInstance.errors.reject( "${message(code: 'subas.valor.deffer', args: [message(code: 'subas.label', default: 'Subas'), subasInstance.id])}"     )       
            msg = true
        }
        
        if( params.fechaid == '' ){                                
            subasInstance.errors.reject( "${message(code: 'subas.fecha.empty', args: [message(code: 'subas.label', default: 'Subas'), subasInstance.id])}" )
            msg = true
        }       
        
        if( !msg ){
            
            try{
                subasInstance.fecha  = commonService.transformDate(params.fechaid)
                
                /*
                if (subasInstance.save()) {
                    flash.message = "${message(code: 'default.created.message', args: [message(code: 'subas.label', default: 'Subas'), subasInstance.id])}"
                    redirect(action: "show", id: subasInstance.id)
                }
                else {
                    def listprv = commonService.listSuppliers()
                    def listbdg = commonService.listWarehouses(1)
                    render(view: "create", model: [subasInstance: subasInstance, listPrv:listprv, listBdg:listbdg])
                }
                */
               
                // Se registra la suba
                subasInstance = movimientosService.registerSuba( subasInstance )
                
                if( subasInstance.hasErrors() ){
                    def listprv = commonService.listSuppliers()
                    def listbdg = commonService.listWarehouses(1)
                    render(view: "create", model: [subasInstance: subasInstance, listPrv:listprv, listBdg:listbdg])
                }else{
                    flash.message = "${message(code: 'default.created.message', args: [message(code: 'subas.label', default: 'Subas'), subasInstance.id])}"
                    redirect(action: "show", id: subasInstance.id)
                }
                
                
            }catch(RuntimeException e){
                log.error "Exception message $e.message, $e"
                subasInstance.errors.reject(e.message)
                def listprv = commonService.listSuppliers()
                def listbdg = commonService.listWarehouses(1)
                render(view: "create", model: [subasInstance: subasInstance, listPrv:listprv, listBdg:listbdg])
            } 
            
            
        }else{
            def listprv = commonService.listSuppliers()
            def listbdg = commonService.listWarehouses(1)            
            render(view: "create", model: [subasInstance: subasInstance, listPrv:listprv, listBdg:listbdg])
        }
        
    }

    def show = {
        def subasInstance = Subas.get(params.id)
        if (!subasInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subas.label', default: 'Subas'), params.id])}"
            redirect(action: "list")
        }
        else {
            [subasInstance: subasInstance]
        }
    }

    def edit = {
        flash.message = "No se permite la modificacion"
        redirect(action: "list")
        /*
        def subasInstance = Subas.get(params.id)
        if (!subasInstance) {
        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subas.label', default: 'Subas'), params.id])}"
        redirect(action: "list")
        }
        else {
        return [subasInstance: subasInstance]
        }
         */
    }

    def update = {
        flash.message = "No se permite la modificacion"
        redirect(action: "list")
        /*
        def subasInstance = Subas.get(params.id)
        if (subasInstance) {
        if (params.version) {
        def version = params.version.toLong()
        if (subasInstance.version > version) {
                    
        subasInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'subas.label', default: 'Subas')] as Object[], "Another user has updated this Subas while you were editing")
        render(view: "edit", model: [subasInstance: subasInstance])
        return
        }
        }
        subasInstance.properties = params
        if (!subasInstance.hasErrors() && subasInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'subas.label', default: 'Subas'), subasInstance.id])}"
        redirect(action: "show", id: subasInstance.id)
        }
        else {
        render(view: "edit", model: [subasInstance: subasInstance])
        }
        }
        else {
        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subas.label', default: 'Subas'), params.id])}"
        redirect(action: "list")
        }
         */
    }

    def delete = {
        flash.message = "No se permite la modificacion"
        redirect(action: "list")
        /*
        def subasInstance = Subas.get(params.id)
        if (subasInstance) {
        try {
        subasInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'subas.label', default: 'Subas'), params.id])}"
        redirect(action: "list")
        }
        catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'subas.label', default: 'Subas'), params.id])}"
        redirect(action: "show", id: params.id)
        }
        }
        else {
        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subas.label', default: 'Subas'), params.id])}"
        redirect(action: "list")
        }
         */
    }
}
