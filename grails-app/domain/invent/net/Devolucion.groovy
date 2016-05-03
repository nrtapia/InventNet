package invent.net

class Devolucion { 
    
    Bodega bodega
    Proveedor proveedor
    Articulo articulo
    Date fecha
    Integer cantidad
    Integer canting = 0
    BigDecimal costo
    BigDecimal valor
    String estado = 'Pendiente'
    Date control = new Date()  
    String detalle = ' '

    static constraints = {
        bodega()
        proveedor()
        articulo()
        fecha(nullable:false)
        costo(nullable:false)
        valor(nullable:false)        
        estado(inList:['Pendiente','Cumplida'])
        cantidad( nullable:false, min:1 )
        control(nullable:false)
        canting()
        detalle(maxSize:100)
    }
    
    static mapping = {        
        cache : true
        articulo fetch: 'join'
    }
    
    def afterUpdate(){
       Devolucion.withNewSession{
            // Se busca el catalogo
            def criteria = Catalogo.createCriteria()
            def xcatalog = criteria.get{
                eq( "bodega", bodega )
                eq( "proveedor", proveedor )
                eq( "articulo", articulo ) 
            }
               
            // Si se reciben devuelta
            if( canting != null && canting > 0 ){
                xcatalog.cantidad = (xcatalog.cantidad + canting)                
            }                        
            // Se actualiza
            xcatalog.save()
            
            // Se busca el movimiento
            def xmov = Movimiento.findByTipoAndCodigo( 'INP', 'DPV' )
            
            // 2012-AGO-30 se debe enviar la fecha de la devolucion no la del dia
            /*
            def date = new Date()
            date.hours = 0
            date.minutes = 0
            date.seconds = 0
            */
            
            // Se realiza el movimiento en el inventario
            def inv = new Inventario()
            inv.bodega = bodega
            inv.proveedor = proveedor
            inv.articulo  = articulo
            inv.cantidad = canting
            inv.costo = costo
            inv.valor = valor
            inv.tipo = 'INP'
            inv.movimiento = xmov
            inv.usuario = 'Devolucion'
            // 2012-AGO-30 se debe enviar la fecha de la devolucion no la del dia
            //inv.fecha = date            
            inv.fecha = fecha
            inv.control = new Date()
            inv.ref = "ID:${id}"
            inv.save()
        }
    }
    
}
