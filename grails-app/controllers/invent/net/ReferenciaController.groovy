package invent.net

class ReferenciaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [referenciaInstanceList: Referencia.list(params), referenciaInstanceTotal: Referencia.count()]
    }

    def create = {
        def referenciaInstance = new Referencia()
        referenciaInstance.properties = params
        return [referenciaInstance: referenciaInstance]
    }

    def save = {
        def referenciaInstance = new Referencia(params)
        if (referenciaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'referencia.label', default: 'Referencia'), referenciaInstance.id])}"
            
            //redirect(action: "show", id: referenciaInstance.id)            
            redirect(controller:"proveedor", action: "show", id: referenciaInstance.proveedor.id)            
        }
        else {
            render(view: "create", model: [referenciaInstance: referenciaInstance])
        }
    }

    def show = {
        def referenciaInstance = Referencia.get(params.id)
        if (!referenciaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), params.id])}"
            redirect(action: "list")
        }
        else {
            [referenciaInstance: referenciaInstance]
        }
    }

    def edit = {
        def referenciaInstance = Referencia.get(params.id)
        if (!referenciaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [referenciaInstance: referenciaInstance]
        }
    }

    def update = {
        def referenciaInstance = Referencia.get(params.id)
        if (referenciaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (referenciaInstance.version > version) {
                    
                    referenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'referencia.label', default: 'Referencia')] as Object[], "Another user has updated this Referencia while you were editing")
                    render(view: "edit", model: [referenciaInstance: referenciaInstance])
                    return
                }
            }
            referenciaInstance.properties = params
            if (!referenciaInstance.hasErrors() && referenciaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'referencia.label', default: 'Referencia'), referenciaInstance.id])}"
                redirect(action: "show", id: referenciaInstance.id)
            }
            else {
                render(view: "edit", model: [referenciaInstance: referenciaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def referenciaInstance = Referencia.get(params.id)
        if (referenciaInstance) {
            try {
                referenciaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'referencia.label', default: 'Referencia'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'referencia.label', default: 'Referencia'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'referencia.label', default: 'Referencia'), params.id])}"
            redirect(action: "list")
        }
    }
}
