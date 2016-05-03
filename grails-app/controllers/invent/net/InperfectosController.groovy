package invent.net

import com.invent.services.*;

class InperfectosController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def catalogoService
    def commonService
    def movimientosService

    def index = {
        redirect(action: "list", params: params)
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
        
        def criteria = Inperfectos.createCriteria() 
        //def listimp = criteria.list{  
        def listimp = criteria.list( max:params.max, offset:params.offset ){   
            inList 'bodega', commonService.listWarehouses(1)             
            //maxResults(params.max)
            join 'articulo'
            join 'proveedor'
            join 'bodega'
            order 'id', 'desc'
        }
        
        def ccant = Inperfectos.createCriteria() 
        def listcnt = ccant.list{   
            inList 'bodega', commonService.listWarehouses(1)             
            join 'articulo'
            join 'proveedor'
            join 'bodega'
        }
       
        //[inperfectosInstanceList: listimp, inperfectosInstanceTotal: Inperfectos.count()]
        [inperfectosInstanceList: listimp, inperfectosInstanceTotal: listcnt.size()]
    }

    def create = {
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
        
        def inperfectosInstance = new Inperfectos()
        inperfectosInstance.properties = params
        return [inperfectosInstance: inperfectosInstance, listPrv:listprv, listBdg:listbdg]
    }

    def save = {
        log.info("-- Creando nuevo inperfecto: ${params}")
        
        def inperfectosInstance = new Inperfectos(params)
        
        if( params.fechaid != '' && params.bodega && params.proveedor && params.articulo ){        
            
            inperfectosInstance.fecha  = commonService.transformDate(params.fechaid)   
            
            try{
                if ( movimientosService.registerImperfect( inperfectosInstance ) ) {
                    flash.message = "${message(code: 'default.created.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), inperfectosInstance.id])}"
                    redirect(action: "show", id: inperfectosInstance.id)
                }
                else {
                    def listprv = commonService.listSuppliers()
                    def listbdg = commonService.listWarehouses(1)
                    render(view: "create", model: [inperfectosInstance: inperfectosInstance, listPrv:listprv, listBdg:listbdg])
                }
            }catch(RuntimeException e){
                log.error "Exception message $e.message, $e"
                def listprv = commonService.listSuppliers()
                def listbdg = commonService.listWarehouses(1)
                inperfectosInstance.errors.reject(e.message)
                render(view: "create", model: [inperfectosInstance: inperfectosInstance, listPrv:listprv, listBdg:listbdg])
            }     
            
        }else{         
            inperfectosInstance.errors.reject( "Por favor, seleccione la sucursal, proveedor y articulo")
            def listprv = commonService.listSuppliers()
            def listbdg = commonService.listWarehouses(1)
            render(view: "create", model: [inperfectosInstance: inperfectosInstance, listPrv:listprv, listBdg:listbdg])                        
        }
        
       
    }

    def show = {
        def inperfectosInstance = Inperfectos.get(params.id)
        if (!inperfectosInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), params.id])}"
            redirect(action: "list")
        }
        else {
            [inperfectosInstance: inperfectosInstance]
        }
    }

    def edit = {        
        flash.message = "No se permite la modificacion"
        redirect(action: "list")
        /*
        def inperfectosInstance = Inperfectos.get(params.id)
        if (!inperfectosInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [inperfectosInstance: inperfectosInstance]
        }
        */
    }

    def update = {         
        flash.message = "No se permite la modificacion"
        redirect(action: "list")
        /*
        def inperfectosInstance = Inperfectos.get(params.id)
        if (inperfectosInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (inperfectosInstance.version > version) {
                    
                    inperfectosInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'inperfectos.label', default: 'Inperfectos')] as Object[], "Another user has updated this Inperfectos while you were editing")
                    render(view: "edit", model: [inperfectosInstance: inperfectosInstance])
                    return
                }
            }
            inperfectosInstance.properties = params
            if (!inperfectosInstance.hasErrors() && inperfectosInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), inperfectosInstance.id])}"
                redirect(action: "show", id: inperfectosInstance.id)
            }
            else {
                render(view: "edit", model: [inperfectosInstance: inperfectosInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), params.id])}"
            redirect(action: "list")
        }
        */
    }

    def delete = {
        flash.message = "No se permite el borrado"
        redirect(action: "list")
        /*
        def inperfectosInstance = Inperfectos.get(params.id)
        if (inperfectosInstance) {
            try {
                inperfectosInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inperfectos.label', default: 'Inperfectos'), params.id])}"
            redirect(action: "list")
        }
        */
    }
}
