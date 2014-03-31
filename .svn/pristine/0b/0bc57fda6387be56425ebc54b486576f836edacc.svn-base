

<%@ page import="invent.net.Inperfectos" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'inperfectos.label', default: 'Inperfectos')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        
        <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" />         
        <g:javascript library="jquery" />
        
    </head>
    <body>
        <g:javascript src="jquery-ui-1.7.3.custom.min.js" />
        <g:javascript src="inperfectos.js" />  
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${inperfectosInstance}">
            <div class="errors">
                <g:renderErrors bean="${inperfectosInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bodega"><g:message code="inperfectos.bodega.label" default="Bodega" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inperfectosInstance, field: 'bodega', 'errors')}">
                                    <g:select name="bodega.id" from="${listBdg}" optionKey="id" value="${inperfectosInstance?.bodega?.id}"
                                              onchange="clearPrv()" noSelection="${['0':' ']}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proveedor"><g:message code="inperfectos.proveedor.label" default="Proveedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inperfectosInstance, field: 'proveedor', 'errors')}">
                                <g:select name="proveedor.id" id="proveedor.id" from="${listPrv}" optionKey="id" 
                                              value="${inperfectosInstance?.proveedor?.id}"  noSelection="${['':'']}"
                                               onchange="${remoteFunction(action:'fillArt', update:'articuloid', params: '\'proveedor.id=\'+this.value+\'&bodega.id=\'+document.getElementById(\'bodega.id\').value' )}"/>                                               
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="articulo"><g:message code="inperfectos.articulo.label" default="Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inperfectosInstance, field: 'articulo', 'errors')}">                                                                      
                                  <select name="articulo.id" id="articuloid" title="Articulo"> </select>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fecha"><g:message code="inperfectos.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inperfectosInstance, field: 'fecha', 'errors')}">
                                    <input type="text" name="fechaid" id="fechaid" size="10"/>
                                </td>
                            </tr>
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipo"><g:message code="inperfectos.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inperfectosInstance, field: 'tipo', 'errors')}">
                                    <g:select name="tipo.id" from="${invent.net.TipoInperfecto.list()}" optionKey="id" value="${inperfectosInstance?.tipo?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cantidad"><g:message code="inperfectos.cantidad.label" default="Cantidad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inperfectosInstance, field: 'cantidad', 'errors')}">
                                  <g:textField name="cantidad" style="text-align: right; width: 40px" 
                                               value="${fieldValue(bean: inperfectosInstance, field: 'cantidad')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="observaciones"><g:message code="inperfectos.observaciones.label" default="Observaciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: inperfectosInstance, field: 'observaciones', 'errors')}">                                    
                                    <g:textArea name="observaciones" maxlength="100" rows="3" cols="40"
                                                value="${inperfectosInstance?.observaciones}" />
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
