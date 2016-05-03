
<%@ page import="invent.net.Subas" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'subas.label', default: 'Subas')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'subas.id.label', default: 'Id')}" />
                        
                            <th><g:message code="subas.bodega.label" default="Bodega" /></th>
                        
                            <th><g:message code="subas.proveedor.label" default="Proveedor" /></th>
                        
                            <th><g:message code="subas.articulo.label" default="Articulo" /></th>
                            
                            <g:sortableColumn property="fecha" title="${message(code: 'subas.fecha.label', default: 'Fecha')}" />
                        
                            <g:sortableColumn property="tipo" title="${message(code: 'subas.tipo.label', default: 'Tipo')}" />
                            
                            <g:sortableColumn property="cantidad" title="${message(code: 'subas.cantidad.label', default: 'Cantidad')}" />
                            
                            <g:sortableColumn property="valorAnterior" title="${message(code: 'subas.valoranterior.label', default: 'Valor Anterior')}" />
                            
                            <g:sortableColumn property="valor" title="${message(code: 'subas.valor.label', default: 'Nuevo Valor')}" />
                            
                            <th><g:message code="subas.diferencia.label" default="Diferencia" /></th>
                            
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${subasInstanceList}" status="i" var="subasInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${subasInstance.id}">${fieldValue(bean: subasInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: subasInstance, field: "bodega")}</td>
                        
                            <td>${fieldValue(bean: subasInstance, field: "proveedor")}</td>
                        
                            <td>${fieldValue(bean: subasInstance, field: "articulo")}</td>
                                                    
                            <td><g:formatDate date="${subasInstance.fecha}"  format="dd/MM/yyyy" /></td>
                        
                            <td>${fieldValue(bean: subasInstance, field: "tipo")}</td>
                            
                            <td style="text-align: right">${fieldValue(bean: subasInstance, field: "cantidad")}</td>
                            
                            <td style="text-align: right">${fieldValue(bean: subasInstance, field: "valorAnterior")}</td>
                            
                            <td style="text-align: right">${fieldValue(bean: subasInstance, field: "valor")}</td>
                            
                            <td style="text-align: right">
                              <g:if test="${subasInstance.tipo == 'Suba'}">
                                ${subasInstance.valor - subasInstance.valorAnterior}
                              </g:if>
                              <g:else>
                                ${subasInstance.valorAnterior - subasInstance.valor}
                              </g:else>
                            </td>
                                                    
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${subasInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
