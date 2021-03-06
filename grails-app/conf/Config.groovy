// 
// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

/*
def extConfig = System.getenv("INVENTNET_EXT_CONFIG")
println ">>>> Buscanco configuracion en ${extConfig}"
if(extConfig) {
    grails.config.locations << "file:" + extConfig
    println ">>> Cargando archivo: ${extConfig}"
}
*/

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [  html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text-plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      pdf: 'application/pdf',
                      rtf: 'application/rtf',
                      excel: 'application/vnd.ms-excel',
                      ods: 'application/vnd.oasis.opendocument.spreadsheet',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
//grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
  
    development {
        grails.logging.jul.usebridge = true
        //jasper.dir.reports="C:\\Users\\Neider\\Documents\\NetBeansProjects\\InventNet\\report\\"
        //jasper.dir.reports="C:\\Users\\ntapia\\Documents\\NetBeansProjects\\InventNet\\report\\"
        jasper.dir.reports=System.getenv("INVENTNET_REPORTS")
    }
    production {
        grails.logging.jul.usebridge = false       
        //jasper.dir.reports="/var/lib/tomcat6/report/"
        //grails.serverURL = "http://localhost:8081/${appName}"
        jasper.dir.reports=System.getenv("INVENTNET_REPORTS")
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
    
    appenders {
        //console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')        
        console name:'stdout', layout:pattern(conversionPattern: '[%-7p][%d{dd/MM/yyyy HH:mm:ss,SSS}] %C %m%n')
    }
    
    //error stdout: "StackTrace"

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
    
    root{
        //info "grails.app"
        info  'grails.app.domain.invent.net.Inventario',
          'grails.app.controller.invent.net.CatalogoController',
          'grails.app.controller.invent.net.InperfectosController',
          'grails.app.controller.invent.net.EntradaController',
          'grails.app.controller.invent.net.QueryController',
          'grails.app.controller.invent.net.SalidaController',
          'org.codehaus.groovy.grails.web.servlet',  //  controllers,
          'grails.app.service.com.invent.services.CatalogoService',
          'grails.app.service.com.invent.services.CommonService',
          'grails.app.service.com.invent.services.MovimientosService', 
          'grails.app.controller.org.codehaus.groovy.grails.plugins.jasper.JasperController',  
          'grails.app.service.org.codehaus.groovy.grails.plugins.jasper.JasperService'
    }
    

    
          
}


grails.plugins.springsecurity.rejectIfNoRule=true

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.inventnet.security.SecUser'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.inventnet.security.SecUserSecRole'
grails.plugins.springsecurity.authority.className = 'com.inventnet.security.SecRole'
grails.plugins.springsecurity.securityConfigType = 'InterceptUrlMap'

//grails.plugins.springsecurity.auth.loginFormUrl = '/loginForm.gsp'


grails.plugins.springsecurity.interceptUrlMap = [ 
    
    
    '/register/**':          ['ROLE_ADMIN'],
    '/requestmap/**':          ['ROLE_ADMIN'],
    
    '/securityInfo/**':          ['ROLE_ADMIN'],
    '/user/**':                  ['ROLE_ADMIN'],
    '/role/**':                  ['ROLE_ADMIN'],
    '/registrationCode/**':      ['ROLE_ADMIN'],
    '/secUser/**':               ['ROLE_ADMIN'],
    
    '/adminManage/**' :          ['ROLE_ADMIN'],
    
    '/articulo/**' :             ['ROLE_ADMIN'],
    '/bodega/**' :               ['ROLE_ADMIN'],
    '/claseArticulo/**' :        ['ROLE_ADMIN'],
    '/cualidadesArticulo/**' :   ['ROLE_ADMIN'],
    '/ciudad/**' :               ['ROLE_ADMIN'],
    '/genero/**' :               ['ROLE_ADMIN'],
    '/proveedor/**' :            ['ROLE_ADMIN'],
    '/referencia/**' :           ['ROLE_ADMIN'],
    '/tipoArticulo/**' :         ['ROLE_ADMIN'],
    '/tipoInperfecto/**' :       ['ROLE_ADMIN'],
    '/inventario/**' :           ['ROLE_ADMIN'],
    '/movimiento/**' :           ['ROLE_ADMIN'],
    '/cierreDiario/list/*' :     ['ROLE_ADMIN'],
    '/query/**' :                ['ROLE_ADMIN', 'ROLE_SUCURSAL', 'ROLE_CONSULTA'],
    '/autorizaCierre/**' :       ['ROLE_ADMIN'],
    
    '/ajustes/**' :              ['ROLE_SUCURSAL'],
    '/catalogo/**' :             ['ROLE_SUCURSAL','ROLE_CATALOGO'],
    '/entrada/**' :              ['ROLE_SUCURSAL'],    
    '/salida/**' :               ['ROLE_SUCURSAL'],
    '/inperfectos/**' :          ['ROLE_SUCURSAL'],
    '/subas/**' :                ['ROLE_SUCURSAL'],
    '/compras/**' :              ['ROLE_SUCURSAL'],
    '/devolucion/**' :           ['ROLE_SUCURSAL'],
    '/cierreDiario/**' :         ['ROLE_SUCURSAL'],
    '/documento/**' :            ['ROLE_ADMIN', 'ROLE_SUCURSAL'],
    '/tipoDocumento/**' :        ['ROLE_ADMIN'],

        
    '/login/**' :               ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/login/**' :               ['IS_AUTHENTICATED_ANONYMOUSLY'],
        
    '/css/**' :                 ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/images/**' :              ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/js/**' :                  ['IS_AUTHENTICATED_ANONYMOUSLY'],
    
    '/**' :                     ['IS_AUTHENTICATED_FULLY']
]

grails.plugins.dynamicController.mixins = [
   'com.burtbeckwith.grails.plugins.appinfo.IndexControllerMixin':       'com.burtbeckwith.appinfo_test.AdminManageController',
   'com.burtbeckwith.grails.plugins.appinfo.HibernateControllerMixin':   'com.burtbeckwith.appinfo_test.AdminManageController',
   'com.burtbeckwith.grails.plugins.appinfo.Log4jControllerMixin' :      'com.burtbeckwith.appinfo_test.AdminManageController',
   'com.burtbeckwith.grails.plugins.appinfo.SpringControllerMixin' :     'com.burtbeckwith.appinfo_test.AdminManageController',
   'com.burtbeckwith.grails.plugins.appinfo.MemoryControllerMixin' :     'com.burtbeckwith.appinfo_test.AdminManageController',
   'com.burtbeckwith.grails.plugins.appinfo.PropertiesControllerMixin' : 'com.burtbeckwith.appinfo_test.AdminManageController',
   'com.burtbeckwith.grails.plugins.appinfo.ScopesControllerMixin' :     'com.burtbeckwith.appinfo_test.AdminManageController'
]



/*
// Seguridad HTTPS
grails.plugins.springsecurity.secureChannel.useHeaderCheckChannelSecurity = true
grails.plugins.springsecurity.secureChannel.definition = [
    '/**' : 'REQUIRES_SECURE_CHANNEL'
]
*/


// Google Analityc
google.analytics.enabled = true
google.analytics.webPropertyID = "UA-30938114-1"

grails.param.stock=10

// Controlador para las Salidas
grails.param.out.controller = "salida"
grails.param.out.action = "small"

//grails.param.out.controller = "documento"
//grails.param.out.action = "index"
