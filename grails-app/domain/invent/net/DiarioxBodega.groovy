package invent.net

class DiarioxBodega {
    
    Bodega bodega
    Date fecha
    BigDecimal valor    
    BigDecimal costo
    Integer cantidad
    
    static constraints = {
        bodega()
        fecha(nullable:false)
        valor(nullable:false)      
    }
}
