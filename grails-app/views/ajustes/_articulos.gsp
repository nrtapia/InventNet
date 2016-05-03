<div class="list">

    <table>
        <thead>
        <tr>
            <th><g:message code="articulo.codigo.label" default="Código"/></th>
            <th><g:message code="articulo.tipo.label" default="Tipo"/></th>
            <th><g:message code="articulo.genero.label" default="Genero"/></th>
            <th><g:message code="articulo.claseArticulo.label" default="Clase"/></th>
            <th><g:message code="articulo.cualidadesArticulo.label" default="Cualidades"/></th>
            <th><g:message code="articulo.ref.label" default="Ref."/></th>
            <th><g:message code="articulo.descripcion.label" default="Descripción"/></th>
            <th><g:message code="articulo.costo.label" default="Costo"/></th>
            <th><g:message code="articulo.valor.label" default="Valor"/></th>
            <th><g:message code="articulo.cantidad.label" default="Cant."/></th>
            <g:if test="${movement.ref}">
                <th width="50px"><g:message code="catalogo.ref.label" default="Ref."/></th>
            </g:if>
        </tr>
        </thead>
        <tbody>
        <g:each in="${catalogoList}" status="i" var="catalogoInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td>
                    <a href="#"
                       onclick="javascript:selectArt(${catalogoInstance.id}, '${catalogoInstance.articulo.codigo?.trim()}', ${catalogoInstance.cantidad}, this)"
                       title="Ajustar">
                        ${catalogoInstance.articulo.codigo?.trim().encodeAsHTML()}
                    </a>
                    <cys:articuloFoto art="${catalogoInstance.articulo}" parent="${catalogoInstance.id}"/>
                </td>

                <td>${catalogoInstance.articulo.tipoArticulo?.detalle?.trim().encodeAsHTML()}</td>

                <td>${catalogoInstance.articulo.genero?.detalle?.trim().encodeAsHTML()}</td>

                <td>${catalogoInstance.articulo.claseArticulo?.detalle?.trim().encodeAsHTML()}</td>

                <td>${catalogoInstance.articulo.cualidadesArticulo?.detalle?.trim().encodeAsHTML()}</td>

                <td>${catalogoInstance.articulo.referencia?.trim().encodeAsHTML()}</td>

                <td>${catalogoInstance.articulo.descripcion?.trim().encodeAsHTML()}</td>

                <td style="text-align: right;">${catalogoInstance.costo?.encodeAsHTML()}</td>

                <td style="text-align: right;">${catalogoInstance.valor?.encodeAsHTML()}</td>

                <td style="text-align: right;">${catalogoInstance.cantidad?.encodeAsHTML()}</td>

                <g:if test="${movement.ref}">
                    <td>
                        <div class="editable" title="Click para modificar">
                            <label data-type="editable" data-for=".ref-field-${catalogoInstance.id}" style="display: inline; cursor: pointer;">
                                ${catalogoInstance.ref?.trim()}
                            </label>
                            <input class="ref-field-${catalogoInstance.id}"
                                   value="${catalogoInstance.ref?.trim()}"
                                   data-id="${catalogoInstance.id}"
                                   data-old="${catalogoInstance.ref?.trim()}"
                                   maxlength="10"
                                   style="display: none; width: 40px">
                        </div>
                    </td>
                </g:if>

            </tr>
        </g:each>
        </tbody>
    </table>

</div>

