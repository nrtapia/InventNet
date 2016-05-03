package com.inventnet.util

/**
 *
 * @author Neider
 */

public enum QueryTypes {
    CTLGNR('Catalogo por Genero'), MTODRO('Movimiento Diario'),LSTART('Listado de Articulos'),CTLPRV('Catalogo por Proveedor'), LSRPRV('Lista de Proveedores')
    
    final String value
    QueryTypes(String value){ this.value = value }

    @Override
    String toString(){ value }
    String getKey() { name() }
}

