$(function() {    
    createCalendar("fechaid");
});


function searchArticulo(){
    
    var bdg = $('#bodegaid').val();
    var mvt = $('#movetypeid').val();
    var fch = $('#fechaid').val();
         
    if( !mvt || !bdg || !fch){
        alert('Por favor, seleccione la sucursal, movimiento y fecha');
        return false;
    }
        
    var params = {
        bodegaid: bdg, 
        movtoid: mvt, 
        fechaid: fch
    };   
        
    $.ajax({
        cache: false,
        url: 'listMovement',
        data: params,
        success: function(data) {
            $('#lista').html(data);
            $("a[rel^='prettyPhoto']").prettyPhoto({ social_tools:'', theme:'facebook' });

            $('.editable').editables(
            {
                beforeEdit: function(field){
                    field.val(this.text().trim());
                },
                beforeFreeze: function(display){
                    if( this.data('old')+'' != this.val().trim() ){
                        var thisobj = this;
                        $.ajax({
                            cache: false,
                            url: 'updref',
                            data: { id: this.data('id'), ref:this.val().trim() },
                            success: function(data) {
                                display.text(thisobj.val().trim());
                            },
                            error : function(data) {
                                showError();
                            }
                        });
                    }
                }
            } );

        },
        error : function(data) {
            showError();
        }
    })
}

function selectArt( id, codart, cant, obj ){
    
    if( confirm( "Confirma ajustar el articulo " + codart + " con " + cant + "?") ){
        
        $.ajax({
            cache: false,
            url: 'deleteMovement',
            data: {idinv:id},
            success: function(data) {
                deleteRow(obj);
            },
            error : function(data) {
                showError();
            }
        })
    }   
}