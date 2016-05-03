

<%@ page import="invent.net.AutorizaCierre" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'autorizaCierre.label', default: 'AutorizaCierre')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${autorizaCierreInstance}">
            <div class="errors">
                <g:renderErrors bean="${autorizaCierreInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bodega"><g:message code="autorizaCierre.bodega.label" default="Bodega" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: autorizaCierreInstance, field: 'bodega', 'errors')}">
                                    <g:select name="bodega.id" from="${invent.net.Bodega.list()}" optionKey="id" value="${autorizaCierreInstance?.bodega?.id}"  />
                                </td>
                            </tr>
                                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fecha"><g:message code="autorizaCierre.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: autorizaCierreInstance, field: 'fecha', 'errors')}">
                                    <g:datePicker name="fecha" precision="day" value="${autorizaCierreInstance?.fecha}"  />
                                </td>
                            </tr>
                       
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="observaciones"><g:message code="autorizaCierre.observaciones.label" default="Observaciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: autorizaCierreInstance, field: 'observaciones', 'errors')}">
                                    <g:textArea name="observaciones" maxlength="200" rows="3" cols="40"
                                                value="${autorizaCierreInstance?.observaciones}" />
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
