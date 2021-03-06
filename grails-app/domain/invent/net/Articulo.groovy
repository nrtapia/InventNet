package invent.net 

class Articulo {
    
    static hasMany = [fotos:Foto]
    
    static searchable = {
        tipoArticulo component : true
        claseArticulo component : true
        cualidadesArticulo component : true
        genero component : true
    }
        
    String codigo
    TipoArticulo tipoArticulo
    ClaseArticulo claseArticulo
    Genero genero
    CualidadesArticulo cualidadesArticulo
    String referencia
    String descripcion
    
    static constraints = {
        codigo(nullable:false, maxSize: 10, unique:true)
        tipoArticulo()
        claseArticulo()
        genero()
        cualidadesArticulo()
        referencia(nullable:false, maxSize: 2)
        descripcion(nullable:false, maxSize: 100) 
        
    }
    
    static mapping = {
        sort : "tipoArticulo.detalle,genero.detalle,claseArticulo.detalle,cualidadesArticulo.detalle,referencia,descripcion"
        cache : true
        
        codigo index: 'idx_codigo'
        
        tipoArticulo fetch: 'join'
        claseArticulo fetch: 'join'
        cualidadesArticulo fetch: 'join' 
        genero fetch: 'join'  
        fotos fetch: 'join'  
    }
    
    String toString(){
        return ( codigo + " " + ( tipoArticulo?.detalle?.trim() + " " + genero?.detalle?.trim() + " " + claseArticulo?.detalle?.trim() + " " + cualidadesArticulo?.detalle?.trim() + " " + referencia?.trim() ).trim() + " " + descripcion?.trim())
    }
}
