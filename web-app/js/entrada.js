$(document).ready(function() {    
   
    createCalendar('fechaid');  
    
});

    
function getParams(){
    
    var prv = $('#proveedorid').val();
    var bdg = $('#bodegaid').val();
    var mvm = $('#movetypeid').val();
    
    if( !prv || !bdg || !mvm){
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


function searchArticulo(){
    $.ajax({
        cache: false,
        url: 'listCatalogo',
        data: getParams(),
        success: function(data) {
            $('#lista').html(data)
            $("a[rel^='prettyPhoto']").prettyPhoto({ social_tools:'', theme:'facebook' });
        },
        error : function(data) {
            showError()
        }
    })
}

function showError(){
    alert('Se ha producido un error, intentelo mas tarde');
}


function updateCatalogo(){
    
    var fecha = $("#fechaid").val();
    var tipo = $("#movetypeid").val();
    var prv = $('#proveedorid').val();
    var bdg = $('#bodegaid').val();
    
    if( !fecha || !tipo || !prv || !bdg ){
        alert('Por favor, ingrese la fecha y tipo de movimiento.');
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
                    
    if( !confirm("Los articulos para entrada son: "+sum+
        ". Confirma actualizar la información del inventario?") ){
        return;
    }
                  
    $.ajax({
        cache: false,
        url: 'addInput',
        type: 'POST',
        data: params,            
        success: function(data) {              
            $("#table-articulos").find("tr:gt(0)").remove();
        },          
        error : function(data) {
            showError()
        }
    })
}