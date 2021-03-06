package invent.net

import com.invent.services.*

class DevolucionController {
    
    def catalogoService
    def commonService 
    def movimientosService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
    
    
    def fillData = {
        log.info "--> Buscando datos del articulo para la suba ${params}"
        def ctlg = Catalogo.read( params.id )        
        
        render(contentType:"text/json") {
            data {
                valor = ctlg.valor                
                articulo = ctlg.articulo.id
                costo = ctlg.costo
            }	
	}
    }
    

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset = ( params.offset ? params.offset : 0 )
        
        def criteria = Devolucion.createCriteria() 
        def listdev = criteria.list(max:params.max, offset:params.offset){   
            inList 'bodega', commonService.listWarehouses(1)  
            //maxResults(params.max)
            //offset: params.offset
            join 'articulo'
            join 'proveedor'
            join 'bodega'
            order 'id', 'desc'
            
        } 
        
        def criteriacant = Devolucion.createCriteria() 
        def listcant = criteriacant.list{   
            inList 'bodega', commonService.listWarehouses(1)  
            join 'articulo'
            join 'proveedor'
            join 'bodega'
            order 'id', 'desc'
        } 
        
        [devolucionInstanceList: listdev, devolucionInstanceTotal: listcant.size()]
    }

    def create = {
        def devolucionInstance = new Devolucion()
        devolucionInstance.properties = params
        
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
        
        return [devolucionInstance: devolucionInstance, listPrv:listprv, listBdg:listbdg]
    }

    def save = {
        def devolucionInstance = new Devolucion(params)
        devolucionInstance.fecha = commonService.transformDate(params.fechaid)        
        
        try{
            if ( movimientosService.refundToProvider(devolucionInstance, params.catalogoid) ) { 

                flash.message = "${message(code: 'default.created.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), devolucionInstance.id])}"
                redirect(action: "show", id: devolucionInstance.id)
            }
            else {
                def listprv = commonService.listSuppliers()
                def listbdg = commonService.listWarehouses(1)
                render(view: "create", model: [devolucionInstance: devolucionInstance, listPrv:listprv, listBdg:listbdg])
            }
        }catch(RuntimeException e){
            log.error "Exception message $e.message"
            
            devolucionInstance.errors.reject( e.message )
            def listprv = commonService.listSuppliers()
            def listbdg = commonService.listWarehouses(1)
            render(view: "create", model: [devolucionInstance: devolucionInstance, listPrv:listprv, listBdg:listbdg])
        } 
    }

    def show = {
        def devolucionInstance = Devolucion.get(params.id)
        if (!devolucionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), params.id])}"
            redirect(action: "list")
        }
        else {
            [devolucionInstance: devolucionInstance]
        }
    }

    def edit = {
        
        def devolucionInstance = Devolucion.get(params.id)
        if (!devolucionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [devolucionInstance: devolucionInstance]
        }
        
    }

    def update = {
        def devolucionInstance = Devolucion.get(params.id)        
        
        if( params.canting > params.cantidad ){
            devolucionInstance.errors.rejectValue("canting", "devolucion.canting.error", [message(code: 'devolucion.label', default: 'Devolucion')] as Object[], "La cantidad a ingresar no puede ser mayor a la cantida devuelta")
            render(view: "edit", model: [devolucionInstance: devolucionInstance])
            return
        }
        
        if( params.canting < params.cantidad && params.detalle == ''  ){
            devolucionInstance.errors.rejectValue("detalle", "devolucion.detalle.error", [message(code: 'devolucion.label', default: 'Devolucion')] as Object[], "Ingrese una observación por el faltante")
            render(view: "edit", model: [devolucionInstance: devolucionInstance])
            return
        }
        
        devolucionInstance.estado = 'Cumplida'
        
        if (devolucionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (devolucionInstance.version > version) {

                    devolucionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'devolucion.label', default: 'Devolucion')] as Object[], "Another user has updated this Devolucion while you were editing")
                    render(view: "edit", model: [devolucionInstance: devolucionInstance])
                    return
                }
            }
            devolucionInstance.properties = params
            if (!devolucionInstance.hasErrors() && devolucionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), devolucionInstance.id])}"
                redirect(action: "show", id: devolucionInstance.id)
            }
            else {
                render(view: "edit", model: [devolucionInstance: devolucionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), params.id])}"
            redirect(action: "list")
        }
                
    }

    def delete = {
        /*
        def devolucionInstance = Devolucion.get(params.id)
        if (devolucionInstance) {
            try {
                devolucionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'devolucion.label', default: 'Devolucion'), params.id])}"
            redirect(action: "list")
        }
        */
    }
}
