

<%@ page import="invent.net.Inventario" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'inventario.label', default: 'Inventario')}" />
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
            <g:hasErrors bean="${inventarioInstance}">
            <div class="errors">
                <g:renderErrors bean="${inventarioInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${inventarioInstance?.id}" />
                <g:hiddenField name="version" value="${inventarioInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bodega"><g:message code="inventario.bodega.label" default="Bodega" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'bodega', 'errors')}">
                                    <g:select name="bodega.id" from="${invent.net.Bodega.list()}" optionKey="id" value="${inventarioInstance?.bodega?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proveedor"><g:message code="inventario.proveedor.label" default="Proveedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'proveedor', 'errors')}">
                                    <g:select name="proveedor.id" from="${invent.net.Proveedor.list()}" optionKey="id" value="${inventarioInstance?.proveedor?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="articulo"><g:message code="inventario.articulo.label" default="Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'articulo', 'errors')}">
                                    <g:select name="articulo.id" from="${invent.net.Articulo.list()}" optionKey="id" value="${inventarioInstance?.articulo?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cantidad"><g:message code="inventario.cantidad.label" default="Cantidad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'cantidad', 'errors')}">
                                    <g:textField name="cantidad" value="${fieldValue(bean: inventarioInstance, field: 'cantidad')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="valor"><g:message code="inventario.valor.label" default="Valor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'valor', 'errors')}">
                                    <g:textField name="valor" value="${fieldValue(bean: inventarioInstance, field: 'valor')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tipo"><g:message code="inventario.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'tipo', 'errors')}">
                                    <g:select name="tipo" from="${inventarioInstance.constraints.tipo.inList}" value="${inventarioInstance?.tipo}" valueMessagePrefix="inventario.tipo"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="movimiento"><g:message code="inventario.movimiento.label" default="Movimiento" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'movimiento', 'errors')}">
                                    <g:select name="movimiento.id" from="${invent.net.Movimiento.list()}" optionKey="id" value="${inventarioInstance?.movimiento?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="usuario"><g:message code="inventario.usuario.label" default="Usuario" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inventarioInstance, field: 'usuario', 'errors')}">
                                    <g:select name="usuario.id" from="${invent.net.Usuario.list()}" optionKey="id" value="${inventarioInstance?.usuario?.id}"  />
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
