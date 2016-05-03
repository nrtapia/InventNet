
<%@ page import="invent.net.Inventario" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'inventario.label', default: 'Inventario')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
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
                            <td valign="top" class="name"><g:message code="inventario.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: inventarioInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.bodega.label" default="Bodega" /></td>
                            
                            <td valign="top" class="value"><g:link controller="bodega" action="show" id="${inventarioInstance?.bodega?.id}">${inventarioInstance?.bodega?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.proveedor.label" default="Proveedor" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proveedor" action="show" id="${inventarioInstance?.proveedor?.id}">${inventarioInstance?.proveedor?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.articulo.label" default="Articulo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="articulo" action="show" id="${inventarioInstance?.articulo?.id}">${inventarioInstance?.articulo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.cantidad.label" default="Cantidad" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: inventarioInstance, field: "cantidad")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.valor.label" default="Valor" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: inventarioInstance, field: "valor")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.tipo.label" default="Tipo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: inventarioInstance, field: "tipo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.movimiento.label" default="Movimiento" /></td>
                            
                            <td valign="top" class="value"><g:link controller="movimiento" action="show" id="${inventarioInstance?.movimiento?.id}">${inventarioInstance?.movimiento?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inventario.usuario.label" default="Usuario" /></td>
                            
                            <td valign="top" class="value"><g:link controller="usuario" action="show" id="${inventarioInstance?.usuario?.id}">${inventarioInstance?.usuario?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${inventarioInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
