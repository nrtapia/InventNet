package invent.net

class Referencia {
    
    static belongsTo = [proveedor:Proveedor]
    
    String detalle
    String descripcion
    
    static constraints = {
        detalle(nullable:false, maxSize:80)
        descripcion(nullable:false, maxSize:150)
    }
    
    static mapping = {
        sort "detalle"
    }
    
    String toString(){
        return this.detalle
    }
}
