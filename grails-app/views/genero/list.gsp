
<%@ page import="invent.net.Genero" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'genero.label', default: 'Genero')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'genero.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="codigo" title="${message(code: 'genero.codigo.label', default: 'Codigo')}" />
                        
                            <g:sortableColumn property="detalle" title="${message(code: 'genero.detalle.label', default: 'Detalle')}" />
                        
                            <g:sortableColumn property="switche" title="${message(code: 'genero.switche.label', default: 'Switche')}" />
                        
                            <g:sortableColumn property="valor" title="${message(code: 'genero.valor.label', default: 'Valor')}" />
                        
                            <g:sortableColumn property="abrevia" title="${message(code: 'genero.abrevia.label', default: 'Abrevia')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${generoInstanceList}" status="i" var="generoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${generoInstance.id}">${fieldValue(bean: generoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: generoInstance, field: "codigo")}</td>
                        
                            <td>${fieldValue(bean: generoInstance, field: "detalle")}</td>
                        
                            <td><g:formatBoolean boolean="${generoInstance.switche}" /></td>
                        
                            <td>${fieldValue(bean: generoInstance, field: "valor")}</td>
                        
                            <td>${fieldValue(bean: generoInstance, field: "abrevia")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${generoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
