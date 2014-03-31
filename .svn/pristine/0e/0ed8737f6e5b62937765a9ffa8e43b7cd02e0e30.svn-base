
<%@ page import="invent.net.Movimiento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'movimiento.label', default: 'Movimiento')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'movimiento.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="tipo" title="${message(code: 'movimiento.tipo.label', default: 'Tipo')}" />
                        
                            <g:sortableColumn property="codigo" title="${message(code: 'movimiento.codigo.label', default: 'Codigo')}" />
                        
                            <g:sortableColumn property="detalle" title="${message(code: 'movimiento.detalle.label', default: 'Detalle')}" />
                        
                            <g:sortableColumn property="abrevia" title="${message(code: 'movimiento.abrevia.label', default: 'Abrevia')}" />
                        
                            <g:sortableColumn property="mostrar" title="${message(code: 'movimiento.mostrar.label', default: 'Mostrar')}" />
                            
                            <g:sortableColumn property="ref" title="${message(code: 'movimiento.ref.label', default: 'Mostrar')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${movimientoInstanceList}" status="i" var="movimientoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${movimientoInstance.id}">${fieldValue(bean: movimientoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: movimientoInstance, field: "tipo")}</td>
                        
                            <td>${fieldValue(bean: movimientoInstance, field: "codigo")}</td>
                        
                            <td>${fieldValue(bean: movimientoInstance, field: "detalle")}</td>
                        
                            <td>${fieldValue(bean: movimientoInstance, field: "abrevia")}</td>
                        
                            <td><g:formatBoolean boolean="${movimientoInstance.mostrar}" /></td>
                            
                            <td><g:formatBoolean boolean="${movimientoInstance.ref}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${movimientoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
