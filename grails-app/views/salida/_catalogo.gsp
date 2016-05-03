<div class="list">
  <table>
    <thead>
      <tr>      
        <th><g:message code="articulo.codigo.label" default="Código" /></th>
        <th><g:message code="articulo.articulo.label" default="Articulo" /></th>
        <th><g:message code="articulo.stock.label" default="Stock" /></th>
        <th><g:message code="articulo.costo.label" default="Costo" /></th>
        <th><g:message code="articulo.valor.label" default="Valor" /></th>
           </tr>
    </thead>
    <tbody>
    <g:each in="${catalogoList}" status="i" var="catalogoInstance">
      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

        <td>         
          <a href="#" onclick="selectArt( ${i}, this )">
            ${catalogoInstance.articulo.codigo?.trim().encodeAsHTML()}
          </a>     
          <input type="hidden" id="sel_id_${i}" value="${catalogoInstance.id}"/>
          <input type="hidden" id="sel_codigo_${i}" value="${catalogoInstance.articulo.codigo?.trim()}"/>
          <input type="hidden" id="sel_tipo_${i}" value="${catalogoInstance.articulo.tipoArticulo?.detalle?.trim()}"/>
          <input type="hidden" id="sel_genero_${i}" value="${catalogoInstance.articulo.genero?.detalle?.trim()}"/>
          <input type="hidden" id="sel_clase_${i}" value="${catalogoInstance.articulo.claseArticulo?.detalle?.trim()}"/>
          <input type="hidden" id="sel_cualid_${i}" value="${catalogoInstance.articulo.cualidadesArticulo?.detalle?.trim()}"/>
          <input type="hidden" id="sel_ref_${i}" value="${catalogoInstance.articulo.referencia?.trim()}"/>
          <input type="hidden" id="sel_desc_${i}" value="${catalogoInstance.articulo.descripcion?.trim()}"/>          
          <input type="hidden" id="sel_costo_${i}" value="${catalogoInstance.costo}"/>          
          <input type="hidden" id="sel_valor_${i}" value="${catalogoInstance.valor}"/>          
        </td>
        <td>
          ${catalogoInstance.articulo}
        </td>
        <td style="text-align: right">${catalogoInstance.cantidad.encodeAsHTML()}</td>
        <td style="text-align: right">${catalogoInstance.costo.encodeAsHTML()}</td>
        <td style="text-align: right">${catalogoInstance.valor.encodeAsHTML()}</td>
        
      </tr>
    </g:each>
    </tbody>
  </table>

</div>

