

<%@ page import="invent.net.CualidadesArticulo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo')}" />
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
            <g:hasErrors bean="${cualidadesArticuloInstance}">
            <div class="errors">
                <g:renderErrors bean="${cualidadesArticuloInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="cualidadesArticulo.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cualidadesArticuloInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" maxlength="3" value="${cualidadesArticuloInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="detalle"><g:message code="cualidadesArticulo.detalle.label" default="Detalle" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cualidadesArticuloInstance, field: 'detalle', 'errors')}">
                                    <g:textField name="detalle" maxlength="60" value="${cualidadesArticuloInstance?.detalle}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="switche"><g:message code="cualidadesArticulo.switche.label" default="Switche" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cualidadesArticuloInstance, field: 'switche', 'errors')}">
                                    <g:checkBox name="switche" value="${cualidadesArticuloInstance?.switche}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="valor"><g:message code="cualidadesArticulo.valor.label" default="Valor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cualidadesArticuloInstance, field: 'valor', 'errors')}">
                                    <g:textField name="valor" value="${fieldValue(bean: cualidadesArticuloInstance, field: 'valor')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="abrevia"><g:message code="cualidadesArticulo.abrevia.label" default="Abrevia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cualidadesArticuloInstance, field: 'abrevia', 'errors')}">
                                    <g:textField name="abrevia" maxlength="3" value="${cualidadesArticuloInstance?.abrevia}" />
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
