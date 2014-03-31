
<%@ page import="invent.net.Inperfectos" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'inperfectos.label', default: 'Inperfectos')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'inperfectos.id.label', default: 'Id')}" />
                        
                            <th><g:message code="inperfectos.bodega.label" default="Bodega" /></th>
                        
                            <th><g:message code="inperfectos.proveedor.label" default="Proveedor" /></th>
                        
                            <th><g:message code="inperfectos.articulo.label" default="Articulo" /></th>
                            
                            <th><g:message code="inperfectos.tipo.label" default="Tipo" /></th>
                        
                            <g:sortableColumn property="fecha" title="${message(code: 'inperfectos.fecha.label', default: 'Fecha')}" />
                        
                            <g:sortableColumn property="cantidad" title="${message(code: 'inperfectos.cantidad.label', default: 'Cantidad')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${inperfectosInstanceList}" status="i" var="inperfectosInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${inperfectosInstance.id}">${fieldValue(bean: inperfectosInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: inperfectosInstance, field: "bodega")}</td>
                        
                            <td>${fieldValue(bean: inperfectosInstance, field: "proveedor")}</td>
                        
                            <td>${fieldValue(bean: inperfectosInstance, field: "articulo")}</td>
                            
                            <td>${fieldValue(bean: inperfectosInstance, field: "tipo")}</td>                            
                        
                            <td><g:formatDate date="${inperfectosInstance.fecha}" format="dd/MM/yyyy" /></td>
                        
                            <td>${fieldValue(bean: inperfectosInstance, field: "cantidad")}</td>                            
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${inperfectosInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
