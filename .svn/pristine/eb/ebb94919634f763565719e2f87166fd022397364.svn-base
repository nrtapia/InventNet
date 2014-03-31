package invent.net

class Proveedor {
    
    static searchable = true    
    static hasMany = [referencias:Referencia]
    
    String codigo
    String razonSocial
    String nit
    Ciudad ciudad
    String direccion
    String telefono
    String estado = "Activo"
    
    static constraints = {
        codigo(nullable:false, maxSize:4)
        razonSocial(nullable:false, maxSize:40)
        nit(nullable:false, maxSize:20)
        ciudad()
        direccion(nullable:false, maxSize:150)
        telefono(nullable:false, maxSize:30)
        estado(inList:["Activo", "Inactivo"])
    }
    
    static mapping = {    
        sort "razonSocial"
        cache true
        ciudad fetch: 'join'
    }
    
    
    
    
    String toString(){
        return "${codigo} - " +this.razonSocial?.trim()
    }
}
