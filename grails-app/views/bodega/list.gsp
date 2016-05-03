
<%@ page import="invent.net.Bodega" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bodega.label', default: 'Bodega')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>            
            <span class="menuButton"><g:link class="list" controller="secUser" action="list">Sucursales x Usuario</g:link></span>            
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'bodega.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nombre" title="${message(code: 'bodega.nombre.label', default: 'Nombre')}" />
                        
                            <th><g:message code="bodega.ciudad.label" default="Ciudad" /></th>
                        
                            <g:sortableColumn property="direccion" title="${message(code: 'bodega.direccion.label', default: 'Direccion')}" />
                        
                            <g:sortableColumn property="telefono" title="${message(code: 'bodega.telefono.label', default: 'Telefono')}" />
                        
                            <g:sortableColumn property="encargado" title="${message(code: 'bodega.encargado.label', default: 'Encargado')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bodegaInstanceList}" status="i" var="bodegaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${bodegaInstance.id}">${fieldValue(bean: bodegaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: bodegaInstance, field: "nombre")}</td>
                        
                            <td>${fieldValue(bean: bodegaInstance, field: "ciudad")}</td>
                        
                            <td>${fieldValue(bean: bodegaInstance, field: "direccion")}</td>
                        
                            <td>${fieldValue(bean: bodegaInstance, field: "telefono")}</td>
                        
                            <td>${fieldValue(bean: bodegaInstance, field: "encargado")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${bodegaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
