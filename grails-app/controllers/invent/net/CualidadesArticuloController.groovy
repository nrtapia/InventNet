package invent.net

class CualidadesArticuloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 15, 100)
        [cualidadesArticuloInstanceList: CualidadesArticulo.list(params), cualidadesArticuloInstanceTotal: CualidadesArticulo.count()]
    }

    def create = {
        def cualidadesArticuloInstance = new CualidadesArticulo()
        cualidadesArticuloInstance.properties = params
        return [cualidadesArticuloInstance: cualidadesArticuloInstance]
    }

    def save = {
        def cualidadesArticuloInstance = new CualidadesArticulo(params)
        if (cualidadesArticuloInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), cualidadesArticuloInstance.id])}"
            redirect(action: "show", id: cualidadesArticuloInstance.id)
        }
        else {
            render(view: "create", model: [cualidadesArticuloInstance: cualidadesArticuloInstance])
        }
    }

    def show = {
        def cualidadesArticuloInstance = CualidadesArticulo.get(params.id)
        if (!cualidadesArticuloInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), params.id])}"
            redirect(action: "list")
        }
        else {
            [cualidadesArticuloInstance: cualidadesArticuloInstance]
        }
    }

    def edit = {
        def cualidadesArticuloInstance = CualidadesArticulo.get(params.id)
        if (!cualidadesArticuloInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [cualidadesArticuloInstance: cualidadesArticuloInstance]
        }
    }

    def update = {
        def cualidadesArticuloInstance = CualidadesArticulo.get(params.id)
        if (cualidadesArticuloInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cualidadesArticuloInstance.version > version) {
                    
                    cualidadesArticuloInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo')] as Object[], "Another user has updated this CualidadesArticulo while you were editing")
                    render(view: "edit", model: [cualidadesArticuloInstance: cualidadesArticuloInstance])
                    return
                }
            }
            cualidadesArticuloInstance.properties = params
            if (!cualidadesArticuloInstance.hasErrors() && cualidadesArticuloInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), cualidadesArticuloInstance.id])}"
                redirect(action: "show", id: cualidadesArticuloInstance.id)
            }
            else {
                render(view: "edit", model: [cualidadesArticuloInstance: cualidadesArticuloInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def cualidadesArticuloInstance = CualidadesArticulo.get(params.id)
        if (cualidadesArticuloInstance) {
            try {
                cualidadesArticuloInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo'), params.id])}"
            redirect(action: "list")
        }
    }
}
