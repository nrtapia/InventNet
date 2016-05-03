<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'catalogo.label', default: 'Catalogo')}" />
  <title>Salida</title>

  <link rel="stylesheet" href="${resource(dir:'css', file: 'salida.css')}" />  
  <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" />  
  
  <g:javascript library="prototype" />
  <g:javascript src="jquery-1.7.1.min.js" />
  <g:javascript src="jquery-ui-1.7.3.custom.min.js" />  
  <g:javascript src="salida.js" />  
  <g:javascript src="util.js" />  

</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>            
  </div>
  <div class="body">
    <h1>Salidas</h1>
    <g:if test="${flash.message}">
      <div id="message" class="message">${flash.message}</div>
    </g:if>
  </div>
  
  <form id="formCatalogo">
    
  <div id="bodega">
    &nbsp; 
    <g:select name="bodegaid" from="${listBdg}"  style="margin-top: 6px"
              optionKey="id"  />
    &nbsp; &nbsp;            

    <input type="button" id="button-add" value="Agregar" class="find-icon" onclick="selectPrv()" />
  </div>  
    

  <div id="fecha">
      <span class="label-custom">Fecha : </span>    
      <input type="text" name="fechavalue" id="fechaid" size="10"/>    
  </div>
  
  <div id="movimiento">
    <span class="label-custom">Movimiento : </span>
    <g:select name="movetypeid" from="${listMov}"  optionKey="id" value=""  />      
  </div>
  
  <div id="proveedores"> 
    <g:select name="proveedorid" from="${listPrv}"  multiple="false"
              optionKey="id" value="${requisicionInstance?.proveedor?.id}"  
              style="width: 238px; height: 384px" />
  </div>
  
  <div id="articulos-salida">   
    <div class="list">
      
      <table id="listArt">      
          <tr>            
            <th><g:message code="proveedor.razon.label" default="Prov." /></th>
            <th><g:message code="articulo.codigo.label" default="Código" /></th>                      
            <th><g:message code="articulo.tipoArticulo.label" default="Tipo" /></th>            
            <th><g:message code="articulo.genero.label" default="Genero" /></th>      
            <th><g:message code="articulo.claseArticulo.label" default="Clase" /></th>
            <th><g:message code="articulo.cualidadesArticulo.label" default="Cualidades" /></th>      
            <th><g:message code="articulo.ref.label" default="Ref." /></th>      
            <th><g:message code="articulo.descripcion.label" default="Descripción" /></th>      
            <th><g:message code="catalogo.valor.label" default="Costo" /></th>      
            <th><g:message code="catalogo.valor.label" default="Valor" /></th>      
            <th><g:message code="catalogo.cant.label" default="Cant." /></th>                      
        </tr>        
      </table>
    </div>
  </div>

  <div id="footer">
    <input type="button" name="save" id="save" value="Guardar"  
           style="margin-top:3px; margin-left: 70px" onclick="updateCatalogo()" />         
  </div>
  
  <div id="dialog-select" title="Seleccione el Articulo">
    <div id="catalogo-content"></div>
  </div>
    
  </form>

</body>
</body>
</html>
