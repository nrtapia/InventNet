package invent.net

import invent.net.*
import grails.converters.*
import groovy.time.*

import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.*
import org.jfree.data.Range
import org.jfree.data.general.DefaultValueDataset
import org.jfree.data.general.ValueDataset
import org.jfree.chart.encoders.EncoderUtil

import java.awt.*

import org.codehaus.groovy.grails.commons.*
import org.apache.commons.lang.RandomStringUtils
import org.jfree.chart.ChartUtilities 

class StadisticController {
    
    def commonService 
    def exportService
    def cierreService
    

    def index() {
    
        def listbdg = commonService.listWarehouses(1)
        def listart = Articulo.list()
        
        [listart: listart, lstbdg: listbdg]        
    }
    
    def query() {
        // println "---- Params: ${params}" 
        // query:Consultar, art:117, fechaend:18/07/2012, bdg:21, option:Articulos, fechabegin:03/07/2012, action:query, controller:stadistic
        
        if( params.option == 'Articulos' ){
            redirect(action: "fillArticulo", params: params)
        }else
            if( params.option == 'Proveedores' ){
                redirect(action: "fillProveedor", params: params)
            }else
                 if( params.option == 'Movimientos' ){
                    redirect(action: "fillMovimiento", params: params)
                 }else{
                     if( params.option == 'Inv. x Bodega' ){
                          redirect(action: "fillInventario", params: params)
                     }else{
                         if( params.option == 'Inv. x Articulo' ){
                            redirect(action: "fillInvArt", params: params)
                         }else{
                            if( params.option == 'Inv. x Proveedor' ){
                                redirect(action: "fillInvPrv", params: params)
                            }else{
                                if( params.option == 'Rotacion Inventario x Bodega' ){
                                    params.type = 'BDG'
                                    redirect(action: "fillRotacion", params: params)
                                }else{
                                    if( params.option == 'Rotacion Inventario x Proveedor' ){
                                        params.type = 'PRV'
                                        redirect(action: "fillRotacion", params: params)
                                    }else{
                                        if( params.option == 'Rotacion Inventario x Articulo' ){
                                            params.type = 'ART'
                                            redirect(action: "fillRotacion", params: params)
                                        }
                                    }
                                }
                            }
                         }
                     }
                 }
                 
        params.put( "_file","stadistic" )                         
         
    }
    
    def fillRotacion(){
        
        def fchbgn = commonService.transformDate( params.fechabegin )
        def fchend = commonService.transformDate( params.fechaend )
        
        def bodegaid = null
        def bodega = null
        if( params.bdg != "" ){ 
            bodegaid = params.bdg as Long 
            bodega = Bodega.read( bodegaid )
        }
       
        def prvid = null
        def prv = null
        if( params.prv != "" ){ 
            prvid = params.prv as Long 
            prv = Proveedor.read( prvid )
        }
        
        def artid = null
        def art = null
        if( params.art != "" ){ 
            artid = params.art as Long 
            art = Articulo.read( artid )
        }
        
        def result = null
        try{
            if( params.type == 'BDG' ){
                result = cierreService.calculateInvByBodega( bodegaid, fchbgn, fchend)
            }
            if( params.type == 'PRV' ){
                if( prvid == null ){
                    throw new RuntimeException('El Proveedor es requerido')
                }
                result = cierreService.calculateInvByProvider( bodegaid, prvid, fchbgn, fchend)
            }
            
            if( params.type == 'ART' ){
                if( artid == null ){
                    throw new RuntimeException('El Articulo es requerido')
                }
                result = cierreService.calculateInvByArticle( bodegaid, artid, fchbgn, fchend)
            }
            
            result.put('bodega', bodega == null ? "Todas" : bodega.nombre )
            result.put('proveedor', prv == null ? "Todos" : prv.razonSocial )
            result.put('articulo', art == null ? "Todos" : art.toString() )
            result.put('fechas', "${params.fechabegin} - ${params.fechaend}")
            
        }catch(RuntimeException e){
            log.error "Error al calcular la rotacion $e.message", e
            
            flash.message = e.message
            redirect(action: "index")
            return
        }
        
        log.info ">>> Datos del indicador rotacion: $result"
        
        def dataset = new DefaultValueDataset( result.rotacion  )
        
        MeterPlot meterplot = new MeterPlot(dataset)
        meterplot.setBackgroundPaint(Color.white)

        // Rango Global
        meterplot.setRange(new Range(0.0D, 1.33D))
        
        // Rango indicador        
        meterplot.addInterval(new MeterInterval("Excelente", new Range(1D, 1.33D), Color.lightGray, 
                new BasicStroke(2.0F), new Color(0, 255, 255)))
        meterplot.addInterval(new MeterInterval("Bueno", new Range(0.66D, 1D), Color.lightGray, 
                new BasicStroke(2.0F), new Color(0, 255, 0, 64)))
        meterplot.addInterval(new MeterInterval("Normal", new Range(0.33D, 0.66D), Color.lightGray, 
                new BasicStroke(2.0F), new Color(255, 255, 0, 64)))        
        meterplot.addInterval(new MeterInterval("Bajo", new Range(0D, 0.33D ), Color.lightGray, 
                new BasicStroke(2.0F),  new Color(255, 0, 0, 128)))
        
        meterplot.setNeedlePaint(Color.darkGray)
        meterplot.setDialBackgroundPaint(Color.white)
        meterplot.setDialOutlinePaint(Color.white)
        meterplot.setDialShape(DialShape.CHORD)
        meterplot.setMeterAngle(260)
        meterplot.setTickLabelsVisible(true)        
        meterplot.setTickLabelPaint(Color.darkGray)
        meterplot.setTickSize(5D)        
        meterplot.setValuePaint(Color.black)        
        JFreeChart jfreechart = new JFreeChart("KPI: Rotación de Inventario", 
                                      JFreeChart.DEFAULT_TITLE_FONT, meterplot, true)
        
        /*
        def buffer = jfreechart.createBufferedImage(700, 400 )
        def bytes = EncoderUtil.encode(  buffer, 'png')
        def output = response.outputStream
        response.contentType = "image/png"         
        response.contentLength = bytes.length            
        output.write( bytes )
        output.flush()
        output.close()
        */
       
        def ext = "png";
        def separator = System.properties["file.separator"]
        def webRootDir = servletContext.getRealPath("/")                
        def dir = new File( "${webRootDir}${separator}charts" );
        if( !dir.exists() ){
            dir.mkdirs()
        }        
        def name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        def file = new File(dir, name)       
        
        ChartUtilities.saveChartAsPNG(file, jfreechart, 600, 400);        
        log.info "-- Indicador generado con exito..."
        
        [data: result, image:name ] 
    }
    
    
    
    def fillInvPrv(){
        def result = DiarioxProveedor.createCriteria().list(){
            if( params.bdg ){
                eq( "bodega.id", params.bdg as Long )                                                   
            }
            if( params.prv ){
                eq( "proveedor.id", params.prv as Long )                                                   
            }
            if( params.fechabegin != "" && params.fechaend != ""  ){    
                def desde = commonService.transformDate( params.fechabegin )
                def hasta = commonService.transformDate( params.fechaend )
                between("fecha", desde, hasta)
            }
            join 'proveedor'
            join 'bodega'
            order('bodega', 'asc')
            order('proveedor', 'asc')
            order('fecha', 'asc')
        }     
        
        result*.discard()
        
        if( params.query == "Exportar" ){
            
            response.contentType = 'application/vnd.ms-excel'
            response.setHeader("Content-disposition", "attachment; filename=stadistic.xls")
             def listFields = ["bodega", "proveedor", "fecha", "cantidad", "costo", "valor"]
            exportService.export('excel', response.outputStream, result, listFields, [:], [:], [:])
        }else{
            [listresult: result]
        }
    }
    
    def fillInventario(){
        
        def result = DiarioxBodega.createCriteria().list(){
            if( params.bdg ){
                eq( "bodega.id", params.bdg as Long )                                                   
            }
            if( params.fechabegin != "" && params.fechaend != ""  ){    
                def desde = commonService.transformDate( params.fechabegin )
                def hasta = commonService.transformDate( params.fechaend )
                between("fecha", desde, hasta)
            }
            join 'bodega'
            order('bodega', 'asc')
            order('fecha', 'asc')
        }     
        
        result*.discard()
        
        if( params.query == "Exportar" ){
            
            response.contentType = 'application/vnd.ms-excel'
            response.setHeader("Content-disposition", "attachment; filename=stadistic.xls")
            def listFields = ["bodega", "fecha", "cantidad", "costo", "valor"]
            exportService.export('excel', response.outputStream, result, listFields, [:], [:], [:])
        }else{
            [listresult: result]
        }
    }
    
    def fillInvArt(){
        
        def result = DiarioxArticulo.createCriteria().list(){
            if( params.bdg ){
                eq( "bodega.id", params.bdg as Long )                                                   
            }
            if( params.art ){
                eq( "articulo.id", params.art as Long )                                                   
            }
            if( params.fechabegin != "" && params.fechaend != ""  ){    
                def desde = commonService.transformDate( params.fechabegin )
                def hasta = commonService.transformDate( params.fechaend )
                between("fecha", desde, hasta)
            }
            join 'articulo'
            join 'bodega'
            order('bodega', 'asc')
            order('articulo', 'asc')
            order('fecha', 'asc')
        }     
        
        result*.discard()
        
        if( params.query == "Exportar" ){
            
            response.contentType = 'application/vnd.ms-excel'
            response.setHeader("Content-disposition", "attachment; filename=stadistic.xls")
            def listFields = ["bodega", "articulo", "fecha", "cantidad", "costo", "valor"]
            exportService.export('excel', response.outputStream, result, listFields, [:], [:], [:])
        }else{
            [listresult: result]
        }
    }
    
    
    def fillArticulo(){
        
        def wbdg = ( params.bdg ?  "and i.bodega.id = ${params.bdg}" : "" )
        def wart = ( params.art ?  "and i.articulo.id = ${params.art}" : "" )
        def fbgn = commonService.transformDate( params.fechabegin )
        def fend = commonService.transformDate( params.fechaend )
        
        def result = Inventario.executeQuery(
            "select  new map( articulo as articulo, proveedor as proveedor, sum( i.cantidad) as cantidad, sum( i.cantidad*i.valor) as valor, 0.0 as porcentaje ) " +
            "from Inventario i " +            
            " join i.articulo as articulo  " +
            " join i.articulo.tipoArticulo as tipoArticulo  " +
            " join i.articulo.claseArticulo as claseArticulo  " +
            " join i.articulo.genero as genero  " +
            " join i.articulo.cualidadesArticulo as cualidadesArticulo  " +
            " join i.proveedor as proveedor  " +
            " join i.movimiento as movimiento  " +               
            "where i.fecha between ? and ? and movimiento.codigo = ?   ${wbdg} ${wart} " +
            "group by i.proveedor, i.articulo " + 
            "order by 3 desc",   
            [ fbgn, fend, 'VTA']                 
        ) 
        
        // Calcula total de cantidad
        def sum = 0
        for( obj in result ){
            sum = sum + obj.cantidad
        }
        
        // Calcula procentaje
        def porc = 0.0d
        for( obj in result ){
            porc = (obj.cantidad * 100)/sum
            obj.porcentaje = new Double(porc).round(2)           
        }

        if( params.query == "Exportar" ){
            
            response.contentType = 'application/vnd.ms-excel'
            response.setHeader("Content-disposition", "attachment; filename=stadistic.xls")
            def listFields = ["articulo", "proveedor", "cantidad", "valor", "porcentaje"]
            exportService.export('excel', response.outputStream, result, listFields, [:], [:], [:])
            
        }else{
            [listart: result, sumcant:sum]
        }
    }
    
    def fillProveedor(){
        
        def wbdg = ( params.bdg ?  "and i.bodega.id = ${params.bdg}" : "" )
        def wprv = ( params.prv ?  "and i.proveedor.id = ${params.prv}" : "" )
        def fbgn = commonService.transformDate( params.fechabegin )
        def fend = commonService.transformDate( params.fechaend )
        
        def result = Inventario.executeQuery(
            "select new map( proveedor as proveedor, movimiento as movimiento, sum( i.cantidad) as cantidad, 0.0 as porcentaje) " +
                "from Inventario i " +           
                " join i.proveedor as proveedor  " +
                " join i.movimiento as movimiento  " +               
                "where i.fecha between ? and ?  ${wbdg} ${wprv}  " +
                "group by i.proveedor, i.movimiento " + 
                "order by proveedor.razonSocial, 3 desc " ,   
            [ fbgn, fend ]          
        ) 
        
        // Calcula total de cantidad
        def sum = 0
        for( obj in result ){
            sum = sum + obj.cantidad
        }
        
        // Calcula procentaje
        def porc = 0.0d
        for( obj in result ){
            porc = (obj.cantidad * 100)/sum
            obj.porcentaje = new Double(porc).round(2)           
        }
        
        if( params.query == "Exportar" ){
            
            response.contentType = 'application/vnd.ms-excel'
            response.setHeader("Content-disposition", "attachment; filename=stadistic.xls")
            def listFields = ["proveedor", "movimiento", "cantidad", "porcentaje"]
            exportService.export('excel', response.outputStream, result, listFields, [:], [:], [:])
            
        }else{
            [listart: result, sumcant: sum]    
        }
    }
    
    def fillMovimiento(){
        
        def wbdg = ( params.bdg ?  "and i.bodega.id = ${params.bdg}" : "" )
        def wmvt = ( params.mvt ?  "and i.movimiento.id = ${params.mvt}" : "" )
        def fbgn = commonService.transformDate( params.fechabegin )
        def fend = commonService.transformDate( params.fechaend )
        
        def result = Inventario.executeQuery(
            "select  new map( bodega as bodega, movimiento as movimiento, sum( i.cantidad) as cantidad, 0.0 as porcentaje ) " +
                "from Inventario i " +           
                " join i.bodega as bodega  " +
                " join i.movimiento as movimiento  " +               
                "where i.fecha between ? and ?  ${wbdg} ${wmvt}  " +
                "group by i.bodega, i.movimiento " + 
                "order by bodega, movimiento, 3 desc " ,   
            [ fbgn, fend ]                 
        ) 
        
        // Calcula total de cantidad
        def sum = 0
        for( obj in result ){
            sum = sum + obj.cantidad
        }
        
        // Calcula procentaje
        def porc = 0.0d
        for( obj in result ){
            porc = (obj.cantidad*100)/sum
            obj.porcentaje = new Double(porc).round(2)           
        }
        
        if( params.query == "Exportar" ){
            
            response.contentType = 'application/vnd.ms-excel'
            response.setHeader("Content-disposition", "attachment; filename=stadistic.xls")
            def listFields = ["bodega", "movimiento", "cantidad", "porcentaje"]
            exportService.export('excel', response.outputStream, result, listFields, [:], [:], [:])
            
        }else{
            [listart: result, sumcant:sum]   
        }
    }
    
    
    def fill(){
        
        if( params.type == 'Articulos' || params.type == 'Inv. x Articulo' || params.type == 'Rotacion Inventario x Articulo'){        
            def listart = Articulo.list( [cache:true] )
            def model = [ listart: listart]
            render( template:'cmbArticulos', model:model )
        }else{
            if( params.type == 'Proveedores' || params.type == 'Inv. x Proveedor' || params.type == 'Rotacion Inventario x Proveedor' ){
                 def listprv = commonService.listSuppliers()
                 def model = [ listprv: listprv]
                 render( template:'cmbProveedor', model:model )
            }else{
                def listmvt = Movimiento.listOrderByDetalle( [cache:true] )                
                def model = [ listmvt: listmvt] 
                render( template:'cmbMovimiento', model:model ) 
            }
        }
    }
    
    
}
