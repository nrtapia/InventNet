
<%@ page import="invent.net.Documento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>


            <div class="dialog">
                <table style="width: 700px">
                    <tbody>

                    <tr class="">
                        <td valign="top" class="">
                            <label for="bodega"><g:message code="documento.bodega.label" default="Bodega" /></label>
                        </td>
                        <td valign="top" class=""> ${documentoInstance.bodega?.toString()?.trim().encodeAsHTML()} </td>

                        <td valign="top" class="">
                            <label for="proveedor"><g:message code="documento.proveedor.label" default="Proveedor" /></label>
                        </td>
                        <td valign="top" class=""> ${fieldValue(bean: documentoInstance, field: 'proveedor')} </td>

                        <td valign="top" class="">
                            <label for="tipoDocumento"><g:message code="documento.tipoDocumento.label" default="Tipo" /></label>
                        </td>
                        <td valign="top" class="">
                            ${fieldValue(bean: documentoInstance, field: 'tipoDocumento')}
                        </td>
                    </tr>

                    <tr class="">
                        <td valign="top" class="">
                            <label for="fechaui"><g:message code="documento.fecha.label" default="Fecha" /></label>
                        </td>
                        <td valign="top" class="" style="width: 100px">
                            <g:formatDate date="${documentoInstance.fecha}" format="yyyy-MM-dd" />
                        </td>

                        <td valign="top" class="">
                            <label for="ref2"><g:message code="documento.ref2.label" default="Ref2" /></label>
                        </td>
                        <td valign="top" class="">
                            ${fieldValue(bean: documentoInstance, field: 'ref2')}
                        </td>

                        <td valign="top" class="">
                            <label for="ref1"><g:message code="documento.ref1.label" default="Ref1" /></label>
                        </td>
                        <td valign="top" class="">
                            ${fieldValue(bean: documentoInstance, field: 'ref1')}
                        </td>

                    </tr>

                    <tr class="prop">

                        <td valign="top" class="name">
                            <label for="ref3"><g:message code="documento.ref3.label" default="Ref3" /></label>
                        </td>
                        <td valign="top" class="">
                            ${fieldValue(bean: documentoInstance, field: 'ref3')}
                        </td>

                        <td valign="top" class="name">
                            <label for="ref4"><g:message code="documento.ref4.label" default="Ref4" /></label>
                        </td>
                        <td valign="top" class="">
                            ${fieldValue(bean: documentoInstance, field: 'ref4')}
                        </td>

                        <td> &nbsp; </td>
                        <td> &nbsp; </td>
                    </tr>

                    <tr>
                        <td colspan="6">
                            <table id="table-data">
                                <thead>
                                <tr>
                                    <th>C&oacute;digo</th>
                                    <th>Articulo</th>
                                    <th>Cant.</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <g:each in="${documentoInstance.articulosxDocumentos}" var="item">
                                        <tr>
                                            <td>  ${item.articulo.codigo?.encodeAsHTML()} </td>
                                            <td> ${item.articulo?.encodeAsHTML()} </td>
                                            <td> ${item.cantidad?.encodeAsHTML()} </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>


            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${documentoInstance?.id}" />
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
