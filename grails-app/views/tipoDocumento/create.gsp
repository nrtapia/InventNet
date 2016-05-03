

<%@ page import="invent.net.TipoDocumento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tipoDocumento.label', default: 'TipoDocumento')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tipoDocumentoInstance}">
            <div class="errors">
                <g:renderErrors bean="${tipoDocumentoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="tipoDocumento.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tipoDocumentoInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" maxlength="3" value="${tipoDocumentoInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="detalle"><g:message code="tipoDocumento.detalle.label" default="Detalle" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tipoDocumentoInstance, field: 'detalle', 'errors')}">
                                    <g:textField name="detalle" maxlength="60" value="${tipoDocumentoInstance?.detalle}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="movimiento"><g:message code="tipoDocumento.movimiento.label" default="Movimiento" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tipoDocumentoInstance, field: 'movimiento', 'errors')}">
                                    <g:select name="movimiento.id" from="${invent.net.Movimiento.list()}" optionKey="id" value="${tipoDocumentoInstance?.movimiento?.id}"  />
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
