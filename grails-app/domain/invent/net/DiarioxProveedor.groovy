package invent.net

class DiarioxProveedor { 

    Bodega bodega
    Proveedor proveedor
    Date fecha
    BigDecimal valor    
    BigDecimal costo
    Integer cantidad
    
    static constraints = {
        bodega()
        proveedor()
        fecha(nullable:false)
        valor(nullable:false)
    }
}
