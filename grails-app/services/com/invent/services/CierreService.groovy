package com.invent.services

import org.hibernate.*
import org.hibernate.criterion.*
import org.hibernate.type.Type

import groovy.sql.Sql 
import invent.net.*
import java.text.SimpleDateFormat
import groovy.time.*


import org.springframework.transaction.annotation.Transactional

@Transactional
class CierreService {
    
    
    def dataSource
    def sessionFactory 

    @Transactional( readOnly = true )
    def calculateInvByBodega( bodegaid, fchbgn, fchend ){
        log.info "-- Iniciando calculo de rotacion x bodega $bodegaid, $fchbgn, $fchend"
        
        // Se buscan las compras del periodo        
        def c = Inventario.createCriteria()
        def compras = c.get(){
            if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
            }
            eq( 'movimiento.id', 9 as Long)
            between( 'fecha', fchbgn, fchend )
            projections{
                //sum('costo')
                addProjectionToList(Projections.sqlProjection(
                    "sum(costo * cantidad) as totalPrice",
                    ["totalPrice"] as String[], 
                    [Hibernate.DOUBLE] as Type[],
                ), "sumProjection")
            }
        }
        
        // Buscar el Inv. inicial
        c = DiarioxBodega.createCriteria()
        def ivnInicial = c.get(){
             if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
             }
             eq( 'fecha', fchbgn )
              projections{
                sum('costo')
              }
        }
        def invini = ivnInicial == null ? null : ivnInicial
        
        // Buscar el Inv. final
        c = DiarioxBodega.createCriteria()
        def ivnFinal = c.get(){
             if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
             }
             eq( 'fecha', fchend )
             projections{
                 sum('costo')
             }
               
        }
        
        return calculateIndicador( fchbgn, fchend, compras, ivnInicial, ivnFinal )
    }
    
    
    
    @Transactional( readOnly = true )
    def calculateInvByProvider( bodegaid, providerid, fchbgn, fchend ){
        log.info "-- Iniciando calculo de rotacion x inventario $bodegaid, $providerid, $fchbgn, $fchend"
        
        // Se buscan las compras del periodo        
        def c = Inventario.createCriteria()
        def compras = c.get(){
            if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
            }
            eq( 'proveedor.id', providerid )
            eq( 'movimiento.id', 9 as Long)
            between( 'fecha', fchbgn, fchend )
            projections{
                //sum('costo')
                addProjectionToList(Projections.sqlProjection(
                    "sum(costo * cantidad) as totalPrice",
                    ["totalPrice"] as String[], 
                    [Hibernate.DOUBLE] as Type[],
                ), "sumProjection")
            }
        }
        
        // Buscar el Inv. inicial
        c = DiarioxProveedor.createCriteria()
        def ivnInicial = c.get(){
             if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
             }
             eq( 'proveedor.id', providerid )
             eq( 'fecha', fchbgn )
              projections{
                sum('costo')
              }
        }
        
        // Buscar el Inv. final
        c = DiarioxProveedor.createCriteria()
        def ivnFinal = c.get(){
             if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
             }
             eq( 'proveedor.id', providerid )
             eq( 'fecha', fchend )
             projections{
                 sum('costo')
             }
               
        }
        
        return calculateIndicador( fchbgn, fchend, compras, ivnInicial, ivnFinal )
    }
    
    @Transactional( readOnly = true )
    def calculateInvByArticle( bodegaid, articuloid, fchbgn, fchend ){
        log.info "-- Iniciando calculo de rotacion x articulo $bodegaid, $articuloid, $fchbgn, $fchend"
        
        // Se buscan las compras del periodo        
        def c = Inventario.createCriteria()
        def compras = c.get(){
            if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
            }
            eq( 'articulo.id', articuloid )
            eq( 'movimiento.id', 9 as Long)
            between( 'fecha', fchbgn, fchend )
            projections{
                //sum('costo')
                addProjectionToList(Projections.sqlProjection(
                    "sum(costo * cantidad) as totalPrice",
                    ["totalPrice"] as String[], 
                    [Hibernate.DOUBLE] as Type[],
                ), "sumProjection")
            }
        }
        
        // Buscar el Inv. inicial
        c = DiarioxArticulo.createCriteria()
        def ivnInicial = c.get(){
             if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
             }
             eq( 'articulo.id', articuloid )
             eq( 'fecha', fchbgn )
              projections{
                sum('costo')
              }
        }
        
        // Buscar el Inv. final
        c = DiarioxArticulo.createCriteria()
        def ivnFinal = c.get(){
             if( bodegaid != null ){
                eq( 'bodega.id', bodegaid )
             }
             eq( 'articulo.id', articuloid )
             eq( 'fecha', fchend )
             projections{
                 sum('costo')
             }
               
        }
        
        return calculateIndicador( fchbgn, fchend, compras, ivnInicial, ivnFinal )
    }
    
    
    // Se calcula el inventario por bodega
    @Transactional( readOnly = true )
    def calculateIndicador( fchbgn, fchend, compras, ivnInicial, ivnFinal ){
        log.info "-- Iniciando calculo de indicador de rotacion"
        println ">>>> $fchbgn, $fchend, $compras, $ivnInicial, $ivnFinal "
        
        // Se calculan los dias del rango
        def dif = fchend - fchbgn + 1
        
        if( compras == null ){
            compras = 0
        }
       
        if( !ivnInicial || ivnInicial == 0 ){
            throw new RuntimeException('No se encontro inventario inicial')
        }
       
        if( !ivnFinal || ivnFinal == 0 ){
            throw new RuntimeException('No se encontro inventario final')
        }
        
        // Se calcula el costo de venta
        def ctoVentas = ivnInicial + compras - ivnFinal
        
        // Se calcula la rotacion
        //def rotacion = ((((ivnInicial.costo + ivnFinal.costo)/2)*dif) / ctoVentas )/100
        def rotacion =   ctoVentas / ((ivnInicial + ivnFinal)/2)
        
        println ">>> $rotacion"
        
        //rotacion = rotacion.trunc()
        
        def response = [:]
        response.put('compras', compras)
        response.put('ivnInicial', ivnInicial)
        response.put('ivnFinal', ivnFinal)
        response.put('ctoVentas', ctoVentas)
        response.put('rotacion', rotacion)
        response.put('dias', dif )
        
        return response
    }
    
    
    
    
    
    
    

    def diario( bodegaid, fecha, username ) { 
        def startTime = System.nanoTime()
        
        def sdfmt = new SimpleDateFormat("dd/MM/yyyy") 
        def sdfmtSql = new SimpleDateFormat("yyyy-MM-dd") 
        
        def bodega = Bodega.read( bodegaid )     
        
        log.info ">>> Cierre diario, se verifica si hay un cierre el dia anterior"
        def ayer = fecha - 1   
        def cierreAyer = CierreDiario.findByBodegaAndFecha( bodega, ayer  )
        
        // Se busca si existe una autorizacion para cierre
        def auth = AutorizaCierre.findByBodegaAndFechaAndEstado( bodega, fecha, 'Abierto' )
        log.info ">>> Autorizacion para validar el cierre: ${auth}"
        
        if( !cierreAyer && !auth ){ 
            throw new RuntimeException("No hay cierre registrado para el dia ${sdfmt.format(ayer)}")
        }else{
            log.info ">>> Se verifica si hay movimientos del dia de manana"
            def manana = fecha + 1
            def inv = Inventario.findByBodegaAndFecha( bodega, manana )
            if( inv  && !auth ){
                throw new RuntimeException("Existen movimientos registrados para el dia ${sdfmt.format(manana)}")
            }else{
                log.info ">>> Se verifica si hay cierre para el dia de hoy"
                def cierreHoy = CierreDiario.findByBodegaAndFecha( bodega, fecha )
                if( cierreHoy ){
                    log.info ">>> Se borra el cierre del dia"
                    cierreHoy.delete(flush:true)
                    
                    log.info ">>> Se borra el inventario por bodega del dia"
                    DiarioxBodega.executeUpdate("delete from DiarioxBodega d where d.bodega=? and d.fecha=?", [bodega,fecha])
                    log.info ">>> Se borra el inventario por proveedor del dia"
                    DiarioxProveedor.executeUpdate("delete from DiarioxProveedor d where d.bodega=? and d.fecha=?", [bodega,fecha])
                     log.info ">>> Se borra el inventario por articulo del dia"
                    DiarioxArticulo.executeUpdate("delete from DiarioxArticulo d where d.bodega=? and d.fecha=?", [bodega,fecha])        
                   
                }
                
                /*
                 *No se verifica movimimientos para el dia (domingo no hay movimiento)
                inv = Inventario.findByBodegaAndFecha( bodega, fecha )
                if( !inv ){
                    throw new RuntimeException("No hay movimientos registrados para el dia ${sdfmt.format(fecha)}")
                }
                */
                

                log.info ">>> Se calcula el inventario diario por bodega"     
                def db = new Sql(dataSource) 
                db.execute( "insert into diariox_bodega (bodega_id,fecha,cantidad,costo,valor,version) " +
                    "select c.bodega_id, '${sdfmtSql.format(fecha)}' as fecha, sum(c.cantidad) as cantidad, " + 
                    "sum(c.costo*c.cantidad) as costo, sum(c.valor*c.cantidad) as valor, 0 as version " +
                    "from catalogo c " +
                    "where c.bodega_id = ? " +
                    "group by 1", [bodegaid] ) 
                
                log.info ">>> Se calcula el inventario diario por proveedor" 
                db.execute( "insert into diariox_proveedor (bodega_id,proveedor_id,fecha,cantidad,costo,valor,version) " +
                    "select c.bodega_id, c.proveedor_id, '${sdfmtSql.format(fecha)}' as fecha, sum(c.cantidad) as cantidad, " + 
                    "sum(c.costo*c.cantidad) as costo, sum(c.valor*c.cantidad) as valor, 0 as version " +
                    "from catalogo c " +
                    "where c.bodega_id = ? " +
                    "group by 1,2", [bodegaid] ) 
                
                log.info ">>> Se calcula el inventario diario por articulo" 
                db.execute( "insert into diariox_articulo (bodega_id,articulo_id,fecha,cantidad,costo,valor,version) " +
                    "select c.bodega_id, c.articulo_id, '${sdfmtSql.format(fecha)}' as fecha, sum(c.cantidad) as cantidad, " + 
                    "sum(c.costo*c.cantidad) as costo, sum(c.valor*c.cantidad) as valor, 0 as version " +
                    "from catalogo c " +
                    "where c.bodega_id = ? " +
                    "group by 1,2", [bodegaid] ) 

                def cierreDiarioInstance = new CierreDiario()
                cierreDiarioInstance.bodega = bodega
                cierreDiarioInstance.control  = new Date()
                cierreDiarioInstance.fecha = fecha
                cierreDiarioInstance.usuario = username

                if (!cierreDiarioInstance.save(flush: true)) {
                    throw new RuntimeException("Error al crear el cierre diario")
                }
               

                // Se cambia el estado del cierre
                if( auth != null ){ 
                    auth.estado = 'Cerrado'
                    auth.save()
                }
                
                
                def session = sessionFactory.getCurrentSession()
                session.flush()
                session.clear()    

                def endTime =  System.nanoTime()
                def diff = (endTime-startTime)/1000000000
                log.info ">>> Cierre diario para la bodega: $bodegaid, fecha:$fecha tiempo ${diff}"   
            }
        }
        
        
    }
}
