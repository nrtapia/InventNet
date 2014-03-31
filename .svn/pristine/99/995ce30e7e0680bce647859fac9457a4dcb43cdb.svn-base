package invent.net

class BodegaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 15, 100)
        params.fetch = [ciudad:'join']
        [bodegaInstanceList: Bodega.list(params), bodegaInstanceTotal: Bodega.count()]
    }

    def create = {
        def bodegaInstance = new Bodega()
        bodegaInstance.properties = params
        return [bodegaInstance: bodegaInstance]
    }

    def save = {
        def bodegaInstance = new Bodega(params)
        if (bodegaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'bodega.label', default: 'Bodega'), bodegaInstance.id])}"
            redirect(action: "show", id: bodegaInstance.id)
        }
        else {
            render(view: "create", model: [bodegaInstance: bodegaInstance])
        }
    }

    def show = {
        def bodegaInstance = Bodega.get(params.id)
        if (!bodegaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bodega.label', default: 'Bodega'), params.id])}"
            redirect(action: "list")
        }
        else {
            [bodegaInstance: bodegaInstance]
        }
    }

    def edit = {
        def bodegaInstance = Bodega.get(params.id)
        if (!bodegaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bodega.label', default: 'Bodega'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [bodegaInstance: bodegaInstance]
        }
    }

    def update = {
        def bodegaInstance = Bodega.get(params.id)
        if (bodegaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (bodegaInstance.version > version) {
                    
                    bodegaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'bodega.label', default: 'Bodega')] as Object[], "Another user has updated this Bodega while you were editing")
                    render(view: "edit", model: [bodegaInstance: bodegaInstance])
                    return
                }
            }
            bodegaInstance.properties = params
            if (!bodegaInstance.hasErrors() && bodegaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'bodega.label', default: 'Bodega'), bodegaInstance.id])}"
                redirect(action: "show", id: bodegaInstance.id)
            }
            else {
                render(view: "edit", model: [bodegaInstance: bodegaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bodega.label', default: 'Bodega'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def bodegaInstance = Bodega.get(params.id)
        if (bodegaInstance) {
            try {
                bodegaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'bodega.label', default: 'Bodega'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'bodega.label', default: 'Bodega'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'bodega.label', default: 'Bodega'), params.id])}"
            redirect(action: "list")
        }
    }
}
