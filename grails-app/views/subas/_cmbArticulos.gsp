<option value=""></option> 
<g:each var="row" in="${artList}">
    <option value="${row.id}">${row.articulo}   - Cant. ${row.cantidad} Valor ${row.valor}</option> 
</g:each> 