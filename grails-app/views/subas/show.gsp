
<%@ page import="invent.net.Subas" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'subas.label', default: 'Subas')}" />
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
                            <td valign="top" class="name"><g:message code="subas.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: subasInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.bodega.label" default="Bodega" /></td>
                            
                            <td valign="top" class="value"><g:link controller="bodega" action="show" id="${subasInstance?.bodega?.id}">${subasInstance?.bodega?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.proveedor.label" default="Proveedor" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proveedor" action="show" id="${subasInstance?.proveedor?.id}">${subasInstance?.proveedor?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.articulo.label" default="Articulo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="articulo" action="show" id="${subasInstance?.articulo?.id}">${subasInstance?.articulo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.fecha.label" default="Fecha" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${subasInstance?.fecha}"  format="dd/MM/yyyy" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.tipo.label" default="Tipo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: subasInstance, field: "tipo")}</td>
                            
                        </tr>
                                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.cantidad.label" default="Cantidad" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: subasInstance, field: "cantidad")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.valorAnterior.label" default="Valor Anterior" /></td>                            
                            <td valign="top" class="value">${fieldValue(bean: subasInstance, field: "valorAnterior")}</td>                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="subas.valor.label" default="Nuevo Valor" /></td>                            
                            <td valign="top" class="value">${fieldValue(bean: subasInstance, field: "valor")}</td>                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${subasInstance?.id}" />
                    
                </g:form>
            </div>
        </div>
    </body>
</html>
