package invent.net

class TipoDocumento {
   
    String codigo
    String detalle
    Movimiento movimiento
    
    static constraints = {
        codigo(nullable:false, maxSize:3, unique:true)
        detalle(nullable:false, maxSize:60)
        movimiento(nullable: false)
    }
    
    static mapping = {
        sort "detalle"
        cache true    
    }
    
    String toString(){
        return this.detalle
    }
}
