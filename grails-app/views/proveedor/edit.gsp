

<%@ page import="invent.net.Proveedor" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proveedor.label', default: 'Proveedor')}" />
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
            <g:hasErrors bean="${proveedorInstance}">
            <div class="errors">
                <g:renderErrors bean="${proveedorInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${proveedorInstance?.id}" />
                <g:hiddenField name="version" value="${proveedorInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="codigo"><g:message code="proveedor.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" maxlength="4" value="${proveedorInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="razonSocial"><g:message code="proveedor.razonSocial.label" default="Razon Social" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'razonSocial', 'errors')}">
                                    <g:textField name="razonSocial" maxlength="40" value="${proveedorInstance?.razonSocial}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nit"><g:message code="proveedor.nit.label" default="Nit" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'nit', 'errors')}">
                                    <g:textField name="nit" maxlength="20" value="${proveedorInstance?.nit}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="ciudad"><g:message code="proveedor.ciudad.label" default="Ciudad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'ciudad', 'errors')}">
                                    <g:select name="ciudad.id" from="${invent.net.Ciudad.list()}" optionKey="id" value="${proveedorInstance?.ciudad?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="direccion"><g:message code="proveedor.direccion.label" default="Direccion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'direccion', 'errors')}">
                                    <g:textField name="direccion" maxlength="150" value="${proveedorInstance?.direccion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="telefono"><g:message code="proveedor.telefono.label" default="Telefono" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'telefono', 'errors')}">
                                    <g:textField name="telefono" maxlength="30" value="${proveedorInstance?.telefono}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="estado"><g:message code="proveedor.estado.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'estado', 'errors')}">
                                    <g:select name="estado" from="${proveedorInstance.constraints.estado.inList}" value="${proveedorInstance?.estado}" valueMessagePrefix="proveedor.estado"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="referencias"><g:message code="proveedor.referencias.label" default="Referencias" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proveedorInstance, field: 'referencias', 'errors')}">
                                    
<ul>
<g:each in="${proveedorInstance?.referencias?}" var="r">
    <li><g:link controller="referencia" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="referencia" action="create" params="['proveedor.id': proveedorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'referencia.label', default: 'Referencia')])}</g:link>

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
