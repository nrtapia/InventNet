<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'catalogo.label', default: 'Catalogo')}" />
  <title>Catalogo</title>


  <link rel="stylesheet" href="${resource(dir:'plugins/extjs4-4.0.7.0/ext/resources/css', file: 'ext-all-gray.css')}" />
  <script type="text/javascript" src="${resource(dir:'plugins/extjs4-4.0.7.0/ext', file: 'bootstrap.js')}"></script>

  <script type="text/javascript">
  Ext.onReady(function() {
    
    Ext.QuickTips.init();
    
    
    var msgMask = new Ext.LoadMask(Ext.getBody(),{msg:'Loading...'});
    

Ext.define('Bodega', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'nombre', type: 'string'},
        {name: 'direccion', type: 'string'},
        {name: 'telefono', type: 'string'},        
        {name: 'encargado', type: 'string'}                        
    ]
});

var bdgStore = Ext.create('Ext.data.Store', {
    model: 'Bodega',
    proxy: {
        type: 'ajax',
        url : '${createLink( action: 'listBodegas' )}',
        reader: { type: 'json', root: 'items' }
    }    
});
var cmbBodega =  Ext.create('Ext.form.ComboBox', {
  id:'formBdg',
  fieldLabel : 'Bodega',
  labelWidth: 50,
  store: bdgStore,
  queryMode: 'remote',
  displayField: 'nombre',
  valueField: 'id'  
});

var button = Ext.create('Ext.Button', {
  text: 'Buscar',
  handler: function(){
    
    var idbdg = Ext.getCmp('formBdg').getValue();
    if( !idbdg ){
      Ext.Msg.alert('Validación', 'Por favor, seleccione una Bodega!');
      return;
    }
    
    var grid = Ext.getCmp('gridPrv');
    if( !grid.getSelectionModel().hasSelection() ){
      Ext.Msg.alert('Validación', 'Por favor, seleccione un Proveedor!');
      return;
    }
    var idprv = grid.getSelectionModel().getSelection()[0].get('id');           
    
    this.gridArt = Ext.getCmp('gridArt');
    this.gridArt.getStore().load( {params:{ idPrv: idprv, idBdg: idbdg }} );    
  }
  
});

var panelSup = Ext.create('Ext.panel.Panel', {
  layout: 'column',
  border: 0,
  items: [{
    columnWidth:.80,
    border: 0,
    items: cmbBodega,
    margin: '5, 5, 0, 5'
  },{
    columnWidth:.20,
    border: 0,
    items: button,
    margin: '5, 5, 0, 0'
  }]
});


Ext.define('Proveedor', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'codigo', type: 'string'},
        {name: 'razonSocial', type: 'string'}        
    ]
});
var prvStore = Ext.create('Ext.data.Store', {
    model: 'Proveedor',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url : '${createLink( action: 'listProveedores' )}',
        reader: { type: 'json', root: 'items' }
    }    
});
var grid = Ext.create('Ext.grid.Panel', {
        store: prvStore,
        id: 'gridPrv',        
        autoScroll: true,
        columns: [
            {text: "Id", flex: 1, dataIndex: 'id', sortable: true, hidden: true},
            {text: "Nombre", width: 180, dataIndex: 'razonSocial', sortable: true},
            {text: "Codigo", width: 115, dataIndex: 'codigo', sortable: true}            
        ],
        //layout: 'fit',
        forceFit: true, 
        //renderTo:'lstTable',
        //width: 300,
        height: 300
    });


///////////////////////////////////////////////////
// CATALOGO
///////////////////////////////////////////////////
Ext.define('Catalogo', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'codigo', type: 'string'},
        {name: 'ref', type: 'string'},
        {name: 'desc', type: 'string'},        
        {name: 'tipo', type: 'string'},
        {name: 'clase', type: 'string'},
        {name: 'genero', type: 'string'},
        {name: 'cualidades', type: 'string'},
        {name: 'valor', type: 'float'}
    ]
});


///////////////////////////////////////////////////
// ARTICULO
///////////////////////////////////////////////////
Ext.define('Articulo', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'codigo', type: 'string'},
        {name: 'ref', type: 'string'},
        {name: 'desc', type: 'string'},        
        {name: 'tipo', type: 'string'},
        {name: 'clase', type: 'string'},
        {name: 'genero', type: 'string'},
        {name: 'cualidades', type: 'string'}
    ]
});
var artStore = Ext.create('Ext.data.Store', {
    model: 'Articulo',    
    proxy: {
        type: 'ajax',
        url : '${createLink( action: 'findByProvBdg' )}',
        reader: { type: 'json', root: 'items' }
    }    
});
var gridArt = Ext.create('Ext.grid.Panel', {
        store: artStore,
        id: 'gridArt',  
        title: 'Articulos',
        autoScroll: true,
        columns: [
            {text: "Id", flex: 1, dataIndex: 'id', sortable: true, hidden: true},            
            {text: "Tipo", width: 50, dataIndex: 'tipo', sortable: true},    
            {text: "Código", width: 65, dataIndex: 'codigo', sortable: true},    
            {text: "Genero", width: 50, dataIndex: 'genero', sortable: true},    
            {text: "Clase", width: 60, dataIndex: 'clase', sortable: true},    
            {text: "Cualidades", width: 60, dataIndex: 'cualidades', sortable: true},    
            {text: "Ref", width: 30, dataIndex: 'ref', sortable: true},
            {text: "Descripción", width: 100, dataIndex: 'desc', sortable: true}            
        ],
        layout: 'fit',
        forceFit: true,        
        width: 530,        
        height: 150        
    });
gridArt.on( 'itemdblclick', function(viewGrid, record, item, index, event, object ){
                  
    msgMask.show();
                  
    var idbdg = Ext.getCmp('formBdg').getValue();        
    var grid = Ext.getCmp('gridPrv');    
    var idprv = grid.getSelectionModel().getSelection()[0].get('id');           
    var idart = record.get('id');
    
    Ext.Ajax.request({
        url: '${createLink( action: 'save' )}',
        params: {
            idPrv: idprv,
            idBdg: idbdg,
            idArt: idart
        },
        success: function(response){
          
            var textId = response.responseText;            
            //alert(textId);
                        
            viewGrid.getStore().removeAt(index);            
            this.rcordCtl = Ext.create('Catalogo',{
              id: textId, 
              codigo: record.get('codigo'), 
              ref: record.get('ref'),
              desc: record.get('desc'),        
              tipo: record.get('tipo'),
              clase: record.get('clase'),
              genero: record.get('genero'),
              cualidades: record.get('cualidades'),
              valor: 0
            });
            
            this.list = new Array();
            this.list[0] = rcordCtl
            
            Ext.getCmp('gridCtlg').getStore().loadData(this.list, true);            
            msgMask.hide();
        },
        failure: function(response){
            msgMask.hide();
            Ext.Msg.alert('Error', 'Se ha presentado un error, comuniquese con el Administrador');
          
        }
    });
    
});         
          

///////////////////////////////////////////////////
// CATALOGO
///////////////////////////////////////////////////
var catStore = Ext.create('Ext.data.Store', {
    model: 'Catalogo',    
    proxy: {
        type: 'ajax',
        url : '${createLink( action: 'findCatalogo' )}',
        reader: { type: 'json', root: 'items' }
    }    
});
var gridCtlg = Ext.create('Ext.grid.Panel', {
        store: catStore,
        id: 'gridCtlg',  
        title: 'Catalogo',
        autoScroll: true,
        columns: [
            {text: "Id", flex: 1, dataIndex: 'id', sortable: true, hidden: true},            
            {text: "Tipo", width: 50, dataIndex: 'tipo', sortable: true},    
            {text: "Código", width: 65, dataIndex: 'codigo', sortable: true},    
            {text: "Genero", width: 50, dataIndex: 'genero', sortable: true},    
            {text: "Clase", width: 60, dataIndex: 'clase', sortable: true},    
            {text: "Cualidades", width: 60, dataIndex: 'cualidades', sortable: true},    
            {text: "Ref", width: 30, dataIndex: 'ref', sortable: true},
            {text: "Descripción", width: 100, dataIndex: 'desc', sortable: true},
            {text: "Valor", width: 100, dataIndex: 'valor', sortable: true}
        ],
        //layout: 'fit',
        //margins: '5 5 0 0',
        forceFit: true,                        
        width: 520,
        height: 140
    });          
          

var panelCentral = Ext.create('Ext.panel.Panel', {
  layout: 'auto',
  border: 0,
  items: [gridArt, gridCtlg]
});


Ext.create('Ext.container.Container', {
    width: 800,
    height: 400,
    //title: 'Border Layout',
    layout: 'border',
    items: [{
        // xtype: 'panel' implied by default
        title: 'Sucursal',
        region:'west',
        xtype: 'panel',
        //margins: '5 0 0 5',
        width: 270,
        collapsible: true,   // make collapsible
        id: 'west-region-container',
        //layout: 'fit',
        items: [panelSup,grid]
    },{
        //title: 'Center Region',
        region: 'center',     // center region is required, no width/height specified
        xtype: 'panel',
        layout: 'auto',        
        items: [panelCentral]
        //margins: '5 5 0 0'
    }],
    renderTo: 'extContent'
});

  })

  </script>


</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>            
  </div>
  <div class="body">
    <h1>Catalogo</h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
  </div>

  <br/>
  <div id="extContent"/>
  
  <div id="gridFoo"/>


</body>
</body>
</html>
