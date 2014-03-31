
<%@ page import="invent.net.Devolucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'devolucion.label', default: 'Devolucion')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: devolucionInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.bodega.label" default="Bodega" /></td>
                            
                            <td valign="top" class="value"><g:link controller="bodega" action="show" id="${devolucionInstance?.bodega?.id}">${devolucionInstance?.bodega?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.proveedor.label" default="Proveedor" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proveedor" action="show" id="${devolucionInstance?.proveedor?.id}">${devolucionInstance?.proveedor?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.articulo.label" default="Articulo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="articulo" action="show" id="${devolucionInstance?.articulo?.id}">${devolucionInstance?.articulo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.fecha.label" default="Fecha" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${devolucionInstance?.fecha}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.costo.label" default="Costo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: devolucionInstance, field: "costo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.valor.label" default="Valor" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: devolucionInstance, field: "valor")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.estado.label" default="Estado" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: devolucionInstance, field: "estado")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.cantidad.label" default="Cantidad" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: devolucionInstance, field: "cantidad")}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.canting.label" default="Cant. enviada" /></td>
                            <td valign="top" class="value">${fieldValue(bean: devolucionInstance, field: "canting")}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="devolucion.canting.label" default="Observaciones" /></td>
                            <td valign="top" class="value">${fieldValue(bean: devolucionInstance, field: "detalle")}</td>
                        </tr>
                                        
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
</html>
