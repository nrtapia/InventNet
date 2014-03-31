package com.cys.inventnet.tags

class CustomTagLib {
    
    static namespace = "cys"
    
    /**
     * Renderiza un link para una foto
     *
     * @attr data REQUIRED objeto de la foto
     */
    def foto = { attrs, body ->
        out << "<a href=\"${createLink(controller:'foto', action:'show', id: attrs.data.id  )}\" "
        out << "rel=\"prettyPhoto[pp_gal${attrs.parent}]\" "
        out << "title=\"${attrs.data.name}\"> "
        out << attrs.data.name
        out << "</a>"      
    }
    
     /**
     * Renderiza un link para una foto
     *
     * @attr art REQUIRED objeto de la foto
     */
    def articuloFoto = { attrs, body ->

        def temp = null
        def cont = 0
        attrs.art?.fotos.each { it ->
       
            if( cont < attrs.art?.fotos.size()-1 ){
                out << "<a href=\"${createLink(controller:'foto', action:'show', id: it.id  )}\" "
                out << "rel=\"prettyPhoto[pp_gal${attrs.art.id}]\" "
                out << "style=\"display: none\" "
                out << "title=\"${it.name}\"> "
                out << it.name
                out << "</a>"
            }
            temp = it
            cont ++
        }
        
        if( temp ){
            out << "<a href=\"${createLink(controller:'foto', action:'show', id: temp.id  )}\" "
            out << "rel=\"prettyPhoto[pp_gal${temp.articulo.id}]\" "
            out << "title=\"${temp.name}\"> "
            out << "<img src=\"${resource(dir:'images', file:'camera.png') }\"/>"
            out << "</a>"
        }
        
    }

}
