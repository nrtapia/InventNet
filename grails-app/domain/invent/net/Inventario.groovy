package invent.net

class Inventario {

    
    Bodega bodega
    Proveedor proveedor
    Articulo articulo
    Integer cantidad    
    BigDecimal costo    
    BigDecimal valor    
    String tipo
    Movimiento movimiento
    String usuario
    Date fecha
    Date control
    String ref

    static constraints = {
        bodega()
        proveedor()
        articulo()
        cantidad()        
        valor()
        costo()
        tipo(inList:["INP", "OUT"])
        movimiento()
        usuario(nullable:false)
        fecha(nullable:false)
        ref(nullable:true, maxSize:10)
    }
    
    static mapping = {
        
    }
 
    
}
