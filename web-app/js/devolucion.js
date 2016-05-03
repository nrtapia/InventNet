var $j = jQuery.noConflict(); 

$j(document).ready(function(){
    
    $j( "#fechaid" ).datepicker( {
        changeMonth: true,
        changeYear: true,   
        dateFormat: 'dd/mm/yy',
        setDate: new Date(),
        dayNamesMin:     ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
        monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic']
    }); 
    $j( "#fechaid" ).datepicker( "setDate", new Date());
});



function callData( pid ){
       
    $j.ajax({
        cache: false,
        url: 'fillData',
        data: { id: pid },
        success: function(resp) {                        
            $j('#valor').val(resp.data.valor);                        
            $j('#costo').val(resp.data.costo);
            document.getElementById('articulo.id').value = resp.data.articulo;
        },
        error : function(data) {
            showError();
        }
    });  
}

function clearPrv(){
    document.getElementById('proveedor.id').selectedIndex  = 0;
    document.getElementById('catalogoid').options.length = 0;
    document.getElementById('articulo.id').value = "";
    document.getElementById('cantidad').value = "0";
    document.getElementById('valor').value = "0";    
    document.getElementById('costo').value = "0";    
}

function update(){
    
    return confirm("Se recuerda haber ingresado la cantidad devuelta por el Proveedor o las observaciones. Confirma continuar?"); 
}