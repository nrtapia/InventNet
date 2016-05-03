package invent.net

class Foto {
    static belongsTo = [articulo: Articulo]
     
    byte[] file     
    String name
    String type
    int size
    
    static constraints = {
        // Limit upload file size to 3MB
        file( maxSize: 1024 * 1024 * 3, required:true )
    }
    
    String toString(){
        return this.name
    }
}
