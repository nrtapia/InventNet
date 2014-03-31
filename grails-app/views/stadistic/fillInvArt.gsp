
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="Estadistica Inventario x Articulo" />
  <title>Estadistica Inventario x Articulo</title>

</head>
<body>

  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>    
    <span class="menuButton"><g:link action="index" class="list">Atras</g:link></span>
  </div>
  <div class="body">

    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">

      <table>
        <thead>
          <tr>
            <th>Sucursal</th>
            <th>Articulo</th>
            <th>Fecha</th>
            <th>Cant.</th>
            <th>Costo</th>
            <th>Valor</th>
          </tr>
        </thead>
        <tbody>
          <% 
          def sumval = 0
          def sumcant = 0
          def sumcost = 0
          %>
        <g:each in="${listresult}" status="i" var="row">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>${row.bodega}</td>
            <td>${row.articulo}</td>
            <td>
                <g:formatDate format="yyyy-MM-dd" date="${row.fecha}" />
            </td>
            <td style="text-align: right">
              <g:formatNumber number="${row.cantidad}" type="number" />
            </td>
            <td style="text-align: right">            
              <g:formatNumber number="${row.costo}" type="number" />
            </td>
            <td style="text-align: right">            
              <g:formatNumber number="${row.valor}" type="number" />
              <% 
              sumval = sumval + row.valor
              sumcant = sumcant + row.cantidad
              sumcost = sumcost + row.costo
              %>
            </td>
            </td>
          </tr>
        </g:each>
        <tr style="background-color: #C0C0C0">
           <td>&nbsp</td>
           <td>&nbsp</td>
           <td style="text-align: right; font-weight: bold ">TOTAL</td>
           <td style="text-align: right; font-weight: bold "><g:formatNumber number="${sumcant}" type="number"/></td>
           <td style="text-align: right; font-weight: bold "><g:formatNumber number="${sumcost}" type="number"/></td>
           <td style="text-align: right; font-weight: bold "><g:formatNumber number="${sumval}" type="number"/></td>
        </tr>
        </tbody>
      </table>
      
      
    </div>
  </div>

</body>
</html>
