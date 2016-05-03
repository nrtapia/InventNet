package invent.net

class CiudadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ciudadInstanceList: Ciudad.list(params), ciudadInstanceTotal: Ciudad.count()]
    }

    def create = {
        def ciudadInstance = new Ciudad()
        ciudadInstance.properties = params
        return [ciudadInstance: ciudadInstance]
    }

    def save = {
        def ciudadInstance = new Ciudad(params)
        if (ciudadInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudadInstance.id])}"
            redirect(action: "show", id: ciudadInstance.id)
        }
        else {
            render(view: "create", model: [ciudadInstance: ciudadInstance])
        }
    }

    def show = {
        def ciudadInstance = Ciudad.get(params.id)
        if (!ciudadInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])}"
            redirect(action: "list")
        }
        else {
            [ciudadInstance: ciudadInstance]
        }
    }

    def edit = {
        def ciudadInstance = Ciudad.get(params.id)
        if (!ciudadInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [ciudadInstance: ciudadInstance]
        }
    }

    def update = {
        def ciudadInstance = Ciudad.get(params.id)
        if (ciudadInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (ciudadInstance.version > version) {
                    
                    ciudadInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ciudad.label', default: 'Ciudad')] as Object[], "Another user has updated this Ciudad while you were editing")
                    render(view: "edit", model: [ciudadInstance: ciudadInstance])
                    return
                }
            }
            ciudadInstance.properties = params
            if (!ciudadInstance.hasErrors() && ciudadInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudadInstance.id])}"
                redirect(action: "show", id: ciudadInstance.id)
            }
            else {
                render(view: "edit", model: [ciudadInstance: ciudadInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def ciudadInstance = Ciudad.get(params.id)
        if (ciudadInstance) {
            try {
                ciudadInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])}"
            redirect(action: "list")
        }
    }
}
