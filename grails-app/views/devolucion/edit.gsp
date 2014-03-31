

<%@ page import="invent.net.Devolucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'devolucion.label', default: 'Devolucion')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${devolucionInstance}">
            <div class="errors">
                <g:renderErrors bean="${devolucionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${devolucionInstance?.id}" />
                <g:hiddenField name="version" value="${devolucionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bodega"><g:message code="devolucion.bodega.label" default="Bodega" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'bodega', 'errors')}">                                    
                                    ${fieldValue(bean: devolucionInstance, field: "bodega")}
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proveedor"><g:message code="devolucion.proveedor.label" default="Proveedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'proveedor', 'errors')}">
                                    ${fieldValue(bean: devolucionInstance, field: "proveedor")}
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="articulo"><g:message code="devolucion.articulo.label" default="Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'articulo', 'errors')}">
                                    ${fieldValue(bean: devolucionInstance, field: "articulo")}
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fecha"><g:message code="devolucion.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'fecha', 'errors')}">
                                    ${fieldValue(bean: devolucionInstance, field: "fecha")}
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="costo"><g:message code="devolucion.costo.label" default="Costo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'costo', 'errors')}">
                                    ${fieldValue(bean: devolucionInstance, field: "costo")}
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="valor"><g:message code="devolucion.valor.label" default="Valor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'valor', 'errors')}">
                                    ${fieldValue(bean: devolucionInstance, field: "valor")}
                                </td>
                            </tr>
                                                
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cantidad"><g:message code="devolucion.cantidad.label" default="Cantidad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'cantidad', 'errors')}">
                                    ${fieldValue(bean: devolucionInstance, field: "cantidad")}
                                    <g:hiddenField name="cantidad" value="${devolucionInstance.cantidad}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="canting"><g:message code="devolucion.canting.label" default="Cant. enviada" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'canting', 'errors')}">
                                    <g:textField name="canting" value="${fieldValue(bean: devolucionInstance, field: 'canting')}"
                                                 style="text-align: right; width: 40px"/>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="detalle"><g:message code="devolucion.detalle.label" default="Observaciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'detalle', 'errors')}">
                                    <g:textArea name="detalle" value="${fieldValue(bean: devolucionInstance, field: 'detalle')}"
                                                 value=" "/>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                  <span class="button"><g:actionSubmit class="save" onclick="return confirm('Se recuerda que: se tiene que ingresar la cantidad devuelta por el Proveedor o las observaciones. Confirma continuar?')"
                                                       action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>                    
                </div>
            </g:form>
        </div>
    </body>
</html>
