package invent.net

class TipoInperfectoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tipoInperfectoInstanceList: TipoInperfecto.list(params), tipoInperfectoInstanceTotal: TipoInperfecto.count()]
    }

    def create = {
        def tipoInperfectoInstance = new TipoInperfecto()
        tipoInperfectoInstance.properties = params
        return [tipoInperfectoInstance: tipoInperfectoInstance]
    }

    def save = {
        def tipoInperfectoInstance = new TipoInperfecto(params)
        if (tipoInperfectoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), tipoInperfectoInstance.id])}"
            redirect(action: "show", id: tipoInperfectoInstance.id)
        }
        else {
            render(view: "create", model: [tipoInperfectoInstance: tipoInperfectoInstance])
        }
    }

    def show = {
        def tipoInperfectoInstance = TipoInperfecto.get(params.id)
        if (!tipoInperfectoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tipoInperfectoInstance: tipoInperfectoInstance]
        }
    }

    def edit = {
        def tipoInperfectoInstance = TipoInperfecto.get(params.id)
        if (!tipoInperfectoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tipoInperfectoInstance: tipoInperfectoInstance]
        }
    }

    def update = {
        def tipoInperfectoInstance = TipoInperfecto.get(params.id)
        if (tipoInperfectoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoInperfectoInstance.version > version) {
                    
                    tipoInperfectoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto')] as Object[], "Another user has updated this TipoInperfecto while you were editing")
                    render(view: "edit", model: [tipoInperfectoInstance: tipoInperfectoInstance])
                    return
                }
            }
            tipoInperfectoInstance.properties = params
            if (!tipoInperfectoInstance.hasErrors() && tipoInperfectoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), tipoInperfectoInstance.id])}"
                redirect(action: "show", id: tipoInperfectoInstance.id)
            }
            else {
                render(view: "edit", model: [tipoInperfectoInstance: tipoInperfectoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tipoInperfectoInstance = TipoInperfecto.get(params.id)
        if (tipoInperfectoInstance) {
            try {
                tipoInperfectoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoInperfecto.label', default: 'TipoInperfecto'), params.id])}"
            redirect(action: "list")
        }
    }
}
