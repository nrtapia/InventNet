
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Indicador de Rotaci&oacute;n" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style type="text/css">
          .number {
            text-align: right;            
          }
        </style>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link action="index" class="list">Atras</g:link></span>
        </div>
        <div class="body">
            <h1>Indicador de Rotaci&oacute;n</h1>
        
            <table>
              <tr>
                <td>
                  <div class="list">
                    <table style="width: 270px">                    
                          <tbody>
                              <tr class="odd">                       
                                <th>Bodega: </th>
                                <td>${data?.bodega}</td>
                              </tr>
                              <tr class="even">                       
                                <th>Proveedor: </th>
                                <td>${data?.proveedor}</td>
                              </tr>
                              <tr class="even">                       
                                <th>Articulo: </th>
                                <td>${data?.articulo}</td>
                              </tr>
                              <tr class="odd">                       
                                <th>Rango: </th>
                                <td>${data?.fechas}</td>
                              </tr>
                              <tr class="even">                       
                                <th>D&iacute;as: </th>
                                <td class="number">${data?.dias}</td>
                              </tr>
                              <tr class="odd">                       
                                <th>Compras: </th>
                                <td class="number">
                                   <g:formatNumber number="${data?.compras}" type="number"/>
                                </td>
                              </tr>
                              <tr class="even">                       
                                <th>Inv. Inicial: </th>
                                <td class="number">
                                   <g:formatNumber number="${data?.ivnInicial}" type="number"/>
                                </td>
                              </tr>
                              <tr class="odd">                       
                                <th>Inv. Final: </th>
                                <td class="number">
                                  <g:formatNumber number="${data?.ivnFinal}" type="number"/>
                                </td>
                              </tr>
                              <tr class="even">                       
                                <th>Costo Ventas: </th>
                                <td class="number">
                                  <g:formatNumber number="${data?.ctoVentas}" type="number"/>
                                </td>
                              </tr>
                              <tr class="odd">                       
                                <th>Rotaci&oacute;n: </th>
                                <td class="number">
                                  <g:formatNumber number="${data?.rotacion}" type="number"/>
                                </td>
                              </tr>
                          </tbody>
                      </table>
                  </div>                
                </td>
                <td>
                  <img src="../charts/${image}" > 
                </td>
              </tr>
            </table>
               
        </div>
    </body>
</html>
