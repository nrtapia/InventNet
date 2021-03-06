<div class="list">
    <form id="formCatalogo">
        <div style="text-align: center; color: #48802c; font-weight: bold; background-color: #eaeaed">Articulos del Catalogo</div>
        <table>
            <thead>
            <tr>
                <th><g:message code="articulo.tipoArticulo.label" default="Tipo"/></th>

                <th><g:message code="articulo.codigo.label" default="Código"/></th>

                <th><g:message code="articulo.genero.label" default="Genero"/></th>

                <th><g:message code="articulo.claseArticulo.label" default="Clase"/></th>

                <th><g:message code="articulo.cualidadesArticulo.label" default="Cualidades"/></th>

                <th><g:message code="articulo.ref.label" default="Ref."/></th>

                <th><g:message code="articulo.descripcion.label" default="Descripción"/></th>

                <th><g:message code="articulo.costo.label" default="Costo"/></th>

                <th><g:message code="articulo.cant.label" default="Cant."/></th>

                <th><g:message code="articulo.valor.label" default="Valor"/></th>

                <th><g:message code="catalogo.stockmin.label" default="Min."/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${catalogoList}" status="i" var="catalogoInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td>
                        ${catalogoInstance.articulo.tipoArticulo?.detalle?.trim().encodeAsHTML()}
                    </td>

                    <td>
                        <a href="#" onclick="deleteCatalogo(${catalogoInstance.id}, this)">
                            ${catalogoInstance.articulo.codigo?.trim().encodeAsHTML()}
                        </a>
                    </td>

                    <td>${catalogoInstance.articulo.genero?.detalle?.trim().encodeAsHTML()}</td>

                    <td>${catalogoInstance.articulo.claseArticulo?.detalle?.trim().encodeAsHTML()}</td>

                    <td>${catalogoInstance.articulo.cualidadesArticulo?.detalle?.trim().encodeAsHTML()}</td>

                    <td>${catalogoInstance.articulo.referencia?.trim().encodeAsHTML()}</td>

                    <td>${catalogoInstance.articulo.descripcion?.trim().encodeAsHTML()}</td>

                    <td style="text-align: right">${catalogoInstance.costo?.encodeAsHTML()}</td>

                    <td style="text-align: right">${catalogoInstance.cantidad?.encodeAsHTML()}</td>

                    <td style="text-align: right">
                        <g:hiddenField name="idvalues" id="idvalues" value="${catalogoInstance.id}"/>

                        <sec:ifAnyGranted roles="ROLE_CATALOGO">
                            <g:textField name="values" id="values" value="${catalogoInstance.valor}"
                                         style="text-align: right; width: 70px"/>
                        </sec:ifAnyGranted>
                        <sec:ifNotGranted roles="ROLE_CATALOGO">
                            ${catalogoInstance.valor?.encodeAsHTML()}
                        </sec:ifNotGranted>
                    </td>

                    <td>
                        <g:textField name="stockmin" id="stockmin" value="${catalogoInstance.stockmin}"
                                     style="text-align: right; width: 30px"/>
                    </td>

                </tr>
            </g:each>
            </tbody>
        </table>

    </form>
</div>

