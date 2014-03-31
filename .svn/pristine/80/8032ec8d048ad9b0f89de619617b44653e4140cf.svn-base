
$(document).ready(function() {
   
    createCalendar("fechaid"); 
       
    $( "#dialog-select" ).dialog({
        height: 300,
        width: 620,
        modal: true,
        autoOpen: false,
        buttons: {
            Salir:function(){
                $(this).dialog("close");
            }
        }
    });
        
    $('#ref').blur(processRef);        
    //$('#prv').blur(processPrv);
    $('#cant').focus(processPrv);
    $('#cant').blur(processCant);
    
    $itemsTable = $('#table-data');
    
     $('#art').autocomplete({ 
       source: '../common/searchArticulo',
       minLength: 4
    });
    
    $('.proveedor').autocomplete({ 
       source: '../common/searchProveedor',
       minLength: 2,
       select: function( event, ui ){
           $this = $(this);
           $this.val( ui.item.id );
           return false;
       }
    })/*
    .data( "autocomplete" )._renderItem = function( ul,item ){
        return 
    }*/;
});

var $itemsTable = null;
    
var rowTemp = [
'<tr class="item-row">',            
'<td><input name="art"   value="" id="art" class="articulo" /> ',
'<input type="hidden" name="ctl" id="ctl" value=""> </td>',
'<td><input name="prv"   value="" id="prv" class="proveedor" /></td>',
'<td><input name="stock" value="" id="stock" disabled="disabled" class="stock"/></td>',
'<td><input name="val"   value="" id="val" disabled="disabled" class="valor" /></td>',
'<td><input name="cant"  value="" id="cant" class="stock" /></td>',
'<td><input name="ref"   value="" id="ref" class="ref" /></td>',
'</tr>'
].join('');
    
function processCant( ){    
    var $itemrow = $(this).closest('tr');
    var stock = $itemrow.find('#stock').val();   
    if( parseInt(this.value) > parseInt(stock) ){
        alert("No hay articulos suficientes en bodega para esta salida.\n" + 
            "Cantidad de articulos en bodega " + stock);
        this.focus();        
    }
}    

function processRef( ){
    var $row = $(rowTemp);

    // save reference to inputs within row
    var $art 	        = $row.find('#art');
    var $prv 	        = $row.find('#prv');    
    var $ref	        = $row.find('#ref');
    var $cant	        = $row.find('#cant');
        
    $($prv).blur(processPrv);
    $($ref).blur(processRef);
    $($cant).blur(processCant);
        
    if( $('.item-row:last', $itemsTable).size() >0 ){
        $('.item-row:last', $itemsTable).after($row);
    }else{
        //$($itemsTable).after($row);
        $($itemsTable).append($row);
    }
    $($art).focus();        
}

function processPrv(  ) { 
    
    var $itemrow = $(this).closest('tr');
    var art = $itemrow.find('#art').val();               
    var cellprv = $itemrow.find('#prv');
    //var prv = this.value;
    var prv = $itemrow.find('#prv').val();
    var bdg = $('#bodegaid').val();
    
    if( prv == '' ){
        return;
    }
        
    var params = {
        proveedorid: prv, 
        bodegaid: bdg,
        articuloid: art
    };      
    
    $.ajax({
        cache: false,
        url: 'search',
        type: 'POST',
        data: params,            
        success: function(resp) { 
            if( resp.data.error ){
                alert(resp.data.error);
                cellprv.focus();
                $itemrow.find('#val').val("");       
                $itemrow.find('#ctl').val("");                           
                return false;
            }else{
                $itemrow.find('#val').val(resp.data.valor);       
                $itemrow.find('#stock').val(resp.data.stock);       
                $itemrow.find('#ctl').val(resp.data.id);                           
                //$itemrow.find('#artnom').html(resp.data.articulo);                
                $("#footer").html(resp.data.articulo+"<BR/>"+resp.data.proveedor);
            }
        },          
        error : function(data) {
            showError()
        }
    })
}

function searchArticulo(){
    $.ajax({
        cache: false,
        url: 'listCatalogo',
        data: getParams(),
        success: function(data) {
            $('#lista').html(data)
        },
        error : function(data) {
            showError()
        }
    })
}

function selectArt( id, obj ){
    
    //$("#bodegaid").attr("disabled","disabled");     
    var tr1 = "<tr>";
    var tr2 = "</tr>"; 
    var td1 = "<td>";
    var td2 = "</td>"; 
    var prv =  $("#proveedorid option:selected").text();
        
    var line = tr1 + td1 + prv.substring( 0, 4 ) +  td2;
    line += td1 +  getValue("sel_codigo_"+id) + td2;    
    line += td1 + getValue("sel_tipo_"+id) + td2;
    line += td1 + getValue("sel_genero_"+id) + td2;
    line += td1 + getValue("sel_clase_"+id) + td2;
    line += td1 + getValue("sel_cualid_"+id) + td2;
    line += td1 + getValue("sel_ref_"+id) + td2;
    line += td1 + getValue("sel_desc_"+id) + td2;
    line += "<td style='text-align:right'>" + getValue("sel_costo_"+id) + td2;    
    line += "<td style='text-align:right'>" + getValue("sel_valor_"+id) + td2;    
    line += td1 + "<input type='text' id='cant' name='cant' value='0' size='4' style='text-align:right'/> " +          
    " <input name='idctl' type='hidden' id='idctl' value='"+getValue("sel_id_"+id)+"' />" + td2;
    line += tr2;
    
    $('#listArt tr:last').after(line);     
    
    deleteRow(obj);       
}


function getParams(){
    
    var prv = $('#proveedorid option:selected').val();
    var bdg = $('#bodegaid').val();
    var mvm = $('#movetypeid').val();
    
    if( !prv || !bdg || !mvm ){
        alert('Por favor, seleccione una sucursal, proveedor y movimiento');
        return false;
    }
        
    var params = {
        proveedorid: prv, 
        bodegaid: bdg,
        mvmid: mvm
    };      
    return params;
}



function searchData( obj ){
    var art = $('#art').val();
    var prv = $('#prv').val();
    var bdg = $('#bodegaid').val();
    
    if( prv == '' ){
        return;
    }
        
    var params = {
        proveedorid: prv, 
        bodegaid: bdg,
        articuloid: art
    };      
    
    $.ajax({
        cache: false,
        url: 'search',
        type: 'POST',
        data: params,            
        success: function(resp) { 
            if( resp.data.error ){
                alert(resp.data.error);
                $('#prv').focus();
                return false;
            }else{
                
            }
        },          
        error : function(data) {
            showError()
        }
    })
}



function updateCatalogo(){
    
    var fecha = $("#fechaid").val();
    var tipo = $("#movetypeid").val();    
    var bdg = $('#bodegaid').val();
    
    if( !fecha || !tipo || !bdg ){
        alert('Por favor, seleccione la sucursal, movimiento y fecha.');
        return;
    }
    var params = $('#formCatalogo').serializeArray();    
      
    var sum = 0;
    //iterate through each textboxes and add the values
    $('input[name="cant"]').each(function() {
        //add only if the value is number
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseFloat(this.value);
        }
    });
                    
    if( !confirm("Los articulos para salida son: "+sum+
        ". Confirma actualizar la información del inventario?") ){
        return;
    }
                  
    $.ajax({
        cache: false,
        //url: 'addOutput',
        url: 'ouputArt',
        type: 'POST',
        data: params,            
        success: function(data) {             
            $("#table-data").find("tr:gt(0)").remove();            
            processRef();
        },          
        error : function(data) {
            showError()
        }
    })
}


