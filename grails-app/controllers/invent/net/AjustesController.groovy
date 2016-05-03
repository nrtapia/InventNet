package invent.net

class AjustesController {
    
    def commonService
    def movimientosService

    def list = {         
        def listbdg = commonService.listWarehouses(1)
        def listmvn = Movimiento.findAll("from Movimiento as m order by m.abrevia, m.detalle", [cache:true])

        def movement = new Movimiento()
        log.info(">>>> Movimiento del ajuste ${movement}")

        [listBdg:listbdg, listMov:listmvn, movement:movement]
    }


    def updref(){

        try{
            this.movimientosService.updateRef(  params.id, params.ref  )
            response.contentType = "text/javascript"
            render "alert('Referencia actualizada con exito!')"
        }catch(RuntimeException e){
            log.error "Exception message $e.message, $e"
            response.contentType = "text/javascript"
            render "alert('No se pudo realizar la actualizacion, causa: $e.message')"
        }


    }
    
    def listMovement = {
        log.info "-- Buscando movimientos para bodega:${params.bodegaid} movimiento:${params.movtoid} fecha:${params.fechaid}"
        
        def date = commonService.transformDate(params.fechaid)        
        
        def list = movimientosService.findMovements( params.bodegaid, params.movtoid, date )
        def movement =  Movimiento.read(params.movtoid)

        def model = [catalogoList:list, movement:movement]
        render( template:'articulos', model:model )          
    }
    
    def deleteMovement ={
        log.info "-- Ajustando movimiento id: ${params.idinv}"
        
        def resp = movimientosService.adjust( params.idinv )
        
        response.contentType = "text/javascript" 
        render "alert('${resp} articulos ajustados del inventario')"                 
    }
}
