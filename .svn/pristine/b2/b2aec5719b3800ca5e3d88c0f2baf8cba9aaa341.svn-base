//var $j = jQuery.noConflict(); 

$(document).ready(function(){
    
    $( "#fechaid" ).datepicker( {
        changeMonth: true,
        changeYear: true,   
        dateFormat: 'dd/mm/yy',
        setDate: new Date(),
        dayNamesMin:     ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
        monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic']
    }); 
    $( "#fechaid" ).datepicker( "setDate", new Date());
});


function clearPrv(){
    document.getElementById('proveedor.id').selectedIndex  = 0;
    //document.getElementById('catalogoid').options.length = 0;
    document.getElementById('articulo.id').value = "";
    document.getElementById('cantidad').value = "0";
    //document.getElementById('valorAnterior').value = "0";    
    //$('#articulo.id option').each(function(i, option){ $(option).remove(); });
}

function callData( pid ){
       
    $.ajax({
        cache: false,
        url: 'fillData',
        data: { id: pid },
        success: function(resp) {            
            $('#cantidad').val(resp.data.cantidad);
            $('#valorAnterior').val(resp.data.valor);                        
            
            document.getElementById('articulo.id').value = resp.data.articulo;
        },
        error : function(data) {
            showError();
        }
    });  
}