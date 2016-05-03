
<%@ page import="invent.net.Inventario" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'inventario.label', default: 'Inventario')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'inventario.id.label', default: 'Id')}" />
                        
                            <th><g:message code="inventario.bodega.label" default="Bodega" /></th>
                        
                            <th><g:message code="inventario.proveedor.label" default="Proveedor" /></th>
                        
                            <th><g:message code="inventario.articulo.label" default="Articulo" /></th>
                        
                            <g:sortableColumn property="cantidad" title="${message(code: 'inventario.cantidad.label', default: 'Cantidad')}" />
                        
                            <g:sortableColumn property="valor" title="${message(code: 'inventario.valor.label', default: 'Valor')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${inventarioInstanceList}" status="i" var="inventarioInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${inventarioInstance.id}">${fieldValue(bean: inventarioInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: inventarioInstance, field: "bodega")}</td>
                        
                            <td>${fieldValue(bean: inventarioInstance, field: "proveedor")}</td>
                        
                            <td>${fieldValue(bean: inventarioInstance, field: "articulo")}</td>
                        
                            <td>${fieldValue(bean: inventarioInstance, field: "cantidad")}</td>
                        
                            <td>${fieldValue(bean: inventarioInstance, field: "valor")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${inventarioInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
