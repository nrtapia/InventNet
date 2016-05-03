<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>Entradas</title>

  <link rel="stylesheet" href="${resource(dir:'css', file: 'entrada.css')}" /> 
  <link rel="stylesheet" href="${resource(dir:'css', file: 'prettyPhoto.css')}" /> 
  <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" /> 

  <r:require module="jquery"/>
      
</head>
<body>  
  <g:javascript src="jquery-ui-1.7.3.custom.min.js" />
  <g:javascript src="jquery.prettyPhoto.js" />
  <g:javascript src="util.js" />  
  <g:javascript src="entrada.js" />  
  
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>            
  </div>
  <div class="body">
    <h1>Entradas</h1>
    <g:if test="${flash.message}">
      <div id="message" class="message">${flash.message}</div>
    </g:if>
  </div>

  <br/>
  <form id="formCatalogo">
    
  <div id="fecha"><span class="label-custom">Fecha : </span>    
    <label>
      <input type="text" name="fechavalue" id="fechaid" size="10"/>
    </label>
  </div>
  
  <div id="sucursal">
    <span class="label-custom">Sucursal : </span> &nbsp; 
    <g:select name="bodegaid" from="${listBdg}" optionKey="id" value=""  />
  </div>
  <div id="proveedor">
      <span class="label-custom">Proveedor : </span>
      <g:select name="proveedorid" from="${listPrv}"  optionKey="id" value=""  
              style="width: 238px;" />
  </div>
  <div id="movimiento">
    <span class="label-custom">Movimiento : </span>
    <g:select name="movetypeid" from="${listMov}"  optionKey="id" value=""  />  &nbsp;   
    <input type="button" value="Buscar"  onclick="searchArticulo()"/>
  </div>
 

  <div id="lista" class="borde">
    <g:render template="articulos" model="model"/>
  </div>
  
  </form>

  <div id="botones" >
    <input type="button" name="save" id="save" value="Guardar" onclick="updateCatalogo()" />    
  </div> 
  
  
</body>
</html>
