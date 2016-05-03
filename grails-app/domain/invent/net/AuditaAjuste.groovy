package invent.net

class AuditaAjuste {
    
    Bodega bodega
    Proveedor proveedor
    Articulo articulo
    Integer cantidad    
    BigDecimal valor    
    String tipo
    Movimiento movimiento    
    Date fechaMov
    Date fechaAjuste

    static constraints = {
        bodega()
        proveedor()
        articulo()
        cantidad()        
        valor()
        tipo()
        movimiento()        
        fechaMov(nullable:false)
        fechaAjuste(nullable:false)
    }     
}