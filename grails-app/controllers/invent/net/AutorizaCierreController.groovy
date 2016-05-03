package invent.net

class AutorizaCierreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def springSecurityService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [autorizaCierreInstanceList: AutorizaCierre.list(params), autorizaCierreInstanceTotal: AutorizaCierre.count()]
    }

    def create = {
        def autorizaCierreInstance = new AutorizaCierre()
        autorizaCierreInstance.properties = params
        return [autorizaCierreInstance: autorizaCierreInstance]
    }

    def save = {
        def autorizaCierreInstance = new AutorizaCierre(params)
        autorizaCierreInstance.usuario = springSecurityService.principal.username 
        
        if (autorizaCierreInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'autorizaCierre.label', default: 'AutorizaCierre'), autorizaCierreInstance.id])}"
            redirect(action: "show", id: autorizaCierreInstance.id)
        }
        else {
            render(view: "create", model: [autorizaCierreInstance: autorizaCierreInstance])
        }
    }

    def show = {
        def autorizaCierreInstance = AutorizaCierre.get(params.id)
        if (!autorizaCierreInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'autorizaCierre.label', default: 'AutorizaCierre'), params.id])}"
            redirect(action: "list")
        }
        else {
            [autorizaCierreInstance: autorizaCierreInstance]
        }
    }

    

    def delete = {
        def autorizaCierreInstance = AutorizaCierre.get(params.id)
        if( autorizaCierreInstance.estado != 'Abierto' ){
            flash.message = "La autorización no puede ser borrada!"
            redirect(action: "show", id: params.id)
        }else{

            if (autorizaCierreInstance) {
                try {
                    autorizaCierreInstance.delete(flush: true)
                    flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'autorizaCierre.label', default: 'AutorizaCierre'), params.id])}"
                    redirect(action: "list")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'autorizaCierre.label', default: 'AutorizaCierre'), params.id])}"
                    redirect(action: "show", id: params.id)
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'autorizaCierre.label', default: 'AutorizaCierre'), params.id])}"
                redirect(action: "list")
            }
        }
    }
}
