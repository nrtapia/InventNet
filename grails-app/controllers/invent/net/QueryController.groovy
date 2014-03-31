package invent.net

import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import java.text.SimpleDateFormat

class QueryController {
    
    def commonService
    def catalogoService
    def movimientosService
    def exportService
    def jasperService
    
    def index = {
        
        def listqry = []
        listqry.add( [key:"CTLGNR", value:"Catalogo por Genero"] )
        listqry.add( [key:"MTODRO", value:"Movimiento Diario"] )
        listqry.add( [key:"LSTART", value:"Listado de Articulos"] )
        listqry.add( [key:"CTLPRV", value:"Catalogo por Proveedor"] )
        listqry.add( [key:"LSRPRV", value:"Lista de Proveedores"] )
        listqry.add( [key:"LSTSBA", value:"Subas/Rebajas"] )
        listqry.add( [key:"LSTDVL", value:"Devoluciones"] )
        listqry.add( [key:"FZAVTA", value:"Fuerza de Ventas"] )
        listqry.add( [key:"CTLGN2", value:"Catalogo por Genero Articulo"] )
                
        def listprv = commonService.listSuppliers()
        def listbdg = commonService.listWarehouses(1)
        def listmvn = Movimiento.findAll("from Movimiento as m order by m.abrevia, m.detalle", [cache:true])
        
        [listPrv:listprv, listBdg:listbdg, listMov:listmvn, listQry:listqry]
    }
    
    def print = {
        log.info "-- Impimiendo reporte : ${params}"
        def listResp = []
        def listFields = []
        def doprint = true
        
        log.info "-- Tipo de consulta [${params.query}]"
        
        if( params.bodegaid == "" ){
            flash.message = "Por favor, filtrar los datos por bodega"
            doprint = false
            redirect(action: "index")                    
        }else{
                
            switch ( params.query ) {
                case 'LSTSBA':
                    log.info '-- Generando datos para lista de subas'
                    if( params.option != 'Consultar' ){
                        listResp = Subas.createCriteria().list(){
                            eq( "bodega.id", params.bodegaid as Long )                                                    
                            if( params.fechabegin != "" && params.fechaend != ""  ){    
                                def desde = commonService.transformDate( params.fechabegin )
                                def hasta = commonService.transformDate( params.fechaend )
                                between("fecha", desde, hasta)
                            }
                            join 'articulo'
                            join 'proveedor'
                            join 'bodega'
                        }                     
                    }
                    params.put( "_file","subas" )      
                    listFields = ["bodega", "proveedor.codigo", "proveedor.razonSocial", 
                                "articulo.codigo", "articulo.tipoArticulo", "articulo.genero", "articulo.claseArticulo", 
                                "articulo.cualidadesArticulo", "articulo.referencia", "articulo.descripcion", 
                                "fecha", "tipo", "valorAnterior", "valor" ]
                
                    if( params.fechabegin != "" && params.fechaend != ""  ){    
                        def desde = commonService.transformDate( params.fechabegin )
                        def hasta = commonService.transformDate( params.fechaend )
                        def sdfmt = new SimpleDateFormat("yyyy-MM-dd") 
                        params.put( "fechabegin", sdfmt.format(desde) )       
                        params.put( "fechaend", sdfmt.format(hasta) )       
                    }
                                
                    break   
                    
                case 'LSTDVL':
                    log.info '-- Generando datos para lista de devoluciones'
                    if( params.option != 'Consultar' ){
                        listResp = Devolucion.createCriteria().list(){
                            eq( "bodega.id", params.bodegaid as Long )                                                    
                            if( params.fechabegin != "" && params.fechaend != ""  ){    
                                def desde = commonService.transformDate( params.fechabegin )
                                def hasta = commonService.transformDate( params.fechaend )
                                between("fecha", desde, hasta)
                            }
                            join 'articulo'
                            join 'proveedor'
                            join 'bodega'
                        }                     
                    } 
                    params.put( "_file","devolucion" )      
                    listFields = ["bodega", "proveedor.codigo", "proveedor.razonSocial", 
                                "articulo.codigo", "articulo.tipoArticulo", "articulo.genero", "articulo.claseArticulo", 
                                "articulo.cualidadesArticulo", "articulo.referencia", "articulo.descripcion", 
                                "fecha", "estado", "costo", "valor", "cantidad", "canting", "detalle" ]
                
                     if( params.fechabegin != "" && params.fechaend != ""  ){    
                        def desde = commonService.transformDate( params.fechabegin )
                        def hasta = commonService.transformDate( params.fechaend )
                        def sdfmt = new SimpleDateFormat("yyyy-MM-dd") 
                        params.put( "fechabegin", sdfmt.format(desde) )       
                        params.put( "fechaend", sdfmt.format(hasta) )       
                    }
                                
                    break  
                
                // Lista de proveedores
                case 'LSRPRV':
                    log.info '-- Generando datos para lista de proveedores'
                    if( params.option != 'Consultar' ){
                        listResp = Proveedor.listOrderByRazonSocial()
                        listResp*.discard()
                    }
                    params.put( "_file","proveedores-sql" )      
                    listFields = ["codigo", "razonSocial", "nit", "direccion", "telefono", "ciudad.nombre"]

                    break      
                case 'FZAVTA':
                    log.info '-- Generando datos para fuerza de ventas'
                    
                    def desde = commonService.transformDate( params.fechabegin )
                    def hasta = commonService.transformDate( params.fechaend )
                    
                    if( params.option != 'Consultar' ){
     
                        listResp = Inventario.createCriteria().list(){                        
                            eq( "bodega.id", params.bodegaid as Long )                        
                            between("fecha", desde, hasta)
                            join 'bodega'
                            join 'proveedor'
                            join 'movimiento'
                            order('bodega', 'asc')                        
                            order('ref', 'asc')  
                            order('fecha', 'asc')  
                        }            
                        listResp*.discard()
                    }
   
                    def sdfmt = new SimpleDateFormat("yyyy-MM-dd") 
                    params.put( "fechabegin", sdfmt.format(desde) )       
                    params.put( "fechaend", sdfmt.format(hasta) )  
                
                    params.put( "_file","fuerzaVenta" )      
                    listFields = ["bodega.nombre", "ref", "fecha", "cantidad", "valor"]

                    break

                case 'LSTART':
                    log.info '-- Generando datos para lista de articulos'

                    listResp = Articulo.createCriteria().list(){
                        order('tipoArticulo', 'asc')
                        order('genero', 'asc')
                        order('claseArticulo', 'asc')
                        order('cualidadesArticulo', 'asc')
                    }            
                    listResp*.discard()

                    params.put( "_file","articulos" )
                    listFields = ["tipoArticulo", "codigo", "genero", "claseArticulo", "cualidadesArticulo", "referencia", "descripcion"]

                    break

                case 'CTLPRV' : 
                    log.info '-- Generando datos para catalogo de proveedores'

                    if( params.option != 'Consultar' ){                
                        listResp = Catalogo.createCriteria().list(){
                            eq( "bodega.id", params.bodegaid as Long )
                            if(  params.proveedorid != "" ){
                                eq( "proveedor.id", params.proveedorid as Long )
                            }
                            join 'articulo'
                            join 'proveedor'
                            join 'bodega'
                            order('bodega', 'asc')
                            order('proveedor', 'asc')
                            order('articulo', 'asc')
                            
                        }            
                        listResp*.discard()
                    }

                    params.put( "_file","catalogo-prv" )                
                    listFields = ["bodega", "proveedor.codigo", "proveedor.razonSocial", "articulo.tipoArticulo", 
                                "articulo.codigo", "articulo.genero", "articulo.claseArticulo", 
                                "articulo.cualidadesArticulo", "articulo.referencia", "articulo.descripcion", 
                                "cantidad", "costo" , "valor"  ]

                break

                case 'CTLGNR' : 
                    log.info '-- Generando datos para catalogo por genero'
                    
                    if( params.option != 'Consultar' ){                
                        listResp = Catalogo.createCriteria().list(){
                            eq( "bodega.id", params.bodegaid as Long )
                            if(  params.proveedorid != "" ){
                                eq( "proveedor.id", params.proveedorid as Long )
                            }
                            join 'articulo'
                            join 'proveedor'
                            join 'bodega'
                            order('bodega', 'asc')
                            order('proveedor', 'asc')
                            order('articulo', 'asc')                                                 
                        }            
                        listResp*.discard()
                    }

                    params.put( "_file","catalogo-gnr" )                
                    listFields = ["bodega", "articulo.genero", "proveedor.codigo", "proveedor.razonSocial", "articulo.tipoArticulo", 
                                "articulo.codigo", "articulo.claseArticulo", 
                                "articulo.cualidadesArticulo", "articulo.referencia", "articulo.descripcion", 
                                "cantidad", "costo" , "valor"  ]
                  

                break
                
                case 'CTLGN2' : 
                    log.info '-- Generando datos para catalogo por genero 2'
                    
                    if( params.option != 'Consultar' ){                
                        listResp = Catalogo.createCriteria().list(){
                            eq( "bodega.id", params.bodegaid as Long )
                            if(  params.proveedorid != "" ){
                                eq( "proveedor.id", params.proveedorid as Long )
                            }
                            join 'articulo'
                            join 'proveedor'
                            join 'bodega'
                            order('bodega', 'asc')
                            order('articulo', 'asc')                                                 
                            order('proveedor', 'asc')
                        }            
                        listResp*.discard()
                    }

                    params.put( "_file","catalogo-gnr-2" )                
                    listFields = ["bodega", "articulo.genero", "proveedor.codigo", "proveedor.razonSocial", "articulo.tipoArticulo", 
                                "articulo.codigo", "articulo.claseArticulo", 
                                "articulo.cualidadesArticulo", "articulo.referencia", "articulo.descripcion", 
                                "cantidad", "costo" , "valor"  ]
                  

                break

                case 'MTODRO' : 
                    log.info '-- Generando datos para movimientos de inventario'

                    if(  params.fechabegin == "" || params.fechaend == ""  ){
                        flash.message = "Por favor, minimo filtrar por fechas"
                        doprint = false
                        redirect(action: "index")                    
                    }            

                    if( params.option != 'Consultar' ){                
                        listResp = Inventario.createCriteria().list(){                        
                            eq( "bodega.id", params.bodegaid as Long )                        
                            if( params.proveedorid != "" ){
                                eq( "proveedor.id", params.proveedorid as Long )
                            }
                            if( params.movetypeid != "" ){
                                eq( "movimiento.id", params.movetypeid as Long )
                            }
                            if( params.fechabegin != "" && params.fechaend != ""  ){    
                                def desde = commonService.transformDate( params.fechabegin )
                                def hasta = commonService.transformDate( params.fechaend )
                                between("fecha", desde, hasta)
                            }
                            join 'articulo'
                            join 'proveedor'
                            join 'movimiento'
                            order('bodega.id', 'asc')                        
                            order('id', 'asc')                          
                        }            
                        listResp*.discard()
                    }else{
                        if( params.fechabegin != "" && params.fechaend != ""  ){    
                            def desde = commonService.transformDate( params.fechabegin )
                            def hasta = commonService.transformDate( params.fechaend )
                            def sdfmt = new SimpleDateFormat("yyyy-MM-dd") 
                            params.put( "fechabegin", sdfmt.format(desde) )       
                            params.put( "fechaend", sdfmt.format(hasta) )       
                        }
                    }

                    params.put( "_file","movimiento" )                
                    listFields = ["bodega", "proveedor.codigo", "proveedor.razonSocial", 
                                "articulo.codigo", "articulo.tipoArticulo", "articulo.genero", "articulo.claseArticulo", 
                                "articulo.cualidadesArticulo", "articulo.referencia", "articulo.descripcion", 
                                "fecha", "movimiento", "tipo", "cantidad",  "costo" , "valor", "ref"  ]

                break

            }

            if( doprint ){

                switch ( params.option ) {
                    case "Consultar":            
                        params.put( "_format","PDF" )
                        params.put( "_name","report" )                                                
                        chain(controller:'jasper',action:'index', params:params,  model: [data: listResp])
                    break

                    case "Exportar":

                        response.contentType = 'application/vnd.ms-excel'
                        response.setHeader("Content-disposition", "attachment; filename=export.xls")

                        exportService.export('excel', response.outputStream, listResp, listFields, [:], [:], [:])

                    break
                }
            }
        }       
        
    }
}
