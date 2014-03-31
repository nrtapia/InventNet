package invent.net

class Catalogo { 
    
    Articulo articulo
    Proveedor proveedor
    Bodega bodega
    BigDecimal valor
    BigDecimal costo
    Integer cantidad
    Integer stockmin = 12

    static constraints = {
        costo(nullable:true, scale:2)
        valor(nullable:false, scale:2)
        cantidad(nullable:false)
        articulo()
        proveedor()
        bodega()
    }
    
    static mapping = {  
        cache:true        
    }
}
