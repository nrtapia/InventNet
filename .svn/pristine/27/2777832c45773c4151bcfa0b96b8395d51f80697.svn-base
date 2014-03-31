
<%@ page import="invent.net.Proveedor" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proveedor.label', default: 'Proveedor')}" />
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
            
            <g:form name="myForm" url="[controller:'proveedor',action:'list']">
              Buscar: <g:textField name="q" value="${params.q}" />
              <input type="submit" value="Buscar"/>
            </g:form>
            
            <div class="list">
                <table>
                    <thead>
                        <tr>                        
                            <g:sortableColumn property="codigo" title="${message(code: 'proveedor.codigo.label', default: 'Código')}" />
                        
                            <g:sortableColumn property="razonSocial" title="${message(code: 'proveedor.razonSocial.label', default: 'Razon Social')}" />
                        
                            <g:sortableColumn property="nit" title="${message(code: 'proveedor.nit.label', default: 'NIT')}" />
                        
                            <th><g:message code="proveedor.ciudad.label" default="Ciudad" /></th>
                        
                            <g:sortableColumn property="direccion" title="${message(code: 'proveedor.direccion.label', default: 'Direccion')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proveedorInstanceList}" status="i" var="proveedorInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${proveedorInstance.id}">${fieldValue(bean: proveedorInstance, field: "codigo")}</g:link></td>
                        
                            <td>${fieldValue(bean: proveedorInstance, field: "razonSocial")}</td>
                        
                            <td>${fieldValue(bean: proveedorInstance, field: "nit")}</td>
                        
                            <td>${fieldValue(bean: proveedorInstance, field: "ciudad")}</td>
                        
                            <td>${fieldValue(bean: proveedorInstance, field: "direccion")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${proveedorInstanceTotal}" />
            </div>
            
        </div>
    </body>
</html>
