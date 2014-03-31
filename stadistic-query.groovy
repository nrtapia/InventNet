import invent.net.*

def begin = new Date().parse('yyyy-MM-dd','2012-06-01')
def end = new Date().parse('yyyy-MM-dd','2012-06-15')

def result = Inventario.executeQuery("select proveedor, movimiento, sum( i.cantidad) as cant, 0.0 as porc " +
                "from Inventario i " +           
                " join i.proveedor as proveedor  " +
                " join i.movimiento as movimiento  " +               
                "where i.bodega.id = ? and i.fecha between ? and ? " +
                "group by i.proveedor, i.movimiento " + 
                "order by i.movimiento.detalle, cant desc",   
                [ 10 as Long, begin, end]                 
            ) 
println "--> ${result[0]}"