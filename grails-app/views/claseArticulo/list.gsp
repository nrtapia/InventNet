
<%@ page import="invent.net.ClaseArticulo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'claseArticulo.label', default: 'ClaseArticulo')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'claseArticulo.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="codigo" title="${message(code: 'claseArticulo.codigo.label', default: 'Codigo')}" />
                        
                            <g:sortableColumn property="detalle" title="${message(code: 'claseArticulo.detalle.label', default: 'Detalle')}" />
                        
                            <g:sortableColumn property="switche" title="${message(code: 'claseArticulo.switche.label', default: 'Switche')}" />
                        
                            <g:sortableColumn property="valor" title="${message(code: 'claseArticulo.valor.label', default: 'Valor')}" />
                        
                            <g:sortableColumn property="abrevia" title="${message(code: 'claseArticulo.abrevia.label', default: 'Abrevia')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${claseArticuloInstanceList}" status="i" var="claseArticuloInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${claseArticuloInstance.id}">${fieldValue(bean: claseArticuloInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: claseArticuloInstance, field: "codigo")}</td>
                        
                            <td>${fieldValue(bean: claseArticuloInstance, field: "detalle")}</td>
                        
                            <td><g:formatBoolean boolean="${claseArticuloInstance.switche}" /></td>
                        
                            <td>${fieldValue(bean: claseArticuloInstance, field: "valor")}</td>
                        
                            <td>${fieldValue(bean: claseArticuloInstance, field: "abrevia")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${claseArticuloInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
