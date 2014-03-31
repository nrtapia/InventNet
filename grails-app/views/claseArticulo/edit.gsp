

<%@ page import="invent.net.ClaseArticulo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'claseArticulo.label', default: 'ClaseArticulo')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${claseArticuloInstance}">
            <div class="errors">
                <g:renderErrors bean="${claseArticuloInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${claseArticuloInstance?.id}" />
                <g:hiddenField name="version" value="${claseArticuloInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="codigo"><g:message code="claseArticulo.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: claseArticuloInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" maxlength="3" value="${claseArticuloInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="detalle"><g:message code="claseArticulo.detalle.label" default="Detalle" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: claseArticuloInstance, field: 'detalle', 'errors')}">
                                    <g:textField name="detalle" maxlength="60" value="${claseArticuloInstance?.detalle}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="switche"><g:message code="claseArticulo.switche.label" default="Switche" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: claseArticuloInstance, field: 'switche', 'errors')}">
                                    <g:checkBox name="switche" value="${claseArticuloInstance?.switche}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="valor"><g:message code="claseArticulo.valor.label" default="Valor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: claseArticuloInstance, field: 'valor', 'errors')}">
                                    <g:textField name="valor" value="${fieldValue(bean: claseArticuloInstance, field: 'valor')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="abrevia"><g:message code="claseArticulo.abrevia.label" default="Abrevia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: claseArticuloInstance, field: 'abrevia', 'errors')}">
                                    <g:textField name="abrevia" maxlength="3" value="${claseArticuloInstance?.abrevia}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
