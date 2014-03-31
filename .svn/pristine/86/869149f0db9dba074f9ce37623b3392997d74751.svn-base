package invent.net

class FotoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def create = {
        def fotoInstance = new Foto()
        fotoInstance.properties = params
        return [fotoInstance: fotoInstance]
    }

    def save = {
        def fotoInstance = new Foto(params)
        fotoInstance.name = params.file.originalFilename
        fotoInstance.type = params.file.contentType
        fotoInstance.size = params.file.size
                
        if (fotoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'foto.label', default: 'Foto'), fotoInstance.id])}"
            //redirect(action: "show", id: fotoInstance.id)
            redirect( controller:"articulo", action: "edit", params: [id: fotoInstance.articulo.id])
        }
        else {
            render(view: "create", model: [fotoInstance: fotoInstance])            
        }
    }

    def show = {
        def fotoInstance = Foto.get(params.id)
        if (!fotoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'foto.label', default: 'Foto'), params.id])}"
            redirect(action: "list")
        }
        else {
           // [fotoInstance: fotoInstance]
           byte[] image = fotoInstance.file 
           response.setContentType(fotoInstance.type)
           response.setContentLength(fotoInstance.size)
           OutputStream out = response.getOutputStream();
           out.write(image);
           out.close();       
        }
    }


    def delete = {
        def fotoInstance = Foto.get(params.id)
        if (fotoInstance) {
            try {
                fotoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'foto.label', default: 'Foto'), params.id])}"
                //redirect(action: "list")
                redirect( controller:"articulo", action: "edit", params: [id: fotoInstance.articulo.id])
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'foto.label', default: 'Foto'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'foto.label', default: 'Foto'), params.id])}"
            redirect(action: "list")
        }
    }
}
