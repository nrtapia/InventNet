
<%@ page import="invent.net.Devolucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'devolucion.label', default: 'Devolucion')}" />
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
                        
                            <th><g:message code="devolucion.bodega.label" default="Bodega" /></th>
                        
                            <th><g:message code="devolucion.proveedor.label" default="Proveedor" /></th>
                        
                            <th><g:message code="devolucion.articulo.label" default="Articulo" /></th>
                        
                            <g:sortableColumn property="fecha" title="${message(code: 'devolucion.fecha.label', default: 'Fecha')}" />
                        
                            <g:sortableColumn property="costo" title="${message(code: 'devolucion.costo.label', default: 'Costo')}" />
                            
                            <g:sortableColumn property="valor" title="${message(code: 'devolucion.valor.label', default: 'Valor')}" />
                            
                            <g:sortableColumn property="cantidad" title="${message(code: 'devolucion.cantidad.label', default: 'Cant.')}" />
                            
                            <g:sortableColumn property="estado" title="${message(code: 'devolucion.estado.label', default: 'Estado')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${devolucionInstanceList}" status="i" var="devolucionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>                               
                                ${fieldValue(bean: devolucionInstance, field: "bodega")}                              
                            </td>
                        
                            <td>${fieldValue(bean: devolucionInstance, field: "proveedor")}</td>
                        
                            <td>${fieldValue(bean: devolucionInstance, field: "articulo")}</td>
                        
                            <td><g:formatDate date="${devolucionInstance.fecha}" /></td>
                        
                            <td>${fieldValue(bean: devolucionInstance, field: "costo")}</td>
                            
                            <td>${fieldValue(bean: devolucionInstance, field: "valor")}</td>
                            
                            <td>${fieldValue(bean: devolucionInstance, field: "cantidad")}</td>
                            
                            <g:if test="${devolucionInstance.estado == 'Pendiente'}">                              
                              <td>
                              <g:link action="edit" id="${devolucionInstance.id}" title="Recibir devolución" style="color: red">
                                  ${fieldValue(bean: devolucionInstance, field: "estado")}
                                </g:link>
                              </td>                              
                            </g:if>
                            <g:else>
                                <td>${fieldValue(bean: devolucionInstance, field: "estado")}</td>
                            </g:else>                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${devolucionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
