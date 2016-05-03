package invent.net

import com.invent.services.*;

class EntradaController {
    
    def commonService
    def catalogoService
    def movimientosService

    def index = { 
        redirect(action: "list", params: params)
    }
    
    def list = {        
        
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
        def listmvn = commonService.listInputType() 
        
        [articuloInstanceTotal:0, listPrv:listprv, listBdg:listbdg, listMov:listmvn, movement: new Movimiento()]
    }
    
    def listCatalogo = {
        log.info "--> Buscando el catalogo de articulos idprv: ${params.proveedorid} idbdg: ${params.bodegaid}"
        
        def result = catalogoService.listCatalogo( params.bodegaid, params.proveedorid )
        
        def model = [catalogoList:result, movement: Movimiento.read(params.mvmid) ]
        render( template:'articulos', model:model )  
    }
    
    def addInput = {
        log.info "--> Agregando entrada a los movimientos idprv: ${params.proveedorid} idbdg: ${params.bodegaid} fechaid: ${params.fechavalue} tipo: ${params.movetypeid}"
       
        def date = commonService.transformDate(params.fechavalue)        
        //def user = Usuario.read(1)            
        def user = 'ENTRADA'
     
        try{
            def cant = movimientosService.addInputMovement( params.bodegaid, params.proveedorid, params.movetypeid, date, params.idvalues, params.cant, user, 'INP', params.ref )      
            response.contentType = "text/javascript" 
            render "alert('${cant} articulos ingresados al inventario.')"
        
        }catch(RuntimeException e){
            log.error "Exception message $e.message, $e"
            response.contentType = "text/javascript" 
            render "alert('No se pudo realizar la entrada, causa: $e.message')"
        } 

                 
    }
}
