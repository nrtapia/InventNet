
<%@ page import="invent.net.Bodega" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="Estadistica Articulos" />
  <title>Estadistica Articulos</title>

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
            <th>Articulo</th>
            <th>Proveedor</th>
            <th>Cant.</th>
            <th>Total</th>
            <th>%</th>
          </tr>
        </thead>
        <tbody>
          <% def sumval = 0 %>
        <g:each in="${listart}" status="i" var="row">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <% sumval = sumval + row.valor  %>
            <td>${row.articulo}</td>
            <td>${row.proveedor}</td>
            <td style="text-align: right">${row.cantidad}</td>
            <td style="text-align: right">            
              <g:formatNumber number="${row.valor}" type="number" />
            </td>
            <td style="text-align: right">${row.porcentaje}</td>
          </tr>
        </g:each>
        <tr style="background-color: #C0C0C0">
           <td>&nbsp</td>
           <td style="text-align: right; font-weight: bold ">TOTAL</td>
           <td style="text-align: right; font-weight: bold ">${sumcant}</td>
           <td style="text-align: right; font-weight: bold "><g:formatNumber number="${sumval}" type="number"/></td>
           <td>&nbsp</td>
        </tr>
        </tbody>
      </table>
      
      
    </div>
  </div>

</body>
</html>
