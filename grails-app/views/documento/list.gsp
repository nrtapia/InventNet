
<%@ page import="invent.net.Documento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <filterpane:includes />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><filterpane:filterButton /></span>
        </div>

        <filterpane:filterPane domain="invent.net.Documento"
            excludeProperties="control,dateCreated,usuario,lastUpdated,bodega"
            dialog="true"
        />

        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">

                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'documento.id.label', default: 'Id')}" />
                        
                            <th><g:message code="documento.bodega.label" default="Bodega" /></th>
                        
                            <th><g:message code="documento.tipoDocumento.label" default="Tipo Documento" /></th>

                            <g:sortableColumn property="fecha" title="${message(code: 'documento.fecha.label', default: 'Fecha')}" params="${filterParams}" />

                            <g:sortableColumn property="ref1" title="${message(code: 'documento.ref1.label', default: 'Ref1')}"  params="${filterParams}"/>

                            <g:sortableColumn property="ref2" title="${message(code: 'documento.ref2.label', default: 'Ref2')}" params="${filterParams}" />

                            <g:sortableColumn property="ref3" title="${message(code: 'documento.ref3.label', default: 'Ref3')}" params="${filterParams}" />

                            <g:sortableColumn property="ref4" title="${message(code: 'documento.ref4.label', default: 'Ref4')}" params="${filterParams}" />


                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${documentoInstanceList}" status="i" var="documentoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${documentoInstance.id}">${fieldValue(bean: documentoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: documentoInstance, field: "bodega")}</td>
                        
                            <td>${fieldValue(bean: documentoInstance, field: "tipoDocumento")}</td>

                            <td><g:formatDate date="${documentoInstance.fecha}" format="yyyy-MM-dd" /></td>
                        
                            <td>${fieldValue(bean: documentoInstance, field: "ref1")}</td>
                        
                            <td>${fieldValue(bean: documentoInstance, field: "ref2")}</td>

                            <td>${fieldValue(bean: documentoInstance, field: "ref3")}</td>

                            <td>${fieldValue(bean: documentoInstance, field: "ref4")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${documentoInstanceTotal}" />
                <filterpane:paginate total="${documentoInstanceTotal}" domainBean="invent.net.Documento" />
            </div>
        </div>
    </body>
</html>
