
<%@ page import="invent.net.AutorizaCierre" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'autorizaCierre.label', default: 'AutorizaCierre')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'autorizaCierre.id.label', default: 'Id')}" />
                        
                            <th><g:message code="autorizaCierre.bodega.label" default="Bodega" /></th>
                        
                            <g:sortableColumn property="fecha" title="${message(code: 'autorizaCierre.fecha.label', default: 'Fecha')}" />
                        
                            <g:sortableColumn property="estado" title="${message(code: 'autorizaCierre.estado.label', default: 'Estado')}" />
                        
                            <g:sortableColumn property="observaciones" title="${message(code: 'autorizaCierre.observaciones.label', default: 'Observaciones')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${autorizaCierreInstanceList}" status="i" var="autorizaCierreInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${autorizaCierreInstance.id}">${fieldValue(bean: autorizaCierreInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: autorizaCierreInstance, field: "bodega")}</td>
                        
                            <td><g:formatDate date="${autorizaCierreInstance.fecha}"  format="dd/MM/yyyy"/></td>
                            
                            <td>${fieldValue(bean: autorizaCierreInstance, field: "estado")}</td>
                        
                            <td>${fieldValue(bean: autorizaCierreInstance, field: "observaciones")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${autorizaCierreInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
