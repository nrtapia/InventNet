package invent.net

class Inperfectos {

    Bodega bodega    
    Proveedor proveedor
    Articulo articulo
    Date fecha
    Date control = new Date()
    Integer cantidad
    String observaciones
    TipoInperfecto tipo

    static constraints = {
        bodega(nullable:false)
        proveedor(nullable:false)
        articulo(nullable:false)
        fecha(nullable:false)
        control(nullable:false)
        tipo(nullable:false)
        cantidad(nullable:false, min:1)
        observaciones(nullable:false, maxSize:100, blank:false)        
    }
    
}
