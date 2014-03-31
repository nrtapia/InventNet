package invent.net

class CierreDiario {
    
    Bodega bodega
    Date fecha
    Date control
    String usuario

    static constraints = {
        bodega(nullable:false, unique:'fecha')
        fecha(nullable:false)
        control(nullable:false)
        usuario(blank:false)        
    }
    
}
