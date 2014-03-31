$(document).ready(function() {    
    $('#button-search').click(refresh);          
    
});
    
function refresh(){
    onclick_search();
}    
    
function deleteCatalogo(value, obj){
        
    if( !confirm("Confirma quitar el articulo del catalogo?") ){
        return;
    }
                
    $.ajax({
        cache: false,
        url: 'deleteCatalogo',
        type: 'POST',
        data: {
            catalogoid:value
        },            
        success: function(data) {
            
            if( data == "success" ){
                searchArticulo();
                deleteRow(obj);
            }
        },          
        error : function(data) {
            showError()
        }
    })               
}
    
function addArticulo( value, obj ){
    
    var params = getParams();
    params.articuloid = value;
    
    deleteRow(obj);
    
    $.ajax({
        cache: false,
        url: 'saveCatalogo',
        data: params,            
        success: function(data) {
            searchCatalogo();            
        },          
        error : function(data) {
            showError()
        }
    })  
}
    

    
function getParams(){
    
    var prv = $('#proveedorid option:selected').val();
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
    
function deleteAll(){
    var param = getParams();
    if( !param ){
        return;
    }
    if( !confirm("Confirma quitar todos los articulos al proveedor?") ){
        return;
    }
          
    $.ajax({
        cache: false,
        url: 'removeAll',
        type: 'POST',
        data: getParams(),            
        success: function(data) {
            onclick_search()
        },          
        error : function(data) {
            showError()
        }
    })
}
    
function addAll(){
    var param = getParams();
    if( !param ){
        return;
    }
    if( !confirm("Confirma agregar todos los articulos al proveedor?") ){
        return;
    }
          
    $.ajax({
        cache: false,
        url: 'addAll',
        data: getParams(),            
        success: function(data) {
            onclick_search()
        },          
        error : function(data) {
            showError()
        }
    })
}
    
    
    
function onclick_search() {
    
    var param = getParams();
    if(!param){
        return;
    };
            
    searchArticulo();             
    searchCatalogo();
}
    
function searchArticulo(){
    $.ajax({
        cache: false,
        url: 'findByProvBdg',
        data: getParams(),
        success: function(data) {
            $('#articulos').html(data)
        },
        error : function(data) {
            showError()
        }
    })
}

function updateCatalogo(){
    if( !confirm("Confirma actulizar los datos del catalogo?") ){
        return;
    }
    
    var params = $('#formCatalogo').serializeArray();
    if(params.length == 0){
        alert('Por favor, ingrese los datos del catalogo.');
        return;
    }
                  
    $.ajax({
        cache: false,
        url: 'updateCatalogo',
        type: 'POST',
        data: params,            
        success: function(data) {           
        },          
        error : function(data) {
            showError()
        }
    })
}
    
    
function searchCatalogo(){
    $.ajax({
        cache: false,
        url: 'findByProvBdgCatalog',
        data: getParams(),
        success: function(data) {
            $('#catalogo').html(data)
        },
        error : function(data) {
            showError()
        }
    })
}