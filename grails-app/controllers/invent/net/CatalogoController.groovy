package invent.net

import grails.converters.*;
import com.invent.services.*;

class CatalogoController {
        
    def catalogoService
    def commonService
    
    def index = { 
        redirect(action: "list", params: params)
    }
    
    def list = {        
        
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
        
        [articuloInstanceTotal:0, listPrv:listprv, listBdg:listbdg]
    }
    
    
    def findByProvBdg = {        
        log.info("-- Buscando articulos fuera del catalogo idprv: ${params.proveedorid} idbdg: ${params.bodegaid}")
        def model  = null
                        
        if( params.proveedorid && params.bodegaid ){            
            
            def result = Articulo.findAll("from Articulo a " +            
                "inner join a.tipoArticulo as tipoArticulo  " +
                "inner join a.claseArticulo as claseArticulo  " +
                "inner join a.genero as genero  " +
                "inner join a.cualidadesArticulo as cualidadesArticulo  " +
                "where a not in ( " + 
                "select c.articulo from Catalogo c where c.proveedor.id = ? and c.bodega.id = ? ) "  +
                "order by a.tipoArticulo.detalle, a.genero.detalle, a.claseArticulo.detalle, a.cualidadesArticulo.detalle, a.referencia, a.descripcion ",
                [params.proveedorid as Long, params.bodegaid as Long]                
            ) 
                                                 
            if( result.size > 0 ){
                //result*.discard()
                model = [articuloInstanceList:result]
                log.info("--> Total registros fuera del catalogo: ${result.size}")
            }
        }
       
        if (request.xhr) {
          render( template:'artByPrv', model:model )          
        }else {
           model
        }                        
    }
    
    
    def findByProvBdgCatalog = {        
        log.info("-- Buscando catalogo idprv: ${params.proveedorid} idbdg: ${params.bodegaid}")
      
        def model2 = null                
        if( params.proveedorid && params.bodegaid ){
            
            def resultCatalog = catalogoService.listCatalogo( params.bodegaid, params.proveedorid )
            if( resultCatalog.size > 0 ){
                resultCatalog*.discard()
                model2 = [catalogoList:resultCatalog]                
            }               
        }
       
        if (request.xhr) {          
          render( template:'artByPrvData', model:model2 )
        }else {
           model2
        }                
        
    }
    
    
   
    def removeAll = {
        log.info "--> Removiendo todos los articulos del proveedor  ${params}"
        catalogoService.deleteAllBySuplier( params.bodegaid as Long, params.proveedorid as Long )
                
        response.contentType = "text/javascript" 
        render "alert('Los articulos fueron removidos del catalogo.')" 
    }
    
    def addAll = {
        log.info "--> Agregando todos los articulos del proveedor ${params}"
        
        catalogoService.addAllBySuplier( params.bodegaid as Long, params.proveedorid as Long )
                
        response.contentType = "text/javascript" 
        render "alert('Los articulos fueron agregados al catalogo.')" 
    }
    
    def deleteCatalogo = {
        log.info "--> Borrando articulo del catalogo ${params}"
        
        try{
            catalogoService.deleteCatalogo( params.catalogoid )
            render "success" 
            
        }catch(RuntimeException e){
            log.error "Exception message $e.message, $e"
            response.contentType = "text/javascript" 
            render "alert('No se pudo borrar del catalogo, causa: $e.message')"
        } 
        
        //response.contentType = "text/javascript" 
        //render "alert('Articulo borrado del catalogo.')" 
        
    }
    
    def saveCatalogo = {
        log.info "--> Agregando articulo al catalogo ${params}"
                
        catalogoService.addAsset( params.bodegaid, params.proveedorid, params.articuloid )        
        render "success" 
    }
    
    def updateCatalogo = { 
        log.info "--> Actualizando el catalogo ${params}"
        response.contentType = "text/javascript" 
        
        def arrayid = []
        arrayid.addAll( params.idvalues )
        def arrayval = []
        arrayval.addAll( params.values )

        def arraymin = []
        arraymin.addAll( params.stockmin )

        if( params.idvalues.size() > 0 ){        
            catalogoService.updateAllBySuplier( arrayid, arrayval, arraymin )
            render "alert('El catalogo fue actualizado.')" 
        }else{
            render "alert('No hay datos en el catalogo para acualizar.')" 
        }
        
    }
}
