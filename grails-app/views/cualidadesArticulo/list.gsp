
<%@ page import="invent.net.CualidadesArticulo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cualidadesArticulo.label', default: 'CualidadesArticulo')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'cualidadesArticulo.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="codigo" title="${message(code: 'cualidadesArticulo.codigo.label', default: 'Codigo')}" />
                        
                            <g:sortableColumn property="detalle" title="${message(code: 'cualidadesArticulo.detalle.label', default: 'Detalle')}" />
                        
                            <g:sortableColumn property="switche" title="${message(code: 'cualidadesArticulo.switche.label', default: 'Switche')}" />
                        
                            <g:sortableColumn property="valor" title="${message(code: 'cualidadesArticulo.valor.label', default: 'Valor')}" />
                        
                            <g:sortableColumn property="abrevia" title="${message(code: 'cualidadesArticulo.abrevia.label', default: 'Abrevia')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cualidadesArticuloInstanceList}" status="i" var="cualidadesArticuloInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cualidadesArticuloInstance.id}">${fieldValue(bean: cualidadesArticuloInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: cualidadesArticuloInstance, field: "codigo")}</td>
                        
                            <td>${fieldValue(bean: cualidadesArticuloInstance, field: "detalle")}</td>
                        
                            <td><g:formatBoolean boolean="${cualidadesArticuloInstance.switche}" /></td>
                        
                            <td>${fieldValue(bean: cualidadesArticuloInstance, field: "valor")}</td>
                        
                            <td>${fieldValue(bean: cualidadesArticuloInstance, field: "abrevia")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cualidadesArticuloInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
