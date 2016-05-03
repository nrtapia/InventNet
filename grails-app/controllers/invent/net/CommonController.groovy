package invent.net

import grails.converters.*

class CommonController {

    
    def searchArticulo() {  
        log.info "-- Busqueda de articulo $params.term"
        
        def list = Articulo.findAllByCodigoLike( params.term + "%", [cache:true] )
            
        def lstresp = []
        list.each{
            lstresp.add( [id:it.codigo.trim(), value:it.codigo.trim()] )
        }
        
        render lstresp as JSON   
    }
    
    def searchProveedor() {  
        log.info "-- Busqueda de proveedor $params.term"
        
        def list = null
        
        if( params.term.isNumber() ){
            list = Proveedor.findAllByCodigoLike( params.term + "%", [cache:true] )
        }else{
            list = Proveedor.findAllByRazonSocialLike( params.term.toUpperCase() + "%", [cache:true] )
        }
            
        def lstresp = []
        list.each{
            lstresp.add( [id:it.codigo.trim(), value: "${it.codigo.trim()} ${it.razonSocial.trim()}" ] )
        }
        
        render lstresp as JSON   
    }
}
