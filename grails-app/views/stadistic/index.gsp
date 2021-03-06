
<%@ page import="invent.net.Bodega" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="Estadisticas" />
  <title>Estadisticas</title>
  <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" /> 

  <g:javascript library="jquery" />

</head>
<body>
<g:javascript src="jquery-ui-1.7.3.custom.min.js" />
<g:javascript src="util.js" />  
<g:javascript src="stadistic.js" />    

<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>    
</div>
<div class="body">
  <g:form name="frm-qry" action="query" >

    <table style="border: none; width: 750px;"> 
      <tr>
        <td style="vertical-align: middle"><h1>Estadisticas</h1></td>
        <td style="vertical-align: middle"> 
      <g:select name="option" from="${['Articulos','Proveedores','Movimientos', 'Inv. x Bodega', 'Inv. x Articulo', 'Inv. x Proveedor', 'Rotacion Inventario x Bodega', 'Rotacion Inventario x Proveedor', 'Rotacion Inventario x Articulo']}"
                onchange="${remoteFunction(action:'fill', update:'render-data', params:  '\'type=\'+this.value' )}"
                onblur="fillTitle(this)" /> 
      </td>
      <td style="width: 450px; vertical-align: middle">
        Desde <input type="text" name="fechabegin" id="fechabegin" size="10" /> &nbsp;&nbsp;
        Hasta  <input type="text" name="fechaend" id="fechaend" size="10" />
      </td>
      <td style="vertical-align: middle; width: 200px"> 
        <g:submitButton value="Consultar" name="query"/> 
        <g:submitButton value="Exportar" name="query"/>
      </td>
      </tr>
    </table>

    <g:if test="${flash.message}">
      <div class="message" style="color:red">${flash.message}</div>
    </g:if>

    <table>
      <tr>
        <th>Sucursales</th>
        <th><div id="title-head">Articulos</div></th>        
      </tr>
      <tr>
        <td style="width: inherit">
      <g:select name="bdg" from="${lstbdg}" size="30"
                style="width: 238px;"  optionKey="id" 
                noSelection="${['': 'Todas']}" />          
      </td>
      <td style="width: inherit"> 
        <div id="render-data">
          <g:render template="cmbArticulos"  />          
        </div>
      </td>
      </tr>
    </table>    

  </g:form>

</div>
</body>
</html>
