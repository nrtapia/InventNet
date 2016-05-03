package invent.net

class AutorizaCierre { 
    
    Bodega bodega
    String usuario
    Date control = new Date()
    Date fecha 
    String estado = 'Abierto'
    String observaciones
    
    static mapping = {
        sort fecha : "desc"
    }

   static constraints = {
        bodega( nullable:false )
        usuario(nullable:false)
        fecha(nullable:false)
        estado(nullable:false, inList: ['Abierto', 'Cerrado'])
        observaciones(nullable:false, maxSize:200)
        
    }
    
    
}
