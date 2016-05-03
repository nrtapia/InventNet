

<%@ page import="invent.net.CierreDiario" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cierreDiario.label', default: 'Cierre Diario')}" />
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
            <g:hasErrors bean="${cierreDiarioInstance}">
            <div class="errors">
                <g:renderErrors bean="${cierreDiarioInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bodega"><g:message code="cierreDiario.bodega.label" default="Sucursal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cierreDiarioInstance, field: 'bodega', 'errors')}">
                                    <g:select name="bodega.id" from="${lstbdg}" optionKey="id" value="${cierreDiarioInstance?.bodega?.id}"  
                                              />
                                </td>
                            </tr>
                           
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fecha"><g:message code="cierreDiario.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cierreDiarioInstance, field: 'fecha', 'errors')}">
                                    <input type="text" name="fechaid" id="fechaid" size="10"/>
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
