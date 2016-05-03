package invent.net

import com.invent.services.*;

class SalidaController {
    
    def commonService
    def catalogoService
    def movimientosService
    
    def index = {        
        redirect(action: "small", params: params)
    }

    
    def small = {        
        def listbdg = commonService.listWarehouses(1)
        def listmvn = commonService.listOutputType() 
        
        [articuloInstanceTotal:0, listBdg:listbdg, listMov:listmvn, movement:new Movimiento()]
    }
    
    def search = {
        log.info "-- Buscando datos ${params}"
                
        def criteria = Catalogo.createCriteria()
        def result = criteria.get {
            eq( 'bodega.id', params.bodegaid as Long) 
            proveedor{
                eq( 'codigo', params.proveedorid )            
            }
            articulo{
                eq( 'codigo', params.articuloid)            
            }
            join 'articulo'
        }
        
        render(contentType:"text/json") {
            data {
                if( result != null ){
                    valor = result.valor
                    stock = result.cantidad
                    id = result.id                    
                    articulo = result.articulo.toString()
                    proveedor = result.proveedor.toString()
                }else{
                    error = "No se encuentra catalogo definido para el articulo."
                }
            }	
	}
        
    }
    
    
    def list = { 
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
        def listmvn = commonService.listOutputType() 
        
        [articuloInstanceTotal:0, listPrv:listprv, listBdg:listbdg, listMov:listmvn, movement:new Movimiento()]
    }
    
    def listCatalogo = {
        log.info "--> Buscando el catalogo de articulos idprv: ${params.proveedorid} idbdg: ${params.bodegaid}"
        
        def result = catalogoService.listCatalogo( params.bodegaid, params.proveedorid )
        def model = [catalogoList:result, movement: Movimiento.read(params.mvmid)]
        render( template:'articulos', model:model )  
    }

    
    def ouputArt = {
        log.info "--> Agregando salida a los movimientos idbdg: ${params.bodegaid} fechaid: ${params.fechavalue} tipo: ${params.movetypeid}"
        
        def arrayid = []
        arrayid.addAll( params.ctl )
        def arraycant = []
        arraycant.addAll( params.cant )
        def arrayref = []
        
        if( params.ref ){
            arrayref.addAll( params.ref )
        }
        
        def date = commonService.transformDate(params.fechavalue)           
        def user = 'SALIDA'
        
        try{
            def cant = movimientosService.addOutMovement(  params.movetypeid, date, arrayid, arraycant, arrayref );                     
            response.contentType = "text/javascript" 
            render "alert('${cant} articulos descargados del inventario.')"         
        }catch(RuntimeException e){
            log.error "Exception message $e.message, $e"
            response.contentType = "text/javascript" 
            render "alert('No se pudo realizar la salida, causa: $e.message')"
        } 
    }
}
