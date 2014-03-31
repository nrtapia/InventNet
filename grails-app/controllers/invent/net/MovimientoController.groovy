package invent.net

class MovimientoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [movimientoInstanceList: Movimiento.list(params), movimientoInstanceTotal: Movimiento.count()]
    }

    def create = {
        def movimientoInstance = new Movimiento()
        movimientoInstance.properties = params
        return [movimientoInstance: movimientoInstance]
    }

    def save = {
        def movimientoInstance = new Movimiento(params)
        if (movimientoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), movimientoInstance.id])}"
            redirect(action: "show", id: movimientoInstance.id)
        }
        else {
            render(view: "create", model: [movimientoInstance: movimientoInstance])
        }
    }

    def show = {
        def movimientoInstance = Movimiento.get(params.id)
        if (!movimientoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), params.id])}"
            redirect(action: "list")
        }
        else {
            [movimientoInstance: movimientoInstance]
        }
    }

    def edit = {
        def movimientoInstance = Movimiento.get(params.id)
        if (!movimientoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [movimientoInstance: movimientoInstance]
        }
    }

    def update = {
        def movimientoInstance = Movimiento.get(params.id)
        if (movimientoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (movimientoInstance.version > version) {
                    
                    movimientoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'movimiento.label', default: 'Movimiento')] as Object[], "Another user has updated this Movimiento while you were editing")
                    render(view: "edit", model: [movimientoInstance: movimientoInstance])
                    return
                }
            }
            movimientoInstance.properties = params
            if (!movimientoInstance.hasErrors() && movimientoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), movimientoInstance.id])}"
                redirect(action: "show", id: movimientoInstance.id)
            }
            else {
                render(view: "edit", model: [movimientoInstance: movimientoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def movimientoInstance = Movimiento.get(params.id)
        if (movimientoInstance) {
            try {
                movimientoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'movimiento.label', default: 'Movimiento'), params.id])}"
            redirect(action: "list")
        }
    }
}
