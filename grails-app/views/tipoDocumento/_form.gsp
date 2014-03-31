<%@ page import="invent.net.TipoDocumento" %>



<div class="fieldcontain ${hasErrors(bean: tipoDocumentoInstance, field: 'codigo', 'error')} ">
	<label for="codigo">
		<g:message code="tipoDocumento.codigo.label" default="Codigo" />
		
	</label>
	<g:textField name="codigo" maxlength="3" value="${tipoDocumentoInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoDocumentoInstance, field: 'detalle', 'error')} ">
	<label for="detalle">
		<g:message code="tipoDocumento.detalle.label" default="Detalle" />
		
	</label>
	<g:textField name="detalle" maxlength="60" value="${tipoDocumentoInstance?.detalle}"/>
</div>

