

<%@ page import="com.inventnet.security.SecUser" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'secUser.label', default: 'SecUser')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>            
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${secUserInstance}">
            <div class="errors">
                <g:renderErrors bean="${secUserInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${secUserInstance?.id}" />
                <g:hiddenField name="version" value="${secUserInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="username"><g:message code="secUser.username.label" default="Username" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: secUserInstance, field: 'username', 'errors')}">
                                    ${secUserInstance?.username}
                                </td>
                            </tr>
                                               
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bodegas"><g:message code="secUser.bodegas.label" default="Bodegas" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: secUserInstance, field: 'bodegas', 'errors')}">
                                    <g:select name="bodegas" from="${invent.net.Bodega.list()}" multiple="yes" 
                                              optionKey="id" size="20" value="${secUserInstance?.bodegas*.id}" 
                                              noSelection="${['': '--']}"/>
                                </td>
                            </tr>
                       
                          
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
