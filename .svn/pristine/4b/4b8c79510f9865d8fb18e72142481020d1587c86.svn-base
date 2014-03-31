package invent.net

class Movimiento {
    
    String tipo
    String codigo
    String detalle
    String abrevia
    boolean mostrar = true
    boolean ref     = false
        
    static constraints = {
        tipo(nullable:false, maxSize:3, inList:['INP','OUT'])        
        codigo(nullable:false, maxSize:3, unique:true)
        detalle(nullable:false, maxSize:60)
        abrevia(nullable:false, maxSize:3)
        mostrar()
        ref()
    }
    
    static mapping = {
        sort "detalle"
        cache true
    }
    
    String toString(){
        return "${abrevia} ${this.detalle}"
    }
}
