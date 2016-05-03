

<%@ page import="com.inventnet.security.SecUser" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Cambiar Contraseña" />
        <title>Cambiar Contraseña</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1>${entityName}</h1>
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
                                  <label for="password"><g:message code="secUser.password.label" default="Contraseña actual" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: secUserInstance, field: 'password', 'errors')}">
                                    <g:passwordField name="password" value="" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password1"><g:message code="secUser.password1.label" default="Nueva contraseña" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: secUserInstance, field: 'password1', 'errors')}">
                                    <g:passwordField name="password1" value="" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password2"><g:message code="secUser.password2.label" default="Confirmar contraseña" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: secUserInstance, field: 'password2', 'errors')}">
                                    <g:passwordField name="password2" value="" />
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
