<div class="list">
    
  <table id="table-articulos">
      <thead>
        <tr>               
          <th><g:message code="articulo.tipoArticulo.label" default="Tipo" /></th>

      <th><g:message code="articulo.codigo.label" default="Código" /></th>

      <th><g:message code="articulo.genero.label" default="Genero" /></th>

      <th><g:message code="articulo.claseArticulo.label" default="Clase" /></th>

      <th><g:message code="articulo.cualidadesArticulo.label" default="Cualidades" /></th>

      <th><g:message code="articulo.ref.label" default="Ref." /></th>

      <th><g:message code="articulo.descripcion.label" default="Descripción" /></th>

      <th><g:message code="articulo.costo.label" default="Costo" /></th>
      
      <th><g:message code="articulo.valor.label" default="Valor" /></th>

      <th><g:message code="articulo.cantidad.label" default="Cant." /></th>
      
      <g:if test="${movement.ref}"> 
          <th><g:message code="catalogo.ref.label" default="Ref." /></th>
      </g:if>
      
      </tr>
      </thead>
      <tbody>
      <g:each in="${catalogoList}" status="i" var="catalogoInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td>
              ${catalogoInstance.articulo.tipoArticulo?.detalle?.trim().encodeAsHTML()}
              <cys:articuloFoto art="${catalogoInstance.articulo}" parent="${catalogoInstance.id}"/>
          </td>

          <td>${catalogoInstance.articulo.codigo?.trim().encodeAsHTML()}</td>

          <td>${catalogoInstance.articulo.genero?.detalle?.trim().encodeAsHTML()}</td>

          <td>${catalogoInstance.articulo.claseArticulo?.detalle?.trim().encodeAsHTML()}</td>

          <td>${catalogoInstance.articulo.cualidadesArticulo?.detalle?.trim().encodeAsHTML()}</td>

          <td>${catalogoInstance.articulo.referencia?.trim().encodeAsHTML()}</td>

          <td>${catalogoInstance.articulo.descripcion?.trim().encodeAsHTML()}</td>

          <td style="text-align: right;" >${catalogoInstance.costo?.encodeAsHTML()}</td>
          
          <td style="text-align: right;" >${catalogoInstance.valor?.encodeAsHTML()}</td>
          <td>                        
            <g:hiddenField name="idvalues" id="idvalues" value="${catalogoInstance.id}" />
            <g:textField name="cant" id="cant" value="0" style="text-align: right; width: 30px"  />
          </td>
          
          <g:if test="${movement.ref}">
            <td>  <g:textField name="ref" style="width: 60px" /> </td>
          </g:if>
        </tr>
      </g:each>
      </tbody>
    </table>

  
</div>

