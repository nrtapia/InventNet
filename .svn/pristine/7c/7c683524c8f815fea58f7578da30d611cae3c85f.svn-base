
<%@ page import="invent.net.Inperfectos" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'inperfectos.label', default: 'Inperfectos')}" />
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
                            <td valign="top" class="name"><g:message code="inperfectos.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: inperfectosInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inperfectos.bodega.label" default="Bodega" /></td>
                            
                            <td valign="top" class="value"><g:link controller="bodega" action="show" id="${inperfectosInstance?.bodega?.id}">${inperfectosInstance?.bodega?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inperfectos.proveedor.label" default="Proveedor" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proveedor" action="show" id="${inperfectosInstance?.proveedor?.id}">${inperfectosInstance?.proveedor?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inperfectos.articulo.label" default="Articulo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="articulo" action="show" id="${inperfectosInstance?.articulo?.id}">${inperfectosInstance?.articulo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inperfectos.fecha.label" default="Fecha" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${inperfectosInstance?.fecha}" format="dd/MM/yyyy" /></td>
                            
                        </tr>
                                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inperfectos.tipo.label" default="Tipo" /></td>                            
                            <td valign="top" class="value">${inperfectosInstance?.tipo?.encodeAsHTML()}</td>                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inperfectos.cantidad.label" default="Cantidad" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: inperfectosInstance, field: "cantidad")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="inperfectos.observaciones.label" default="Observaciones" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: inperfectosInstance, field: "observaciones")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${inperfectosInstance?.id}" />
                    
                </g:form>
            </div>
        </div>
    </body>
</html>
