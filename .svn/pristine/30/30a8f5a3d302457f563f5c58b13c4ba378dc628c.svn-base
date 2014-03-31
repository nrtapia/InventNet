
<%@ page import="invent.net.Referencia" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'referencia.label', default: 'Referencia')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'referencia.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="detalle" title="${message(code: 'referencia.detalle.label', default: 'Detalle')}" />
                        
                            <g:sortableColumn property="descripcion" title="${message(code: 'referencia.descripcion.label', default: 'Descripcion')}" />
                        
                            <th><g:message code="referencia.proveedor.label" default="Proveedor" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${referenciaInstanceList}" status="i" var="referenciaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${referenciaInstance.id}">${fieldValue(bean: referenciaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: referenciaInstance, field: "detalle")}</td>
                        
                            <td>${fieldValue(bean: referenciaInstance, field: "descripcion")}</td>
                        
                            <td>${fieldValue(bean: referenciaInstance, field: "proveedor")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${referenciaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
