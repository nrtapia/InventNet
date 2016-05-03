package com.invent.services

import invent.net.Articulo
import invent.net.ArticulosxDocumento
import invent.net.Documento

class DocumentService {

    static transactional = true

    def movimientosService

    def saveDocument( Documento doc, artarr, cantarr ) {

        (0..artarr.size()-1).each{ i ->

            println(">>>> i: ${i}")

            if( artarr[i].trim() != '' &&  cantarr[i].trim() != '' && (cantarr[i] as Integer) > 0  ){
                   def art = Articulo.get( artarr[i] as Long )
                   def artxdoc = new ArticulosxDocumento( articulo: art, cantidad: cantarr[i] as Integer  )
                   println("--ArtxDoc: ${artxdoc}")
                   doc.addToArticulosxDocumentos( artxdoc )
            }
        }
        println("---- Verificando cantidad ${doc.articulosxDocumentos}")
        if( doc.articulosxDocumentos.size() > 0 ){
            doc.save()
            return doc.errors
        }else{
            return null
        }

    }

    def deleteDocument( Documento document ){
        document.articulosxDocumentos.each {
              movimientosService.adjust( it.id_inv )
        }

        document.delete(flush: true)
    }

}
