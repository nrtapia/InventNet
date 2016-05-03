package invent.net

class TipoArticulo {
    
    static searchable = true    

    String codigo
    String detalle
    boolean switche
    double valor 
    String abrevia
    
    static constraints = {
        codigo(nullable:false, maxSize:3, unique:true)
        detalle(nullable:false, maxSize:60)
        switche()
        valor(scale:2)
        abrevia(nullable:true, maxSize:3)
    }
    
    static mapping = {
        sort "detalle"
        cache true
    }
    
    String toString(){
        return this.detalle
    }
}
