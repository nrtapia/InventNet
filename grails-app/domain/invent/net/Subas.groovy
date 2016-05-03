package invent.net

class Subas {

    Bodega bodega
    Proveedor proveedor
    Articulo articulo
    Date fecha
    Date control = new Date()
    Integer cantidad    
    String tipo
    BigDecimal valor
    BigDecimal valorAnterior  
    Long catalogoid

    static constraints = {
        bodega(nullable:false)
        proveedor(nullable:false)
        articulo(nullable:false)
        fecha(nullable:false)
        tipo(nullable:false, inList:['Suba', 'Rebaja'])
        control(nullable:false)
        cantidad(nullable:false, min:1)        
        valor(nullable:false)           
        valorAnterior(nullable:false)    
        catalogoid(nullable:false)    
    }    
    
    String toString(){
        return this.tipo
    }
    
    
    def afterInsert(){
        Subas.withNewSession{

            // Se busca el catalogo
            def xcatalog = Catalogo.get(catalogoid)              
            // Se incrementa establece el nuevo valor
            xcatalog.valor = valor                                    
            // Se actualiza
            xcatalog.save()
        }
    }
    
}
