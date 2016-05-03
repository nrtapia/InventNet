
<%@ page import="invent.net.TipoDocumento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tipoDocumento.label', default: 'TipoDocumento')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'tipoDocumento.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="codigo" title="${message(code: 'tipoDocumento.codigo.label', default: 'Codigo')}" />
                        
                            <g:sortableColumn property="detalle" title="${message(code: 'tipoDocumento.detalle.label', default: 'Detalle')}" />
                        
                            <th><g:message code="tipoDocumento.movimiento.label" default="Movimiento" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tipoDocumentoInstanceList}" status="i" var="tipoDocumentoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tipoDocumentoInstance.id}">${fieldValue(bean: tipoDocumentoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: tipoDocumentoInstance, field: "codigo")}</td>
                        
                            <td>${fieldValue(bean: tipoDocumentoInstance, field: "detalle")}</td>
                        
                            <td>${fieldValue(bean: tipoDocumentoInstance, field: "movimiento")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${tipoDocumentoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
