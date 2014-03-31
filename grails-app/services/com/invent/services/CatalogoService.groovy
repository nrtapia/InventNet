package com.invent.services

import org.springframework.transaction.annotation.*
import invent.net.*; 

class CatalogoService {
    
    def sessionFactory
    static transactional = true
    
    def List listCatalogo( idbdg, idprv ){
        
        def criteria = Catalogo.createCriteria()        
        def results = criteria.listDistinct {
            createAlias "articulo", "art"
            createAlias "articulo.tipoArticulo", "tpo"
            createAlias "articulo.genero", "gnr"
            createAlias "articulo.claseArticulo", "cls"
            createAlias "articulo.cualidadesArticulo", "cld"
            eq( 'bodega.id', idbdg as Long) 
            eq( 'proveedor.id', idprv as Long)            
            join 'articulo'           
            order 'tpo.detalle', 'asc' 
            order 'gnr.detalle', 'asc'
            order 'cls.detalle', 'asc'
            order 'cld.detalle', 'asc'
            order 'art.referencia', 'asc'
            order 'art.descripcion', 'asc'
        }
        
        return results
    }
    
    def deleteAllBySuplier( idbdg, idprv ){        
        Catalogo.executeUpdate("delete Catalogo c where c.proveedor.id = :idPrv and c.bodega.id = :idBdg", [idPrv: idprv, idBdg:idbdg])        
    }
    
    def deleteCatalogo(id){
        def ctlgo = Catalogo.get(id);
        
        if( ctlgo.cantidad >0 ){
         throw new RuntimeException("Hay stock en el Inventario.")   
        }
        
        ctlgo.delete(flush:true)
    }
    
    def addAsset( idbdg, idprv, idart ) {
        def prov = Proveedor.read( idprv ) 
        def bdg = Bodega.read( idbdg ) 
        def art = Articulo.read( idart )
        log.info "--> Registrando articulo [${idart}] proveedor[${idprv}] bodega [${idbdg}] en el catalogo..."
        def catalogo = new Catalogo( articulo:art, proveedor:prov, bodega:bdg, valor:0, cantidad:0, costo:0 )
        catalogo.save(flush:true, failOnError: true)
    }
    
    def addAllBySuplier( idbdg, idprv ){
        def startTime = System.nanoTime()
        
        def prov = Proveedor.get( idprv ) 
        def bdg = Bodega.get( idbdg ) 
        def resp = Articulo.list( )
        
        for(articulo in resp) {
            def catalogo = new Catalogo( articulo:articulo, proveedor:prov, bodega:bdg, valor:0, cantidad:0, costo:0 )
            catalogo.save()
        }
        
        def session = sessionFactory.getCurrentSession()
        session.flush()
        session.clear()    
        
        def endTime =  System.nanoTime()
        def diff = (endTime-startTime)/1000000000
        println "TIME TAKEN SAVE CATALOGO IS :::"+diff
    }
    
    def updateAllBySuplier( arrayIds, arrayValues, arraymin ){
        def startTime = System.nanoTime()
                    
        def list = []        
        (0..arrayIds.size()-1).each{ i ->                        
            log.info "-- Actualizando id[${arrayValues[i]}] value[${arrayIds[i]}]"                
            Catalogo.executeUpdate("update Catalogo c set c.valor = :pvalue, c.stockmin = :pmin where c.id = :pid",
                    [pvalue: new BigDecimal(arrayValues[i]), pid: new Long(arrayIds[i]), pmin: new Integer(arraymin[i]) ])
        }
        
        def session = sessionFactory.getCurrentSession()
        session.flush()
        session.clear()    
        
        def endTime =  System.nanoTime()
        def diff = (endTime-startTime)/1000000000
        log.info  "TIME TAKEN UPDATING CATALOGO IS ::: ${diff}"
    }
}
