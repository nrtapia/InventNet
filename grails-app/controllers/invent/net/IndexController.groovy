package invent.net

import grails.converters.*
import invent.net.*

class IndexController {

    def commonService

    def index() {
        render(view: "/index", model: [ salidactr: grailsApplication.config.grails.param.out.controller,
                                        salidaact: grailsApplication.config.grails.param.out.action] )
    }

    def fillAlerts(){

        def margen = grailsApplication.config.grails.param.stock
        log.info("-- Margen para stock minimo ${margen}")

        def criteria = Catalogo.createCriteria()
        def listcatl = criteria.list( max:10 ){
            inList 'bodega', commonService.listWarehouses(1)
            sqlRestriction "stockmin + ${margen} >= cantidad "
            gt("cantidad", 0)
            join 'articulo'
            join 'proveedor'
            join 'bodega'
            order 'stockmin', 'asc'
        }

        HashMap jsonMap = new HashMap()

        jsonMap.data = listcatl.collect {comp ->
            return [articulo: comp.articulo.toString(), proveedor: comp.proveedor.toString(),
                    artcode: comp.articulo.codigo, prvcode: comp.proveedor.codigo,
                    cantidad: comp.cantidad, stockmin: comp.stockmin, bodega: comp.bodega.toString()  ]
        }

        render jsonMap as JSON
    }

}
