package com.invent.services

import invent.net.*;

class MovimientosService {

    static transactional = true
    
    def sessionFactory
    def springSecurityService

    def updateRef( id, newref ) {
        def Inventario inv = Inventario.get( id )
        if( inv != null ){
            inv.ref = newref
            inv.version = inv.version + 1
            inv.save(flush: true)
        }
    }
    
    def validateCierre( bodega, fecha ){ 
       log.info ">>> Datos para validar el cierre  bodega: ${bodega} - fecha: ${fecha}"  
        
        // Se busca si existe una autorizacion para cierre
        def aut = AutorizaCierre.findByBodegaAndFechaAndEstado( bodega, fecha, 'Abierto' )
        log.info ">>> Autorizacion para validar el cierre: ${aut}"
        
        // Si existe una autorizacion no se valida
        if( aut == null ){
            
            // Se verifica que exista cierre para el dia anterior
            def cierreayer = CierreDiario.findByBodegaAndFecha(bodega,fecha-1)
            if( !cierreayer ){
                throw new RuntimeException("No hay cierre para el dia anterior del movimiento")
            }else{
                // Se busca si hay movimientos para dias despues 
                def movmanana = Inventario.findByBodegaAndFechaGreaterThan( bodega, fecha )
                if( movmanana ){
                    throw new RuntimeException("Existe movimientos registrados despues de la fecha")
                }   
            }
        }
    }
    
    def registerSuba( subasInstance ){
        // Se valida el cierre
        validateCierre( subasInstance.bodega, subasInstance.fecha )
        // Se registra la suba
        subasInstance.save(flush:true)
        return subasInstance   
    }

    def refundToProvider( objDev, idCatalogo ){
        
        // Se busca el movimiento de la devolucion
        def mov = Movimiento.findByTipoAndCodigo('OUT','DVL')
        // Se persiste la devolucion
        objDev.save()               
        // Se realiza el movimiento en el inventario
        addInputMovement( objDev.bodega.id, objDev.proveedor.id, mov.id, objDev.fecha, idCatalogo, objDev.cantidad as String, 'DEVOLUCION', 'OUT', "ID:${objDev.id}" )                          
    }   
    
    def registerImperfect( obj ){
                
        // Se busca el movimiento
        def xmovimiento = Movimiento.findByCodigoAndTipo( 'INP', 'OUT' )
        // Se busca el catalogo
        def criteria = Catalogo.createCriteria()
        def xcatalogo = criteria.get {
            eq( 'bodega', obj.bodega) 
            eq( 'proveedor', obj.proveedor)            
            eq( 'articulo', obj.articulo)        
        } 
       
        // Se registra el movimiento
        def resp = obj.save()
        if( resp ){
            addInputMovement( obj.bodega.id, obj.proveedor.id, xmovimiento.id, obj.fecha, xcatalogo.id, obj.cantidad as String, 'INPERFECTO', 'OUT', "ID:${obj.id}" )
            return true
        }else{        
            return false
        }
    }
    

    def int addInputMovement( bdg, prv, type, dateMov, arrayIds, arrayCant, user, inout, arrayRef ) {
        log.info "-- Agregando movimiento de ${inout}..."                       
        def startTime = System.nanoTime()
        
        // Nombre del usuario en sesion
        user = springSecurityService.principal.username        
        
        def xarrayid = []
        xarrayid.addAll( arrayIds )
        def xarraycant = []
        xarraycant.addAll( arrayCant )
        
        def xarrayref = []
        if( arrayRef != null ){
            xarrayref.addAll( arrayRef )
        }
                
        def xbodega     = Bodega.get(     bdg )
        def xproveedor  = Proveedor.get(  prv )
        def xmovimiento = Movimiento.get( type ) 
        
        // Se valida el cierre
        validateCierre( xbodega, dateMov )
        
        def cantupd = 0
        def valor = new BigDecimal(0)
        def cant = 0
       
        (0..xarrayid.size()-1).each{ i -> 
            
            cant = xarraycant[i] as int            
            if( cant > 0 ){                            
                
                // Se busca el catalogo
                def xcatalogo = Catalogo.findById( xarrayid[i], [fetch:[articulo:'join', proveedor:'join', bodega:'join', articulo:'join']] )
                valor = xcatalogo.valor
                
                log.info "--> Movimiento para el inventario tipo: ${xmovimiento.tipo} codigo: ${xmovimiento.codigo}"
                  
                def ref = ( xarrayref.size>0 ? xarrayref[i] : '' )
                
                // Se registra el movimiento
                def invent = new Inventario( bodega:xbodega, proveedor:xproveedor, articulo:xcatalogo.articulo, 
                    cantidad:cant, valor:valor, tipo:xmovimiento.tipo, costo: xcatalogo.costo,
                    movimiento:xmovimiento, usuario:user, fecha:dateMov, control:new Date(),
                    ref:ref )                
                
                // Se persiste el inventario
                invent.save()
                                
                // *** Actualizacion del catalogo                 
                // Se incrementa la cantidad                
                if( 'INP' == xmovimiento.tipo ){
                    xcatalogo.cantidad = (xcatalogo.cantidad + cant)                
                }else{
                    xcatalogo.cantidad = (xcatalogo.cantidad - cant)
                }                
                // Se actualiza el catalogo
                xcatalogo.save()    
                
                if (invent.hasErrors() || xcatalogo.hasErrors() ) {
                    log.error "--- ERROR al registrar entrada: ${invent.errors} - ${xcatalogo.errors}"       
                    throw new RuntimeException("ERROR al registrar entrada: ${invent.errors} - ${xcatalogo.errors}")
                }                
                cantupd = cantupd + cant
            }            
        }
                
        def session = sessionFactory.getCurrentSession()
        session.flush()
        session.clear()    
        
        def endTime =  System.nanoTime()
        def diff = (endTime-startTime)/1000000000
        log.info "TIME TAKEN ADD INPUT MOVED IS ::: ${diff}"   
        
        return cantupd
    }
    
    
    
    def List findMovements( bdgid, mvntype, date  ){
          
        def criteria = Inventario.createCriteria()
        def results = criteria.listDistinct {
            eq( 'bodega.id', bdgid as Long) 
            eq( 'movimiento.id', mvntype as Long)
            eq( 'fecha', date )
            join 'articulo'
        }
        
        return results
    }
    
    def int adjust( id ){       
                
        def criteria = Inventario.createCriteria() 
        def inv = criteria.get {
            eq( 'id', id as Long)             
            join 'articulo'
            join 'proveedor'
            join 'bodega'
        }
        
        // Se valida el cierre
        //validateCierre( inv.bodega, inv.fecha )
        
        def newCant = inv.cantidad
        if( inv.tipo == 'INP' ){
            newCant = inv.cantidad * -1
        }
        
        // Se guarda la auditoria del ajuste
        def audita = new AuditaAjuste( bodega: inv.bodega, proveedor: inv.proveedor, articulo: inv.articulo, cantidad: inv.cantidad, valor: inv.valor, tipo: inv.tipo, movimiento: inv.movimiento, fechaMov: inv.fecha, fechaAjuste: new Date() )
        audita.save(flush:true)
        
        Catalogo.executeUpdate("update Catalogo c set c.cantidad=(c.cantidad + :valCant) where c.proveedor.id=:idPrv and c.bodega.id=:idBdg and c.articulo.id=:idArt", 
            [idPrv:inv.proveedor.id, idBdg:inv.bodega.id, idArt:inv.articulo.id, valCant:newCant])        
        inv.delete()
                
        return inv.cantidad
    }
    
    
    def int addComprasMovement( bdg, prv, dateMov, arrayIds, arrayCosto, arrayValor, arrayCant ) {
                
        def xarrayid = []
        xarrayid.addAll( arrayIds )
        def xarraycant = []
        xarraycant.addAll( arrayCant )
        def xarrayval = []
        xarrayval.addAll( arrayValor )
        def xarraycost = []
        xarraycost.addAll( arrayCosto )
        
        def startTime = System.nanoTime()
            
        def cantupd = 0
        def newvalor = new BigDecimal(0)
        def newcost = new BigDecimal(0)
        def cant = 0
        
        // Se busca el movimiento
        def xmovimiento = Movimiento.findByCodigoAndTipo( 'CMP', 'INP' )
       
        (0..xarrayid.size()-1).each{ i -> 
            
            cant     = xarraycant[i] as int
            newvalor = xarrayval[i] as BigDecimal
            newcost  = xarraycost[i] as BigDecimal
            
            
            if( cant > 0 ){                            
                
                // Se busca el catalogo                               
                def criteria = Catalogo.createCriteria()
                def xcatalogo = criteria.get {
                    eq( 'bodega.id', bdg as Long) 
                    eq( 'proveedor.id', prv as Long)            
                    eq( 'articulo.id', xarrayid[i] as Long)
                    join 'articulo'
                    join 'proveedor'
                    join 'bodega'
                } 
                
                // Nombre del usuario en sesion
                def username = springSecurityService.principal.username 
                
                // Se valida el cierre
                validateCierre( xcatalogo.bodega, dateMov )
                                                            
                // Se registra el movimiento
                def invent = new Inventario( bodega:xcatalogo.bodega, proveedor:xcatalogo.proveedor, articulo:xcatalogo.articulo, 
                    cantidad:cant, valor:newvalor, tipo:xmovimiento.tipo, costo: newcost, 
                    movimiento:xmovimiento, usuario:username, fecha:dateMov, control:(new Date()) )                
                
                // Se persiste el inventario
                invent.save()
                
                // *** Actualizacion del catalogo                 
                // Se incrementa la cantidad
                xcatalogo.cantidad = (xcatalogo.cantidad + cant)                                
                // Se cambia el valor del catalogo
                if( xcatalogo.valor != newvalor ){
                   xcatalogo.valor = newvalor
                }
                // Se cambia el costo
                if( xcatalogo.costo != newcost ){
                    // Se calcula el promedio
                    if( xcatalogo.costo > 0 ){
                        xcatalogo.costo = ( xcatalogo.costo + newcost)/2
                    }else{
                        xcatalogo.costo = newcost;
                    }
                }
                // Se actualiza el catalogo
                xcatalogo.save()                
                                                
                if (invent.hasErrors() || xcatalogo.hasErrors() ) {
                    log.error "--- ERROR al registrar compra: ${invent.errors} - ${xcatalogo.errors}"       
                    throw new RuntimeException("ERROR al registrar compra: ${invent.errors} - ${xcatalogo.errors}")
                }                
                cantupd = cantupd + cant
            }            
        }
                
        def session = sessionFactory.getCurrentSession()
        session.flush()
        session.clear()    
        
        def endTime =  System.nanoTime()
        def diff = (endTime-startTime)/1000000000
        log.info "TIME TAKEN ADD INPUT MOVED IS ::: ${diff}"   
        
        return cantupd
    }



    def int addOutMovement( type, dateMov, arrayIds, arrayCant, arrayRef ) {
        log.info "-- Agregando movimiento de salida..."                       
        def startTime = System.nanoTime()
        
        // Nombre del usuario en sesion
        def user = springSecurityService.principal.username        
        
        def xarrayid = []
        xarrayid.addAll( arrayIds )
        def xarraycant = []
        xarraycant.addAll( arrayCant )
        
        def xarrayref = []
        if( arrayRef != null ){
            xarrayref.addAll( arrayRef )
        }
        
        def xmovimiento = Movimiento.get( type ) 
        
        def cantupd = 0
        def valor = new BigDecimal(0)
        def cant = 0
               
        (0..xarrayid.size()-1).each{ i -> 
            
            cant = ( xarraycant[i].trim() == '' ? 0  : xarraycant[i] as int )            
        
            if( cant > 0 ){                            
                
                // Se busca el catalogo
                def xcatalogo = Catalogo.findById( xarrayid[i], [fetch:[articulo:'join', proveedor:'join', bodega:'join', articulo:'join']] )
                valor = xcatalogo.valor
                
                log.info "--> Movimiento para el inventario tipo: ${xmovimiento.tipo} codigo: ${xmovimiento.codigo}"
                  
                def ref = ( xarrayref.size>0 ? xarrayref[i] : '' )
                
                // Se valida el cierre
                validateCierre( xcatalogo.bodega, dateMov )
                
                // Se registra el movimiento
                def invent = new Inventario( bodega:xcatalogo.bodega, proveedor:xcatalogo.proveedor, articulo:xcatalogo.articulo, 
                    cantidad:cant, valor:valor, tipo:xmovimiento.tipo, costo: xcatalogo.costo,
                    movimiento:xmovimiento, usuario:user, fecha:dateMov, control:new Date(),
                    ref:ref )                
                
                // Se persiste el inventario
                invent.save()
                                
                // *** Actualizacion del catalogo                 
                // Se incrementa la cantidad                
                if( 'INP' == xmovimiento.tipo ){
                    xcatalogo.cantidad = (xcatalogo.cantidad + cant)                
                }else{
                    xcatalogo.cantidad = (xcatalogo.cantidad - cant)
                }                
                // Se actualiza el catalogo
                xcatalogo.save()    
                
                if (invent.hasErrors() || xcatalogo.hasErrors() ) {
                    log.error "--- ERROR al registrar salida: ${invent.errors} - ${xcatalogo.errors}"       
                    throw new RuntimeException("ERROR al registrar salida: ${invent.errors} - ${xcatalogo.errors}")
                }                
                cantupd = cantupd + cant
            }            
        }
                
        def session = sessionFactory.getCurrentSession()
        session.flush()
        session.clear()    
        
        def endTime =  System.nanoTime()
        def diff = (endTime-startTime)/1000000000
        log.info "TIME TAKEN ADD INPUT MOVED IS ::: ${diff}"   
        
        return cantupd
    }
    
}

