package invent.net

class InventarioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [inventarioInstanceList: Inventario.list(params), inventarioInstanceTotal: Inventario.count()]
    }

    def create = {
        def inventarioInstance = new Inventario()
        inventarioInstance.properties = params
        return [inventarioInstance: inventarioInstance]
    }

    def save = {
        def inventarioInstance = new Inventario(params)
        if (inventarioInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'inventario.label', default: 'Inventario'), inventarioInstance.id])}"
            redirect(action: "show", id: inventarioInstance.id)
        }
        else {
            render(view: "create", model: [inventarioInstance: inventarioInstance])
        }
    }

    def show = {
        def inventarioInstance = Inventario.get(params.id)
        if (!inventarioInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), params.id])}"
            redirect(action: "list")
        }
        else {
            [inventarioInstance: inventarioInstance]
        }
    }

    def edit = {
        def inventarioInstance = Inventario.get(params.id)
        if (!inventarioInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [inventarioInstance: inventarioInstance]
        }
    }

    def update = {
        def inventarioInstance = Inventario.get(params.id)
        if (inventarioInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (inventarioInstance.version > version) {
                    
                    inventarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'inventario.label', default: 'Inventario')] as Object[], "Another user has updated this Inventario while you were editing")
                    render(view: "edit", model: [inventarioInstance: inventarioInstance])
                    return
                }
            }
            inventarioInstance.properties = params
            if (!inventarioInstance.hasErrors() && inventarioInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'inventario.label', default: 'Inventario'), inventarioInstance.id])}"
                redirect(action: "show", id: inventarioInstance.id)
            }
            else {
                render(view: "edit", model: [inventarioInstance: inventarioInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def inventarioInstance = Inventario.get(params.id)
        if (inventarioInstance) {
            try {
                inventarioInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'inventario.label', default: 'Inventario'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'inventario.label', default: 'Inventario'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), params.id])}"
            redirect(action: "list")
        }
    }
}
