package invent.net

class DocumentoController {


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def movimientosService
    def commonService
    def springSecurityService
    def documentService

    def filterPaneService

    def filter = {
        //if(!params.max)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        params.put("filter.op.bodega.id", "ILike" )

        def ids = ""
        commonService.listWarehouses(1).each {
            ids = ids + it.id + ", "
        }

        params.put("filter.bodega.id", ids )

        log.info("--->  params: ${params}")


        render( view:'list',
                model:[ documentoInstanceList: filterPaneService.filter( params, Documento ),
                        documentoInstanceTotal: filterPaneService.count( params, Documento ),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params:params ] )
    }


    def index = {
        redirect(action: "list", params: params)
    }
    
    def search = {
        log.info "-- Buscando datos ${params}"
                
        def criteria = Catalogo.createCriteria()
        def result = criteria.get {
            eq( 'bodega.id', params.bodegaid as Long) 
            eq( 'proveedor.id', params.proveedorid  as Long )                        
            articulo{
                eq( 'codigo', params.articulocode)            
            }
            join 'articulo'
        }
        
        render(contentType:"text/json") {
            data {
                if( result != null ){
                    valor = result.valor
                    stock = result.cantidad
                    ctlid = result.id                    
                    artid = result.articulo.id
                    articulo = result.articulo.toString()
                    proveedor = result.proveedor.toString()
                }else{
                    error = "No se encuentra catalogo definido para el articulo."
                }
            }	
	}
        
    }
    

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        //[documentoInstanceList: Documento.list(params), documentoInstanceTotal: Documento.count()]

        def criteria = Documento.createCriteria()
        def listdoc = criteria.list( max:params.max, offset:params.offset ){
            inList 'bodega', commonService.listWarehouses(1)
            join 'bodega'
            order 'fecha', 'desc'
        }


        def criteriasize = Documento.createCriteria()
        def listsize = criteriasize.list( max:params.max, offset:params.offset ){
            inList 'bodega', commonService.listWarehouses(1)
        }

        [documentoInstanceList: listdoc, documentoInstanceTotal: listsize.size()]

    }

    def create = {
        def documentoInstance = new Documento()
        documentoInstance.properties = params
        return [documentoInstance: documentoInstance]
    }

    def save = {

        // Nombre del usuario en sesion
        def user = springSecurityService.principal.username

        def documentoInstance = new Documento(params)
        documentoInstance.fecha = commonService.transformDate(params.fechaui)
        documentoInstance.control = new Date()
        documentoInstance.usuario = user

        def arrayid = []
        arrayid.addAll( params.artid )
        def arraycant = []
        arraycant.addAll( params.cant )

        (0..arrayid.size()-1).each{ i ->

            println(">>>> i: ${i}")

            if( arrayid[i].trim() != '' &&  arrayid[i].trim() != '' && (arraycant[i] as Integer) > 0  ){
                def art = Articulo.get( arrayid[i] as Long )
                def artxdoc = new ArticulosxDocumento( articulo: art, cantidad: arraycant[i] as Integer  )
                println("--ArtxDoc: ${artxdoc}")
                documentoInstance.addToArticulosxDocumentos( artxdoc )
            }
        }

        if (documentoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])}"
            redirect(action: "show", id: documentoInstance.id)
        }
        else {
            render(view: "create", model: [documentoInstance: documentoInstance])
        }

    }

    def show = {
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        }
        else {
            [documentoInstance: documentoInstance]
        }
    }

    def edit = {
        def documentoInstance = Documento.get(params.id)
        if (!documentoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [documentoInstance: documentoInstance]
        }
    }

    def update = {
        def documentoInstance = Documento.get(params.id)
        if (documentoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (documentoInstance.version > version) {
                    
                    documentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'documento.label', default: 'Documento')] as Object[], "Another user has updated this Documento while you were editing")
                    render(view: "edit", model: [documentoInstance: documentoInstance])
                    return
                }
            }
            documentoInstance.properties = params
            if (!documentoInstance.hasErrors() && documentoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])}"
                redirect(action: "show", id: documentoInstance.id)
            }
            else {
                render(view: "edit", model: [documentoInstance: documentoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def documentoInstance = Documento.get(params.id)
        if (documentoInstance) {
            try {
                //documentoInstance.delete(flush: true)
                documentService.deleteDocument( documentoInstance )
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), params.id])}"
            redirect(action: "list")
        }
    }
}
