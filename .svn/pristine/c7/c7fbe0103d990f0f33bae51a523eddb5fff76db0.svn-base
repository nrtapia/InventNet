var $itemsTable = null;

var rowTemp = [
'<tr class="item-row">',            
'<td><input name="art"  value="" id="art" class="articulo" /> ',
'<input type="hidden" name="artid" id="artid" value=""> </td>',
'<td> <input type="text" id="artnom" name="artnom"  style="width: 300px" readonly="true"/> </td>',
'<td><input name="stock" value="" id="stock" disabled="disabled" class="stock"/></td>',
'<td><input name="val"   value="" id="val" disabled="disabled" class="valor" /></td>',
'<td><input name="cant"  value="0" id="cant" class="stock number sumcant" /></td>',
'</tr>'
].join('');


$(document).ready(function() {
    
    //$("#docform").validate();

    $("#docform").validate({
        submitHandler: function(form) {

            var sum = 0;
            $(".sumcant").each(function(){
                sum += Number($(this).val());
            }) ;

            if( sum < 1 ){
                alert('Por favor, ingrese los articulos y la cantidad');
            }else{
                if( confirm('¿Confirma enviar los datos?') ){
                    form.submit();
                }
            }
        }
    });

    
    createCalendar("fechaui");
    $itemsTable = $('#table-data');
    //$('#cant').blur(addRow);
    $('#art').blur(searchArt);
    $('#cant').blur(processCant);

    $('#ref2').focus();
});

function addRow( ){
    var $row = $(rowTemp);

    // save reference to inputs within row
    var $art 	        = $row.find('#art');                
    var $cant	        = $row.find('#cant');
    $($cant).blur(addRow);
    $($art).blur(searchArt);

    if( $('.item-row:last', $itemsTable).size() >0 ){
        $('.item-row:last', $itemsTable).after($row);
    }else{
        $($itemsTable).append($row);
    }
    $($art).focus();       
}



function searchArt(  ) {
    
    var $itemrow = $(this).closest('tr');
    var cellart = $itemrow.find('#art');
    var art = $itemrow.find('#art').val();                   
    var prv = getValueDoc("proveedor.id");
    var bdg = getValueDoc('bodega.id');
    
    if( art == null || art.trim() == '' ){
        return false
    }
    
    if( prv == '' ){
        return;
    }
        
    var params = {
        proveedorid: prv, 
        bodegaid: bdg,
        articulocode: art
    };      
    
    $.ajax({
        cache: false,
        url: 'search',
        type: 'POST',
        data: params,            
        success: function(resp) { 
            if( resp.data.error ){
                alert(resp.data.error);
                cellart.focus();
                $itemrow.find('#val').val("");       
                $itemrow.find('#stock').val("");       
                $itemrow.find('#artid').val("");                           
                //return false;
            }else{
                $itemrow.find('#val').val(resp.data.valor);       
                $itemrow.find('#stock').val(resp.data.stock);       
                $itemrow.find('#artid').val(resp.data.artid);                           
                $itemrow.find('#artnom').val(resp.data.articulo);                
                //$("#footer").html(resp.data.articulo+"<BR/>"+resp.data.proveedor);
            }
        },          
        error : function(data) {
            showError()
        }
    })
}

function processCant( event ){    
    event.preventDefault();
    
    var $itemrow = $(this).closest('tr');
    var stock = $itemrow.find('#stock').val();   
    if( parseInt(this.value) > parseInt(stock) ){
        alert("No hay articulos suficientes en bodega para esta salida.\n" + 
            "Cantidad de articulos en bodega " + stock);
        $cant = $itemrow.find('#cant');
        $cant.val(0);
        $cant.focus();        
    }else{
        addRow()
    }
}  