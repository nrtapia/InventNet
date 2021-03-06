package com.invent.services

import invent.net.*
import java.text.SimpleDateFormat
import com.inventnet.security.*


class CommonService { 

    static transactional = false
    def springSecurityService


    def listSuppliers() {        
        return Proveedor.findAllByEstado('Activo',[sort:'codigo', cache:true])
    }   
    
    def listWarehouses( user ){ 
        def curuser = SecUser.read( springSecurityService.currentUser.id )
        if( curuser ){
            return curuser.bodegas.sort{it.nombre}
        }else{
            return null
        }
    } 
    
    def listInputType(){
        return Movimiento.findAllByTipoAndMostrar('INP', true, [cache:true])
    }
    
    def listOutputType(){
        return Movimiento.findAllByTipoAndMostrar('OUT', true, [cache:true])
    }
    
    def Date transformDate( value ){
        def sdfmt = new SimpleDateFormat("dd/MM/yyyy") 
        return sdfmt.parse(value)
    }
}
