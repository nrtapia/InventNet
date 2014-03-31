package invent.net

class Ciudad {
    
    String nombre
    String departamento

    static constraints = {
        nombre(nullable:false, maxSize:30)
        departamento(nullable:false, maxSize:20)
    }
    
    static mapping = {
        sort "nombre"
        cache true
    }
    
    String toString(){
        return this.nombre.trim() + " - " + this.departamento.trim()
    }
}
