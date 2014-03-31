
<%@ page import="invent.net.Articulo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        
        <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" /> 
        <link rel="stylesheet" href="${resource(dir:'css',file:'prettyPhoto.css')}" />
        <g:javascript library="jquery" />
        
    </head>
    <body>
        <g:javascript src="jquery.prettyPhoto.js" /> 
        <g:javascript src="articulo.js" /> 
        
      
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            
            <g:form name="myForm" url="[controller:'articulo',action:'list']">
              Buscar: <g:textField name="q" value="${params.q}" />
              <input type="submit" value="Buscar"/>
            </g:form>
            
            <div class="list">
                <table>
                    <thead>
                        <tr>                       
                                                        
                            <g:sortableColumn property="tipoArticulo" title="${message(code: 'articulo.tipoArticulo.label', default: 'Tipo')}" />
                                                      
                            <g:sortableColumn property="codigo" title="${message(code: 'articulo.codigo.label', default: 'Código')}" />
                            
                            <th><g:message code="articulo.genero.label" default="Genero" /></th>                            
                            
                            <th><g:message code="articulo.claseArticulo.label" default="Clase" /></th>
                        
                            <th><g:message code="articulo.cualidadesArticulo.label" default="Cualidades" /></th>
                            
                            <th><g:message code="articulo.ref.label" default="Ref." /></th>
                            
                            <th><g:message code="articulo.des.label" default="Descripción" /></th>
                            
                            <th>Fotos</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${articuloInstanceList}" status="i" var="articuloInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                                  
                             <td>${fieldValue(bean: articuloInstance, field: "tipoArticulo")}</td>
                          
                            <td><g:link action="show" id="${articuloInstance.id}">${fieldValue(bean: articuloInstance, field: "codigo")}</g:link></td>
                      
                            <td>${fieldValue(bean: articuloInstance, field: "genero")}</td>    
                    
                            <td>${fieldValue(bean: articuloInstance, field: "claseArticulo")}</td>
                    
                            <td>${fieldValue(bean: articuloInstance, field: "cualidadesArticulo")}</td>
                           
                            <td>${fieldValue(bean: articuloInstance, field: "referencia")}</td>
                            
                            <td>${fieldValue(bean: articuloInstance, field: "descripcion")}</td>
                            
                            <td>
                                <g:each in="${articuloInstance?.fotos?}" var="row">                                        
                                      <cys:foto data="${row}" />
                                      &nbsp;
                                </g:each>
                            </td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${articuloInstanceTotal}" />
            </div>
        </div>
            
      <iframe id="hdn-frm" name="hdn-frm" style="display: none" onload="uploadComplete()">
      </iframe>
      
    </body>
</html>
