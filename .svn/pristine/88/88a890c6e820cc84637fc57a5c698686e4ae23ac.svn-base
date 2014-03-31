<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->


<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>Salidas</title>

    <link rel="stylesheet" href="${resource(dir:'css', file: 'salida.css')}" /> 
    <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" /> 
    <link rel="stylesheet" href="${resource(dir:'css',file:'jquery/jquery.ui.autocomplete.css')}" />
    
    <style>
      .ui-datepicker{
        z-index: 10;
      }
      
    </style>
    

  <g:javascript library="jquery" />
  
</head>
<body>  
  <g:javascript src="jquery/jquery.ui.core.js" />
  <g:javascript src="jquery/jquery.ui.widget.js" />
  <g:javascript src="jquery-ui-1.7.3.custom.min.js" />
  <g:javascript src="jquery/jquery.ui.position.js" />
  <g:javascript src="jquery/jquery.ui.autocomplete.js" />
  <g:javascript src="util.js" />   
  <g:javascript src="salida.js" />  
  
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>            
  </div>
  <div class="body">
    <h1>Salidas</h1>
    <g:if test="${flash.message}">
      <div id="message" class="message">${flash.message}</div>
    </g:if>
  </div>

  <br/>
  <form id="formCatalogo">

    <div id="fecha"><span class="label-custom">Fecha : </span>    
      <label>
        <input type="text" name="fechavalue" id="fechaid" size="10"/>
      </label>
    </div>

    <div id="sucursal">
      <span class="label-custom">Sucursal : </span> &nbsp; 
      <g:select name="bodegaid" from="${listBdg}" optionKey="id" value=""  />
    </div>

    <div id="proveedor">      
    </div>

    <div id="movimiento">
      <span class="label-custom">Movimiento : </span>
      <g:select name="movetypeid" from="${listMov}"  optionKey="id" value=""  />        
    </div>


    <div id="lista" class="borde">
      <table id="table-data">
        <thead>
          <tr>            
            <th>Articulo</th>
            <th>Proveedor</th>
            <th>Stock</th>
            <th>Valor</th>
            <th>Cant.</th>
            <th>Ref.</th>
          </tr>
        </thead>
        <tbody>
          <tr class="item-row">            
            <td> 
              <g:textField name="art" id="art" value="" class="articulo"/>  
              <g:hiddenField name="ctl" id="ctl" value="" />              
            </td>  
            <td> <g:textField name="prv" id="prv" value="" class="proveedor" />  </td>
            <td> <g:textField name="stock" class="stock" value="" disabled="disabled" style="text-align: right"/>  </td>
            <td> <g:textField name="val"  value="" disabled="disabled" class="valor"/>  </td>
            <td> <g:textField name="cant" id="cant" value="" class="stock"  />  </td>
            <td> <g:textField name="ref" id="ref" value="" class="ref"  />  </td>
          </tr>         
        </tbody>
      </table>
    </div>

  </form>

  <div id="footer">    
  </div>


  <div id="botones" >
    <input type="button" name="save" id="save" value="Guardar" onclick="updateCatalogo()" />    
  </div> 
</body>