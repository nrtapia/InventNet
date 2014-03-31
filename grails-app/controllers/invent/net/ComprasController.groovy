package invent.net

import com.invent.services.*;

class ComprasController {
    
    def commonService
    def catalogoService
    def movimientosService

    def index = { 
    
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
                
        [articuloInstanceTotal:0, listPrv:listprv, listBdg:listbdg]
        
    }
    
    def listCatalogo = {
        log.info "--> Buscando el catalogo de articulos idprv: ${params.proveedorid} idbdg: ${params.bodegaid}"
        
        def result = catalogoService.listCatalogo( params.bodegaid, params.proveedorid )
        def model = [catalogoList:result]
        render( template:'articulos', model:model )  
    }
    
    def addInput = {
        log.info "--> Agregando compras al inventario idprv: ${params.proveedorid} idbdg: ${params.bodegaid} fechaid: ${params.fechavalue}"
       
        def date = commonService.transformDate(params.fechavalue)        
        //def user = Usuario.read(1)            
        def user = 'COMPRA'
        
        try{
            def cant = movimientosService.addComprasMovement( params.bodegaid, params.proveedorid, date, params.idart, params.costo, params.valor, params.cant )
            response.contentType = "text/javascript" 
            render "alert('${cant} articulos ingresados al inventario.')"         
            
        }catch(RuntimeException e){
            log.error "Exception message $e.message, $e"
            response.contentType = "text/javascript" 
            render "alert('No se pudo realizar el movimiento de compra, causa: $e.message')"
        } 
    }
}
