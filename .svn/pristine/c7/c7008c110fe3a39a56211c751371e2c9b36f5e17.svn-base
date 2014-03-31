
<%@ page import="invent.net.CierreDiario" %>
<!doctype html>
<html>
	<head>
                <meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'cierreDiario.label', default: 'CierreDiario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
                <div class="nav">
                    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
                    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>          
                </div>
                <div class="body">
          
          
		<div id="list-cierreDiario" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="cierreDiario.bodega.label" default="Bodega" /></th>
					
						<g:sortableColumn property="fecha" title="${message(code: 'cierreDiario.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="control" title="${message(code: 'cierreDiario.control.label', default: 'Control')}" />
					
						<g:sortableColumn property="usuario" title="${message(code: 'cierreDiario.usuario.label', default: 'Usuario')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cierreDiarioInstanceList}" status="i" var="cierreDiarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: cierreDiarioInstance, field: "bodega")}</td>
					
						<td><g:formatDate date="${cierreDiarioInstance.fecha}" format="dd/MM/yyyy"  /></td>
					
						<td><g:formatDate date="${cierreDiarioInstance.control}" format="dd/MM/yyyy hh:mm:ss" /></td>
					
						<td>${fieldValue(bean: cierreDiarioInstance, field: "usuario")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>

                        <div class="paginateButtons">
                            <g:paginate total="${cierreDiarioInstanceTotal}" />
                        </div>
		</div>
                </div>
	</body>
</html>
