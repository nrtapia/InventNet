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
  <title>Catalogo</title>

  <link rel="stylesheet" href="${resource(dir:'css', file: 'catalogo.css')}" />  
  <g:javascript library="jquery" />    
  
</head>

<body>
  <g:javascript src="catalogo.js" />  
  <g:javascript src="util.js" />  
  
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>            
  </div>
  <div class="body">
    <h1>Catalogo de Proveedores</h1>
    <g:if test="${flash.message}">
      <div id="message" class="message">${flash.message}</div>
    </g:if>
  </div>

  <br/>

  <div id="proveedores"> 
    <g:select name="proveedorid" from="${listPrv}"  multiple="false"
              optionKey="id" value="${requisicionInstance?.proveedor?.id}"  
              style="width: 238px; height: 384px" />
  </div>
  <div id="bodega">
    &nbsp; 
    <g:select name="bodegaid" from="${listBdg}"  style="margin-top: 6px"
              optionKey="id"   />
    &nbsp; &nbsp;            

    <input type="button" id="button-search" value="Buscar" class="find-icon" />

  </div>


  <div id="articulos">   
    <g:render template="artByPrv" model="model"/>
  </div>

  <div id="catalogo">
    <g:render template="artByPrvData" model="model2"/>
  </div>

  <div id="options">
    &nbsp;  


    <a href="#" onclick="deleteAll()" style="display: none">
      <img src="${createLinkTo(dir:'images', file:'arrow_up.png')}" class="borde" 
           title="Quitar todos los articulos"/> 
    </a>

    <a href="#" onclick="addAll()" style="display: none">
      <img src="${createLinkTo(dir:'images', file:'arrow_down.png')}" class="borde"
           title="Agregar todos los articulos"/>
    </a>     

  </div>

  <div id="footer">
   
    <sec:ifAnyGranted roles="ROLE_CATALOGO">    
      <input type="button" name="save" id="save" value="Guardar"  
            style="margin-top:3px; margin-left: 120px" onclick="updateCatalogo()" />
    </sec:ifAnyGranted>
  </div>

</body>
</body>
</html>
