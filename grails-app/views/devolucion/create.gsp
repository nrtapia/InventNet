

<%@ page import="invent.net.Devolucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'devolucion.label', default: 'Devolucion a Proveedor')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>        
        <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" />                 
        <g:javascript library="jquery" />

    </head>
    <body>
        <g:javascript src="jquery-ui-1.7.3.custom.min.js" />
        <g:javascript src="devolucion.js" />  
        
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${devolucionInstance}">
            <div class="errors">
                <g:renderErrors bean="${devolucionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bodega"><g:message code="devolucion.bodega.label" default="Bodega" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'bodega', 'errors')}">
                                    <g:select name="bodega.id" from="${listBdg}" optionKey="id" value="${devolucionInstance?.bodega?.id}"  
                                              onchange="clearPrv()" noSelection="${['0':' ']}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proveedor"><g:message code="devolucion.proveedor.label" default="Proveedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'proveedor', 'errors')}">
                                    <g:select name="proveedor.id" from="${listPrv}" optionKey="id" value="${devolucionInstance?.proveedor?.id}"  
                                               onchange="${remoteFunction(controller:'subas', action:'fillArt', update:'catalogoid', params: '\'proveedor.id=\'+this.value+\'&bodega.id=\'+document.getElementById(\'bodega.id\').value' )}"
                                               noSelection="${['':'']}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="articulo"><g:message code="devolucion.articulo.label" default="Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'articulo', 'errors')}">
                                    <select name="catalogoid" id="catalogoid" title="Articulo" onchange="callData( this.value )"> </select>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fecha"><g:message code="devolucion.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'fecha', 'errors')}">
                                    <input type="text" name="fechaid" id="fechaid" size="10"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="costo"><g:message code="devolucion.costo.label" default="Costo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'costo', 'errors')}">
                                    <g:textField name="costo" value="${fieldValue(bean: devolucionInstance, field: 'costo')}" 
                                                 style="text-align: right; width: 60px" readonly="readonly"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="valor"><g:message code="devolucion.valor.label" default="Valor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'valor', 'errors')}">
                                    <g:textField name="valor" value="${fieldValue(bean: devolucionInstance, field: 'valor')}" 
                                                 style="text-align: right; width: 60px" readonly="readonly"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cantidad"><g:message code="devolucion.cantidad.label" default="Cantidad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: devolucionInstance, field: 'cantidad', 'errors')}">
                                    <g:textField name="cantidad" value="${fieldValue(bean: devolucionInstance, field: 'cantidad')}" 
                                                 style="text-align: right; width: 40px"/>
                                    <g:hiddenField name="articulo.id" id="articulo.id" value="" />
                                </td>
                            </tr>
                                                
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
