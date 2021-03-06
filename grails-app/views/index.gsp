<% def commonService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("commonService") %>

<html>
  <head>
    <title>InventNet</title>
    <meta name="layout" content="main" />
    <style type="text/css" media="screen">

      #nav {
        margin-top:20px;
        margin-left:30px;
        width:228px;
        float:left;

      }
      .homePagePanel * {
        margin:0px;
      }
      .homePagePanel .panelBody ul {
        list-style-type:none;
        margin-bottom:10px;
      }
      .homePagePanel .panelBody h1 {
        text-transform:uppercase;
        font-size:1.1em;
        margin-bottom:10px;
      }
      .homePagePanel .panelBody {
        background: url(images/leftnav_midstretch.png) repeat-y top;
        margin:0px;
        padding:15px;
      }
      .homePagePanel .panelBtm {
        background: url(images/leftnav_btm.png) no-repeat top;
        height:20px;
        margin:0px;
      }

      .homePagePanel .panelTop {
        background: url(images/leftnav_top.png) no-repeat top;
        height:11px;
        margin:0px;
      }
      h2 {
        margin-top:15px;
        margin-bottom:15px;
        font-size:1.2em;
      }
      #pageBody {
        margin-left:280px;
        margin-right:20px;
      }
    </style>

    <g:javascript library="jquery" />
    <r:require module="toats"/>


    <g:javascript>
    function genChart(){
      var bdg = $("#bodegaid").val();
      var url = "${createLink(controller: 'visualization', action: 'index')}?bodegaid="+bdg;
      buildWindown( url );
    }
    </g:javascript>
    
  </head>
    
  <body>


    
    <div id="nav">
      <div class="homePagePanel">
        <div class="panelTop"></div>
        <div class="panelBody">
          <h1>Administrativo</h1>
          <ul>
            <li>Usuario: <sec:username/> </li>                        
            <li>App version: <g:meta name="app.version"></g:meta></li>            
            <li><BR/></li>
            <li><g:link controller="cierreDiario" action="list" title="Cierre Diario">Cierre Diario</g:link></li>

            <sec:ifAnyGranted roles="ROLE_ADMIN"> 
              <li><g:link controller="autorizaCierre" action="list" title="Autorización Cierre">Autorizaci&oacute;n Cierre</g:link></li>
            </sec:ifAnyGranted>
			
			<li><g:link controller="stadisticUsers" action="index" title="Rotacion">Rotaci&oacuten</g:link></li>
            
            <li><BR/></li>
            <li>
            <sec:ifLoggedIn>
              <g:link controller="logout">
                Cerrar Sesi&oacute;n
              </g:link>
            </sec:ifLoggedIn>  
            </li>
			
          </ul>
          
          
          
          <sec:ifAnyGranted roles="ROLE_ADMIN">            
            <h1>Tablas Maestras</h1>
            <ul>
              <li><g:link controller="claseArticulo" action="list" title="Clase Articulo">Clase Articulo</g:link></li>
              <li><g:link controller="ciudad" action="list" title="Ciudades">Ciudades</g:link></li>
              <li><g:link controller="cualidadesArticulo" action="list" title="Cualidades">Cualidades Articulo</g:link></li>
              <li><g:link controller="genero" action="list" title="Generos">Generos</g:link></li>            
              <li><g:link controller="movimiento" action="list" title="Movimientos">Movimientos</g:link></li>
              <li><g:link controller="tipoArticulo" action="list" title="Tipo Articulo">Tipo Articulo</g:link></li>
              <li><g:link controller="tipoInperfecto" action="list" title="Tipos Inperfecto">Tipos Inperfecto</g:link></li>
              <li><g:link controller="tipoDocumento" action="list" title="Tipos de Documentos">Tipos de Documentos</g:link></li>
              
            </ul>
          </sec:ifAnyGranted>
          
          <sec:ifAnyGranted roles="ROLE_CONSULTA">            
            <h1>Base</h1>
            <ul>
              <li><g:link controller="query" action="index" title="Consultas">Consultas</g:link></li>
              
            </ul>
          </sec:ifAnyGranted>
          
          
          
        </div>
        <div class="panelBtm">
        </div>
      </div>
    </div>
    <div id="pageBody">
      <h1>Bienvenido a la Gestión de Inventario</h1>
      <br/>
 
<sec:ifAnyGranted roles="ROLE_SUCURSAL">      
      <table id="Table_01" cellpadding="0" cellspacing="0"  style="width: 501px; height: 194; border-width: 0px">
        <tr>
          <td colspan="10" style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'paneluser_01.jpg')}"  alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'spacer.gif')}" alt=""></td>
        </tr>
        <tr>
          <td rowspan="3" style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'paneluser_02.jpg')}"  alt=""></td>
          <td rowspan="2" style="padding: 0px">
        <g:link controller="catalogo" action="list" title="Catalogo de Proveedores">
          <img src="${resource(dir:'images/panel',file:'paneluser_03.gif')}"  border="0" alt="">
        </g:link> 
        </td>            
        <td rowspan="3" style="padding: 0px">
          <img src="${resource(dir:'images/panel',file:'paneluser_04.jpg')}" alt=""></td>
        <td rowspan="2" style="padding: 0px">
        <g:link controller="entrada" action="list" title="Entradas">
          <img src="${resource(dir:'images/panel',file:'paneluser_05.gif')}"alt="" border="0">
        </g:link>
        </td>
        <td rowspan="3" style="padding: 0px">          
          <img src="${resource(dir:'images/panel',file:'paneluser_06.jpg')}" alt=""></td>          
        <td style="padding: 0px">
        <g:link controller="${salidactr}" action="${salidaact}" title="Salidas">
          <img src="${resource(dir:'images/panel',file:'paneluser_08.jpg')}" alt="" border="0">
        </g:link>
        </td>
        <td rowspan="3" style="padding: 0px">
          <img src="${resource(dir:'images/panel',file:'paneluser_08-09.jpg')}" width="20" height="156" alt=""></td>
        <td style="padding: 0px">
          <g:link controller="ajustes" action="list" title="Ajustes">
            <img src="${resource(dir:'images/panel',file:'paneluser_07.gif')}" width="77" height="105" alt="" border="0"></td>
          </g:link>
        <td rowspan="3" style="padding: 0px">
          <img src="${resource(dir:'images/panel',file:'paneluser_10.jpg')}" width="20" height="156" alt=""></td>
        <td style="padding: 0px">
          <g:link controller="query" action="index" title="Consultas">
            <img src="${resource(dir:'images/panel',file:'paneluser_09.jpg')}" width="77" height="105" alt="" border="0">
          </g:link>          
        </td>        
        <td style="padding: 0px">
          <img src="${resource(dir:'images/panel',file:'spacer.gif')}" width="1" height="105" alt=""></td>
        </tr>
        <tr>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'paneluser_12.jpg')}" width="77" height="51" alt=""></td>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'paneluser_13.jpg')}" width="77" height="51" alt=""></td>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'paneluser_14.jpg')}" width="77" height="51" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'spacer.gif')}" width="1" height="1" alt=""></td>
        </tr>
        <tr>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'paneluser_15.jpg')}" width="77" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'paneluser_16.jpg')}" width="77" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel',file:'spacer.gif')}" width="1" height="50" alt=""></td>
        </tr>
      </table>
  
      <table id="Table_01" style="width: 501px; height: 194; border-width: 0px" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan="10" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_01.gif')}" width="500" height="38" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'spacer.gif')}" width="1" height="38" alt=""></td>
        </tr>
        <tr>
          <td rowspan="3" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_02.gif')}" width="35" height="156" alt=""></td>
          <td rowspan="2" style="padding: 0px">
            <g:link controller="inperfectos" action="list" title="Inperfectos">
              <img src="${resource(dir:'images/panel2', file:'paneluser_03.gif')}" width="77" height="106" alt="" border="0">
            </g:link>
          </td>
          <td rowspan="3" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_04.gif')}" width="20" height="156" alt=""></td>
          <td rowspan="2" style="padding: 0px">
            <g:link controller="subas" action="list" title="Subas/Bajas">
              <img src="${resource(dir:'images/panel2', file:'paneluser-2_05.gif')}" width="77" height="106" alt="" border="0">
            </g:link>
          </td>
          <td rowspan="3" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_06.gif')}" width="20" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="compras" action="index" title="Compras">
              <img src="${resource(dir:'images/panel2', file:'paneluser_08.jpg')}" width="77" height="105" alt="" border="0">
            </g:link>
          </td>          
          <td rowspan="3" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_08.gif')}" width="20" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="devolucion" action="list" title="Devoluciones">
              <img src="${resource(dir:'images/panel2', file:'paneluser_07.jpg')}" width="77" height="105" alt="" border="0">
            </g:link>          
          </td>
          <td rowspan="3" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_10.gif')}" width="14" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="changePassword" action="index" title="Cambiar contraseña">
              <img src="${resource(dir:'images/panel2', file:'paneluser_09.jpg')}" width="83" height="105" alt="" border="0">
            </g:link>
          </td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'spacer.gif')}" width="1" height="105" alt=""></td>
        </tr>
        <tr>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_12.gif')}" width="77" height="51" alt=""></td>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_13.gif')}" width="77" height="51" alt=""></td>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_14.gif')}" width="83" height="51" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'spacer.gif')}" width="1" height="1" alt=""></td>
        </tr>
        <tr>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_15.gif')}" width="77" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'paneluser-2_16.gif')}" width="77" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel2', file:'spacer.gif')}" width="1" height="50" alt=""></td>
        </tr>
      </table>  
  
</sec:ifAnyGranted>
      
<sec:ifAnyGranted roles="ROLE_ADMIN">
      <table id="Table_02"  style="width: 698px; height: 194; border-width: 0px" 
             width="698" height="194" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan="15" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_01.gif')}" width="698" height="38" alt=""></td>
        </tr>
        <tr>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_02.gif')}" width="38" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="proveedor" action="list" title="Registro de proveedores">
              <img src="${resource(dir:'images/panel-adm',file:'Panel_03.gif')}" width="90" height="106" alt="" border="0">
            </g:link>
          </td>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_04.gif')}" width="7" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="articulo" action="list" title="Registro de articulos">
              <img src="${resource(dir:'images/panel-adm',file:'Panel_05.gif')}" width="75" height="106" alt="" border="0">
            </g:link>
          </td>            
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_06.gif')}" width="8" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="bodega" action="list" title="Registro de sucursales">
              <img src="${resource(dir:'images/panel-adm',file:'Panel_07.gif')}" width="83" height="106" alt="" border="0">
            </g:link>
          </td>
          <td rowspan="2"  style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_08.gif')}" width="6" height="156" alt=""></td>
          <td style="padding: 0px">
         
         <g:link controller="user" title="Registro de usuarios">
              <img src="${resource(dir:'images/panel-adm',file:'Panel_09.gif')}" width="76" height="106" alt="" border="0">
            </g:link>
          </td>              
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_10.gif')}" width="3" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="stadistic" action="index" title="Estadisticas">
              <img src="${resource(dir:'images/panel-adm',file:'Panel_11.gif')}" width="89" height="106" alt="" border="0"></td>
            </g:link>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_12.gif')}" width="7" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="query" action="index" title="Consultas">
              <img src="${resource(dir:'images/panel-adm',file:'Panel_13.gif')}" width="81" height="106" alt="" border="0">
            </g:link>
          </td>          
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_14.gif')}" width="7" height="156" alt=""></td>
          <td style="padding: 0px">
            <g:link controller="changePassword" action="index" title="Cambiar contraseña">
              <img src="${resource(dir:'images/panel-adm',file:'Panel_15.jpg')}" width="89" height="106" alt="" border="0">
            </g:link>            
          </td>
          <td rowspan="2" style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_16.gif')}" width="39" height="156" alt=""></td>
        </tr>
        <tr>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_17.gif')}" width="90" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_18.gif')}" width="75" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_19.gif')}" width="83" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_20.gif')}" width="76" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_21.gif')}" width="89" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_22.gif')}" width="81" height="50" alt=""></td>
          <td style="padding: 0px">
            <img src="${resource(dir:'images/panel-adm',file:'Panel_23.gif')}" width="89" height="50" alt=""></td>
        </tr>
      </table>
  </sec:ifAnyGranted>
      
      <br/>
      <div id="form-chart">
        <form>
          <span class="label-custom">Sucursal : </span> &nbsp; 
          <g:select name="bodegaid" from="${commonService.listWarehouses(0)}" 
                    optionKey="id"  noSelection="${['':'Todas']}"  />
          
          <a href="javascript:void(0)" onclick="genChart()">Graficar</a>
        </form>
      </div>
       <br/>

    </div>

  <g:javascript src="jquery.toastmessage.js" />
  <g:javascript src="util.js" />
  <g:javascript src="index.js" />

  </body>
</html>
