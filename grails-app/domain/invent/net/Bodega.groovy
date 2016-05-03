package invent.net

class Bodega {
    
    String nombre 
    Ciudad ciudad
    String direccion
    String telefono
    String encargado
    
    static constraints = {
        nombre(nullable:false, maxSize:80)
        ciudad()
        direccion(nullable:false, maxSize:200)
        telefono(nullable:false, maxSize:30)
        encargado(nullable:false, maxSize:60)
    }
    
    static mapping = {
        sort "nombre"
        cache true     
        ciudad fetch: 'join'
    }
        
    String toString(){
        return this.nombre
    }
}
