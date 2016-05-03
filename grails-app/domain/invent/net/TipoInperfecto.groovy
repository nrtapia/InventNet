package invent.net

class TipoInperfecto {
    
    static mapping = {
        sort "detalle"
        cache true        
    }
    

    String detalle
    
    static constraints = {
    }
    
    String toString() {
        return this.detalle
    }

}
