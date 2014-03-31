

<%@ page import="invent.net.Subas" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'subas.label', default: 'Subas')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        
        <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" /> 
        
        <g:javascript library="jquery" />
        
    </head>
    <body>
        <g:javascript src="jquery-ui-1.7.3.custom.min.js" />
        <g:javascript src="inperfectos.js" />  
        <g:javascript src="util.js" />  
        
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${subasInstance}">
            <div class="errors">
                <g:renderErrors bean="${subasInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bodega"><g:message code="subas.bodega.label" default="Bodega" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'bodega', 'errors')}">
                                    <g:select name="bodega.id" from="${listBdg}" optionKey="id" value="${subasInstance?.bodega?.id}"  
                                              onchange="clearPrv()" noSelection="${['0':' ']}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proveedor"><g:message code="subas.proveedor.label" default="Proveedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'proveedor', 'errors')}">
                                    <g:select name="proveedor.id" from="${listPrv}" optionKey="id" 
                                              value="" noSelection="${['':'']}"
                                              onchange="${remoteFunction(controller:'subas', action:'fillArt', update:'catalogoid', params: '\'proveedor.id=\'+this.value+\'&bodega.id=\'+document.getElementById(\'bodega.id\').value' )}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="articulo"><g:message code="subas.articulo.label" default="Articulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'articulo', 'errors')}">
                                    <select name="catalogoid" id="catalogoid" onchange="callData( this.value )">                                      
                                    </select>
                                    <g:hiddenField name="articulo.id" id="articulo.id" value="" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fecha"><g:message code="subas.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'fecha', 'errors')}">
                                    <input type="text" name="fechaid" id="fechaid" size="10"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipo"><g:message code="subas.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'tipo', 'errors')}">
                                    <g:select name="tipo" from="${subasInstance.constraints.tipo.inList}" value="${subasInstance?.tipo}" valueMessagePrefix="subas.tipo"  />
                                </td>
                            </tr>
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cantidad"><g:message code="subas.cantidad.label" default="Cantidad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'cantidad', 'errors')}">
                                  <g:textField name="cantidad" id="cantidad" value="${fieldValue(bean: subasInstance, field: 'cantidad')}" 
                                                 style="text-align: right; width: 40px" readonly="readonly" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="valorAnterior"><g:message code="subas.valoranterior.label" default="Valor anterior" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'valor', 'errors')}">
                                  <g:textField name="valorAnterior" id="valorAnterior" value="${fieldValue(bean: subasInstance, field: 'valorAnterior')}" 
                                               style="text-align: right;  width: 60px" readonly="readonly"  />
                                </td>
                            </tr>
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="valor"><g:message code="subas.valor.label" default="Nuevo valor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: subasInstance, field: 'valor', 'errors')}">
                                    <g:textField name="valor" value="${fieldValue(bean: subasInstance, field: 'valor')}" 
                                                 style="text-align: right;  width: 60px" />
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
