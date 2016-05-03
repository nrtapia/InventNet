package invent.net

class DiarioxArticulo {

    Bodega bodega
    Articulo articulo
    Date fecha
    BigDecimal valor  
    BigDecimal costo
    Integer cantidad
    
    static constraints = {
        bodega()
        articulo()
        fecha(nullable:false)
        valor(nullable:false)
    }
}
