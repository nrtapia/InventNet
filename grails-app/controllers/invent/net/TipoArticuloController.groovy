package invent.net

class TipoArticuloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 15, 100)
        [tipoArticuloInstanceList: TipoArticulo.list(params), tipoArticuloInstanceTotal: TipoArticulo.count()]
    }

    def create = {
        def tipoArticuloInstance = new TipoArticulo()
        tipoArticuloInstance.properties = params
        return [tipoArticuloInstance: tipoArticuloInstance]
    }

    def save = {
        def tipoArticuloInstance = new TipoArticulo(params)
        if (tipoArticuloInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), tipoArticuloInstance.id])}"
            redirect(action: "show", id: tipoArticuloInstance.id)
        }
        else {
            render(view: "create", model: [tipoArticuloInstance: tipoArticuloInstance])
        }
    }

    def show = {
        def tipoArticuloInstance = TipoArticulo.get(params.id)
        if (!tipoArticuloInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tipoArticuloInstance: tipoArticuloInstance]
        }
    }

    def edit = {
        def tipoArticuloInstance = TipoArticulo.get(params.id)
        if (!tipoArticuloInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tipoArticuloInstance: tipoArticuloInstance]
        }
    }

    def update = {
        def tipoArticuloInstance = TipoArticulo.get(params.id)
        if (tipoArticuloInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoArticuloInstance.version > version) {
                    
                    tipoArticuloInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoArticulo.label', default: 'TipoArticulo')] as Object[], "Another user has updated this TipoArticulo while you were editing")
                    render(view: "edit", model: [tipoArticuloInstance: tipoArticuloInstance])
                    return
                }
            }
            tipoArticuloInstance.properties = params
            if (!tipoArticuloInstance.hasErrors() && tipoArticuloInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), tipoArticuloInstance.id])}"
                redirect(action: "show", id: tipoArticuloInstance.id)
            }
            else {
                render(view: "edit", model: [tipoArticuloInstance: tipoArticuloInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tipoArticuloInstance = TipoArticulo.get(params.id)
        if (tipoArticuloInstance) {
            try {
                tipoArticuloInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoArticulo.label', default: 'TipoArticulo'), params.id])}"
            redirect(action: "list")
        }
    }
}
