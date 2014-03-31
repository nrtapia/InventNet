
<%@ page import="invent.net.Articulo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        
        <link rel="stylesheet" href="${resource(dir:'css',file:'prettyPhoto.css')}" />
       
        <g:javascript library="jquery" />
        
    </head>
    <body>
      
        <g:javascript src="jquery.prettyPhoto.js" /> 
        <g:javascript src="articulo.js" /> 
      
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: articuloInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.codigo.label" default="Codigo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: articuloInstance, field: "codigo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.tipoArticulo.label" default="Tipo Articulo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="tipoArticulo" action="show" id="${articuloInstance?.tipoArticulo?.id}">${articuloInstance?.tipoArticulo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.claseArticulo.label" default="Clase Articulo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="claseArticulo" action="show" id="${articuloInstance?.claseArticulo?.id}">${articuloInstance?.claseArticulo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.genero.label" default="Genero" /></td>
                            
                            <td valign="top" class="value"><g:link controller="genero" action="show" id="${articuloInstance?.genero?.id}">${articuloInstance?.genero?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.cualidadesArticulo.label" default="Cualidades Articulo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="cualidadesArticulo" action="show" id="${articuloInstance?.cualidadesArticulo?.id}">${articuloInstance?.cualidadesArticulo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.referencia.label" default="Referencia" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: articuloInstance, field: "referencia")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.descripcion.label" default="Descripcion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: articuloInstance, field: "descripcion")}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="articulo.fotos.label" default="Fotos" /></td>
                            
                            <td valign="top" class="value">
                            
                                <ul>
                                    <g:each in="${articuloInstance?.fotos?}" var="row">
                                        <li>
                                          <cys:foto data="${row}" />
                                        </li>
                                    </g:each>
                                </ul>                       
                              
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${articuloInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
