package invent.net

class GeneroController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 15, 100)
        [generoInstanceList: Genero.list(params), generoInstanceTotal: Genero.count()]
    }

    def create = {
        def generoInstance = new Genero()
        generoInstance.properties = params
        return [generoInstance: generoInstance]
    }

    def save = {
        def generoInstance = new Genero(params)
        if (generoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'genero.label', default: 'Genero'), generoInstance.id])}"
            redirect(action: "show", id: generoInstance.id)
        }
        else {
            render(view: "create", model: [generoInstance: generoInstance])
        }
    }

    def show = {
        def generoInstance = Genero.get(params.id)
        if (!generoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'genero.label', default: 'Genero'), params.id])}"
            redirect(action: "list")
        }
        else {
            [generoInstance: generoInstance]
        }
    }

    def edit = {
        def generoInstance = Genero.get(params.id)
        if (!generoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'genero.label', default: 'Genero'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [generoInstance: generoInstance]
        }
    }

    def update = {
        def generoInstance = Genero.get(params.id)
        if (generoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (generoInstance.version > version) {
                    
                    generoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'genero.label', default: 'Genero')] as Object[], "Another user has updated this Genero while you were editing")
                    render(view: "edit", model: [generoInstance: generoInstance])
                    return
                }
            }
            generoInstance.properties = params
            if (!generoInstance.hasErrors() && generoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'genero.label', default: 'Genero'), generoInstance.id])}"
                redirect(action: "show", id: generoInstance.id)
            }
            else {
                render(view: "edit", model: [generoInstance: generoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'genero.label', default: 'Genero'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def generoInstance = Genero.get(params.id)
        if (generoInstance) {
            try {
                generoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'genero.label', default: 'Genero'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'genero.label', default: 'Genero'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'genero.label', default: 'Genero'), params.id])}"
            redirect(action: "list")
        }
    }
}
