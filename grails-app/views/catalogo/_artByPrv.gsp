<div class="list">
  <table id="listArt">
  <thead>
    <tr>            
      <th><g:message code="articulo.tipoArticulo.label" default="Tipo" /></th>
      
      <th><g:message code="articulo.codigo.label" default="Código" /></th>
            
      <th><g:message code="articulo.genero.label" default="Genero" /></th>
      
      <th><g:message code="articulo.claseArticulo.label" default="Clase" /></th>

      <th><g:message code="articulo.cualidadesArticulo.label" default="Cualidades" /></th>
      
      <th><g:message code="articulo.ref.label" default="Ref." /></th>
      
      <th><g:message code="articulo.descripcion.label" default="Descripción" /></th>
      
     </tr>
  </thead>
  <tbody>
    <g:each in="${articuloInstanceList}" status="i" var="articuloInstance">
      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

        <td>
           ${articuloInstance[0].tipoArticulo?.detalle?.trim().encodeAsHTML()}
        </td>
    
        <td>
          <a href="#" onclick="addArticulo( ${articuloInstance[0].id}, this )">
            ${articuloInstance[0].codigo?.trim().encodeAsHTML()}
          </a>
        </td>

        <td>${articuloInstance[0].genero?.detalle?.trim().encodeAsHTML()}</td>
        
        <td>${articuloInstance[0].claseArticulo?.detalle?.trim().encodeAsHTML()}</td>

        <td>${articuloInstance[0].cualidadesArticulo?.detalle?.trim().encodeAsHTML()}</td>
        
        <td>${articuloInstance[0].referencia?.trim().encodeAsHTML()}</td>
        
        <td>${articuloInstance[0].descripcion?.trim().encodeAsHTML()}</td>
        
      </tr>
    </g:each>
  </tbody>
  </table>
</div>

