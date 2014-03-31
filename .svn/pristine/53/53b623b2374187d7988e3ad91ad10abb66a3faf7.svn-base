

<%@ page import="invent.net.Movimiento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'movimiento.label', default: 'Movimiento')}" />
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
            <g:hasErrors bean="${movimientoInstance}">
            <div class="errors">
                <g:renderErrors bean="${movimientoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipo"><g:message code="movimiento.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: movimientoInstance, field: 'tipo', 'errors')}">
                                    <g:select name="tipo" from="${movimientoInstance.constraints.tipo.inList}" value="${movimientoInstance?.tipo}" valueMessagePrefix="movimiento.tipo"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="movimiento.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: movimientoInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" maxlength="3" value="${movimientoInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="detalle"><g:message code="movimiento.detalle.label" default="Detalle" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: movimientoInstance, field: 'detalle', 'errors')}">
                                    <g:textField name="detalle" maxlength="60" value="${movimientoInstance?.detalle}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="abrevia"><g:message code="movimiento.abrevia.label" default="Abrevia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: movimientoInstance, field: 'abrevia', 'errors')}">
                                    <g:textField name="abrevia" maxlength="3" value="${movimientoInstance?.abrevia}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mostrar"><g:message code="movimiento.mostrar.label" default="Mostrar" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: movimientoInstance, field: 'mostrar', 'errors')}">
                                    <g:checkBox name="mostrar" value="${movimientoInstance?.mostrar}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ref"><g:message code="movimiento.ref.label" default="Ref" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: movimientoInstance, field: 'ref', 'errors')}">
                                    <g:checkBox name="ref" value="${movimientoInstance?.ref}" />
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
