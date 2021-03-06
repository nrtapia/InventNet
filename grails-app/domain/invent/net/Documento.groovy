package invent.net

class Documento {
   
    static hasMany = [articulosxDocumentos:ArticulosxDocumento]
    
    Bodega bodega
    TipoDocumento tipoDocumento
    Proveedor proveedor
    Date fecha
    Date control
    String usuario
    String ref1
    String ref2
    String ref3
    String ref4
    
    Date dateCreated 
    Date lastUpdated
    
    static constraints = {
        bodega(nullable:false)
        tipoDocumento(nullable:false)
        proveedor(nullable:false)
        fecha(nullable:false)
        control(nullable:false)
        
        ref1(maxSize: 20)        
        ref1(maxSize: 20)
        ref2(maxSize: 20)
        ref3(maxSize: 20)
        ref4(maxSize: 20)        
    }
    
    static mapping = {
        sort : "fecha"
        order : "desc"

        bodega fetch: 'join'
        tipoDocumento fetch: 'join'
        proveedor fetch: 'join'

        fecha index: 'fecha_idx'
        ref1 index: 'ref1_idx'
        ref2 index: 'ref2_idx'
        ref3 index: 'ref3_idx'
        ref4 index: 'ref4_idx'
    }
        
}
