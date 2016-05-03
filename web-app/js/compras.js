$(function() {    
    createCalendar('fechaid');   
});

    
function getParams(){
    
    var prv = $('#proveedorid').val();
    var bdg = $('#bodegaid').val();
    
    if( !prv || !bdg ){
        alert('Por favor, seleccione un Proveedor y Sucursal');
        return false;
    }
        
    var params = {
        proveedorid: prv, 
        bodegaid: bdg
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
    var prv = $('#proveedorid').val();
    var bdg = $('#bodegaid').val();
    
    if( !fecha || !prv || !bdg ){
        alert('Por favor, sellecione la sucursal, proveedor y fecha.');
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
                    
    if( !confirm("Los articulos para compra son: "+sum+
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