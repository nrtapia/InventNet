

<%@ page import="invent.net.Documento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        
        <link rel="stylesheet" href="${resource(dir:'css/ui-lightness', file: 'jquery-ui-1.7.3.custom.css')}" /> 
        <link rel="stylesheet" href="${resource(dir:'css', file: 'salida.css')}" />
        <g:javascript src="jquery-1.7.1.min.js" />
      </head>
      <body>  

        <g:javascript src="jquery-ui-1.7.3.custom.min.js" />        
        <g:javascript src="jquery.validate.js" />
        <g:javascript src="documento.js" />
        <g:javascript src="util.js" />

        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${documentoInstance}">
            <div class="errors">
                <g:renderErrors bean="${documentoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" name="docform">
                <div class="dialog">
                  <table style="width: 700px">
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bodega"><g:message code="documento.bodega.label" default="Bodega" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'bodega', 'errors')}">
                                    <g:select name="bodega.id" from="${invent.net.Bodega.list()}" optionKey="id" value="${documentoInstance?.bodega?.id}"  />
                                </td>
                                
                                <td valign="top" class="name">
                                    <label for="proveedor"><g:message code="documento.proveedor.label" default="Proveedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'proveedor', 'errors')}">
                                    <g:select name="proveedor.id" from="${invent.net.Proveedor.list()}" optionKey="id" value="${documentoInstance?.proveedor?.id}"  />
                                </td>
                                
                                <td valign="top" class="name">
                                    <label for="tipoDocumento"><g:message code="documento.tipoDocumento.label" default="Tipo Documento" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'tipoDocumento', 'errors')}">
                                    <g:select name="tipoDocumento.id" from="${invent.net.TipoDocumento.list()}" optionKey="id" value="${documentoInstance?.tipoDocumento?.id}"  />
                                </td>
                                
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fechaui"><g:message code="documento.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'fecha', 'errors')}">                                    
                                   <g:textField name="fechaui" maxlength="10" id="fechaui" class="required" value="${documentoInstance?.fecha}" />
                                </td>
                                
                                <td valign="top" class="name">
                                    <label for="ref2"><g:message code="documento.ref2.label" default="Ref2" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'ref2', 'errors')}">
                                    <g:textField name="ref2" maxlength="20" class="required"  value="${documentoInstance?.ref2}" />
                                </td>

                                <td valign="top" class="name">
                                    <label for="ref1"><g:message code="documento.ref1.label" default="Ref1" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'ref1', 'errors')}">
                                    <g:textField name="ref1" maxlength="20"  class="required"  value="${documentoInstance?.ref1}" />
                                </td>

                            </tr>
                        
                            <tr class="prop">
                                
                                <td valign="top" class="name">
                                    <label for="ref3"><g:message code="documento.ref3.label" default="Ref3" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'ref3', 'errors')}">
                                    <g:textField name="ref3" maxlength="20" class="required"  value="${documentoInstance?.ref3}" />
                                </td>
                                
                                <td valign="top" class="name">
                                    <label for="ref4"><g:message code="documento.ref4.label" default="Ref4" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoInstance, field: 'ref4', 'errors')}">
                                    <g:textField name="ref4" maxlength="20"  class="required"  value="${documentoInstance?.ref4}" />
                                </td>

                                <td> &nbsp; </td>
                                <td> &nbsp; </td>
                            </tr>
                        
                            <tr>
                              <td colspan="6"> 
                                <table id="table-data">
                                  <thead> 
                                    <tr>
                                      <th>C&oacute;digo</th>
                                      <th>Articulo</th>
                                      <th>Stock</th>
                                      <th>Valor</th>
                                      <th>Cant.</th>                                    
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <tr class="item-row">   
                                      <td> 
                                          <g:textField name="art" id="art" value="" class="articulo"/>  
                                          <g:hiddenField name="artid" id="artid" value="" />          
                                      </td>
                                      <td> <input type="text" id="artnom" name="artnom" style="width: 300px" readonly="true"/> </td>
                                      <td> <g:textField name="stock" class="stock" value="" disabled="disabled" /></td>
                                      <td> <g:textField name="val"  value="" disabled="disabled" class="valor"/>  </td>                                    
                                      <td> <g:textField name="cant" id="cant" value="0" class="stock sumcant number"  /> </td>
                                    </tr>
                                    
                                   </tbody>
                                 </table>
                              </td>                                
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save submit" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
