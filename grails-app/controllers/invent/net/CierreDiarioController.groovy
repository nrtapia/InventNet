package invent.net

import org.springframework.dao.DataIntegrityViolationException
import com.invent.services.*

class CierreDiarioController {
    
    def commonService
    def springSecurityService
    def cierreService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        def listbdg = commonService.listWarehouses(1)
        
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        
        def criteria = CierreDiario.createCriteria() 
        def listcierre = criteria.list{   
            inList 'bodega', commonService.listWarehouses(1)  
            maxResults(params.max)
            join 'bodega'
            order 'fecha', 'desc'
        }
        
        [cierreDiarioInstanceList: listcierre, cierreDiarioInstanceTotal: CierreDiario.count()]
    }

    def create() {
        def lstbdg = commonService.listWarehouses(1)
        [cierreDiarioInstance: new CierreDiario(params), lstbdg:lstbdg]
    }

    def save() {
        def cierreDiarioInstance = new CierreDiario(params)
        cierreDiarioInstance.control  = new Date()
        cierreDiarioInstance.fecha = commonService.transformDate(params.fechaid)
        cierreDiarioInstance.usuario = springSecurityService.principal.username 
        
        try{
            cierreService.diario( params.bodega.id, 
                    cierreDiarioInstance.fecha, 
                    cierreDiarioInstance.usuario )
        }catch(e){
            log.error "Exception message $e.message,  has throw $e.cause "
            cierreDiarioInstance.errors.reject( e.message )
            def lstbdg = commonService.listWarehouses(1)
            render(view: "create", model: [cierreDiarioInstance: cierreDiarioInstance, lstbdg:lstbdg])
            return
        } 

	flash.message = "Cierre para la fecha $params.fechaid, realizado con exito"
        redirect(action: "list")
    }

    def show() {
        def cierreDiarioInstance = CierreDiario.get(params.id)
        if (!cierreDiarioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cierreDiario.label', default: 'CierreDiario'), params.id])
            redirect(action: "list")
            return
        }

        [cierreDiarioInstance: cierreDiarioInstance]
    }

    def edit() {
        def cierreDiarioInstance = CierreDiario.get(params.id)
        if (!cierreDiarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cierreDiario.label', default: 'CierreDiario'), params.id])
            redirect(action: "list")
            return
        }
        
        def lstbdg = commonService.listWarehouses(1)
        [cierreDiarioInstance: cierreDiarioInstance, lstbdg:lstbdg]
    }

    def update() {
        def cierreDiarioInstance = CierreDiario.get(params.id)
        if (!cierreDiarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cierreDiario.label', default: 'CierreDiario'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (cierreDiarioInstance.version > version) {
                cierreDiarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cierreDiario.label', default: 'CierreDiario')] as Object[],
                          "Another user has updated this CierreDiario while you were editing")
                render(view: "edit", model: [cierreDiarioInstance: cierreDiarioInstance])
                return
            }
        }

        cierreDiarioInstance.properties = params

        if (!cierreDiarioInstance.save(flush: true)) {
            render(view: "edit", model: [cierreDiarioInstance: cierreDiarioInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'cierreDiario.label', default: 'CierreDiario'), cierreDiarioInstance.id])
        redirect(action: "show", id: cierreDiarioInstance.id)
    }

    def delete() {
        def cierreDiarioInstance = CierreDiario.get(params.id)
        if (!cierreDiarioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cierreDiario.label', default: 'CierreDiario'), params.id])
            redirect(action: "list")
            return
        }

        try {
            cierreDiarioInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'cierreDiario.label', default: 'CierreDiario'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cierreDiario.label', default: 'CierreDiario'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
