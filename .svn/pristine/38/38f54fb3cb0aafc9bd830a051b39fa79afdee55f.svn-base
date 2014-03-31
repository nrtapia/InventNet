var Ajax;
if (Ajax && (Ajax != null)) {
    Ajax.Responders.register({
        onCreate: function() {
            if($('spinner') && Ajax.activeRequestCount>0)
                Effect.Appear('spinner',{
                    duration:0.5,
                    queue:'end'
                });
        },
        onComplete: function() {
            if($('spinner') && Ajax.activeRequestCount==0)
                Effect.Fade('spinner',{
                    duration:0.5,
                    queue:'end'
                });
        }
    });
}


$(document).ready(function() {

    $("#spinner").bind("ajaxSend", function() {
        //$(this).fadeIn();
        //$(this).show();
        $.blockUI({message:'<h1>Procesando..</h1>'});
    }).bind("ajaxStop", function() {
        //$(this).fadeOut();
        //$(this).hide();
        $.unblockUI();
    }).bind("ajaxError", function() {
        //$(this).fadeOut();
        //$(this).hide();
        $.unblockUI();
    });  
    
});