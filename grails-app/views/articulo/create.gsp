

<%@ page import="invent.net.Articulo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <g:javascript library="jquery" /> 
    </head>
    <body>
        <g:javascript src="util.js" /> 
        <g:javascript src="jquery.prettyPhoto.js" /> 
        <g:javascript src="articulo.js" /> 
        
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${articuloInstance}">
            <div class="errors">
                <g:renderErrors bean="${articuloInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="articulo.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: articuloInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" maxlength="10" value="${articuloInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipoArticulo"><g:message code="articulo.tipoArticulo.label" default="Tipo Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: articuloInstance, field: 'tipoArticulo', 'errors')}">
                                    <g:select name="tipoArticulo.id" class="tpoArt" from="${invent.net.TipoArticulo.list()}" optionKey="id" value="${articuloInstance?.tipoArticulo?.id}" 
                                              noSelection="${['': '']}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="claseArticulo"><g:message code="articulo.claseArticulo.label" default="Clase Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: articuloInstance, field: 'claseArticulo', 'errors')}">
                                    <g:select name="claseArticulo.id" class="clsArt"  from="${invent.net.ClaseArticulo.list()}" optionKey="id" value="${articuloInstance?.claseArticulo?.id}"  
                                              noSelection="${['': '']}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="genero"><g:message code="articulo.genero.label" default="Genero" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: articuloInstance, field: 'genero', 'errors')}">
                                    <g:select name="genero.id" class="gnrArt"  from="${invent.net.Genero.list()}" optionKey="id" value="${articuloInstance?.genero?.id}"  
                                              noSelection="${['': '']}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cualidadesArticulo"><g:message code="articulo.cualidadesArticulo.label" default="Cualidades Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: articuloInstance, field: 'cualidadesArticulo', 'errors')}">
                                    <g:select name="cualidadesArticulo.id"  class="cldArt"  from="${invent.net.CualidadesArticulo.list()}" optionKey="id" value="${articuloInstance?.cualidadesArticulo?.id}"  
                                              noSelection="${['': '']}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="referencia"><g:message code="articulo.referencia.label" default="Referencia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: articuloInstance, field: 'referencia', 'errors')}">
                                    <g:textField name="referencia" maxlength="2" value="${articuloInstance?.referencia}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="descripcion"><g:message code="articulo.descripcion.label" default="Descripcion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: articuloInstance, field: 'descripcion', 'errors')}">
                                    <g:textField name="descripcion" maxlength="20" value="${articuloInstance?.descripcion}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
