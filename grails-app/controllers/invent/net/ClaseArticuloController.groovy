package invent.net

class ClaseArticuloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 15, 100)
        [claseArticuloInstanceList: ClaseArticulo.list(params), claseArticuloInstanceTotal: ClaseArticulo.count()]
    }

    def create = {
        def claseArticuloInstance = new ClaseArticulo()
        claseArticuloInstance.properties = params
        return [claseArticuloInstance: claseArticuloInstance]
    }

    def save = {
        def claseArticuloInstance = new ClaseArticulo(params)
        if (claseArticuloInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), claseArticuloInstance.id])}"
            redirect(action: "show", id: claseArticuloInstance.id)
        }
        else {
            render(view: "create", model: [claseArticuloInstance: claseArticuloInstance])
        }
    }

    def show = {
        def claseArticuloInstance = ClaseArticulo.get(params.id)
        if (!claseArticuloInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), params.id])}"
            redirect(action: "list")
        }
        else {
            [claseArticuloInstance: claseArticuloInstance]
        }
    }

    def edit = {
        def claseArticuloInstance = ClaseArticulo.get(params.id)
        if (!claseArticuloInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [claseArticuloInstance: claseArticuloInstance]
        }
    }

    def update = {
        def claseArticuloInstance = ClaseArticulo.get(params.id)
        if (claseArticuloInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (claseArticuloInstance.version > version) {
                    
                    claseArticuloInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'claseArticulo.label', default: 'ClaseArticulo')] as Object[], "Another user has updated this ClaseArticulo while you were editing")
                    render(view: "edit", model: [claseArticuloInstance: claseArticuloInstance])
                    return
                }
            }
            claseArticuloInstance.properties = params
            if (!claseArticuloInstance.hasErrors() && claseArticuloInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), claseArticuloInstance.id])}"
                redirect(action: "show", id: claseArticuloInstance.id)
            }
            else {
                render(view: "edit", model: [claseArticuloInstance: claseArticuloInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def claseArticuloInstance = ClaseArticulo.get(params.id)
        if (claseArticuloInstance) {
            try {
                claseArticuloInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'claseArticulo.label', default: 'ClaseArticulo'), params.id])}"
            redirect(action: "list")
        }
    }
}
