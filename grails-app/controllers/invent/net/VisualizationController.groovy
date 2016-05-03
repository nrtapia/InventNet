package invent.net

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  
import org.jfree.chart.ChartUtilities; 
  
import java.io.File;  
import java.io.FileOutputStream; 

import grails.converters.JSON;
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.chart.encoders.EncoderUtil
import org.jfree.chart.axis.CategoryLabelPositions
import java.awt.Color


class VisualizationController {
    
    def index = {

        use( groovy.time.TimeCategory){
                    
            def end = new Date()
            def begin = end - 12.months
            begin = begin.clearTime()
            end = end.clearTime()
            
            def rstvta = []
            def bodegaid = 0
            def title = "Ventas y Compras"

            if( !session["ventas_$params.bodegaid"] ){
                
                def sqlvta = "select new map( movimiento as mov, concat(year(i.fecha),'_',LPAD( month(i.fecha),2,'0')) as mes, sum(i.cantidad) as valor ) " +
                             "from Inventario i " +
                             "join i.movimiento as movimiento " +   
                             "where (movimiento.id = 2 or movimiento.id = 9) " +
                             "and (i.fecha between ? and ?) " 
                if( params.bodegaid ){
                    sqlvta    = sqlvta + "and i.bodega.id = ? "
                    bodegaid = params.bodegaid as Long 
                    def bodega = Bodega.read(bodegaid)
                    title = "${title} - ${bodega.nombre?.trim()}"
                    session["title_$params.bodegaid"]  = title 
                }
                sqlvta     = sqlvta + "group by 1,2"

                if( params.bodegaid ){
                    rstvta = Inventario.executeQuery( sqlvta, [ begin, end, bodegaid ] )   
                }else{
                    rstvta = Inventario.executeQuery( sqlvta, [ begin, end ] )   
                }
                session["ventas_$params.bodegaid"]  = rstvta     
            }else{
                rstvta = session["ventas_$params.bodegaid"] 
                title  = session["title_$params.bodegaid"] 
            }
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset()
            
            rstvta.each{
                dataset.addValue(  it.valor, it.mov.detalle?.trim(), it.mes )
            }
            
            

            JFreeChart chart = ChartFactory.createLineChart( title,  
                                                          "Mes",  "Cantidades",  
                dataset,  PlotOrientation.VERTICAL,  
                true, false, false );  

            def plot = chart.getCategoryPlot()
            plot.setBackgroundPaint(Color.white)
            plot.setDomainGridlinePaint(Color.lightGray)
            plot.setRangeGridlinePaint(Color.lightGray)
            
            def domainAxis = plot.getDomainAxis()
            domainAxis.setCategoryLabelPositions( CategoryLabelPositions.UP_45 )

            def buffer = chart.createBufferedImage(700, 400 )
            def bytes = EncoderUtil.encode(  buffer, 'png')
            def output = response.outputStream

            response.contentType = "image/png" 
            response.contentLength = bytes.length
            
            output.write( bytes )
            output.flush()
            output.close()
        }
        
    }
    
}
