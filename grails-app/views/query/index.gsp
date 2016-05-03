<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.*" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <title>Asistente de Consultas</title>

    <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" /> 

  <g:javascript library="jquery" />
  
</head>
<body>
  <g:javascript src="jquery-ui-1.7.3.custom.min.js" />
  <g:javascript src="util.js" />  
  <g:javascript src="query.js" /> 
  
  
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>    
  </div>
  <div class="body">
    <h1>Consultas</h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <g:form action="print" >
      <div class="dialog">
        <g:form name="myForm" action="print">
          <table>
            <tbody>
              <tr class="prop">
                <td valign="top" class="name">
                  <label for="codigo">Consulta</label>
                </td>
                <td valign="top">
            <g:select name="query" from="${listQry}" optionKey="key" optionValue="${{it.value}}" />
                        
            </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="bodegaid">Sucursal</label>
              </td>
              <td valign="top">
            <g:select name="bodegaid" from="${listBdg}" optionKey="id" value="" />
            </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="proveedorid">Proveedor</label>
              </td>
              <td valign="top">
            <g:select name="proveedorid" from="${listPrv}"  optionKey="id" value=""  style="width: 238px;"
                      noSelection="${['':'Todos...']}"/>
            </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="movetypeid">Movimiento</label>
              </td>
              <td valign="top">
            <g:select name="movetypeid" from="${listMov}"  optionKey="id" value="id"  
                      noSelection="${['':'Todos...']}"/>
            </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="fechavalue">Desde</label>
              </td>
              <td valign="top">
                <input type="text" name="fechabegin" id="fechabegin" size="10" />
                &nbsp;&nbsp; Hasta   &nbsp;
                <input type="text" name="fechaend" id="fechaend" size="10" />
              </td>
            </tr>          
            <tr>
              <td>&nbsp;</td>
              <td style="text-align: right">
                <g:submitButton name="option" value="Exportar" />&nbsp;
                <g:submitButton name="option" value="Consultar" />
              </td>
            </tr>
            </tbody>
          </table>
        </g:form>
        
      </div>
    </g:form>
  </div>
</body>
</html>
