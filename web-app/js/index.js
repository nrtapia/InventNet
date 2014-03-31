$(document).ready(function () {

    $.ajax({
        url:'index/fillAlerts',
        success:function (resp) {

            $.each(resp.data, function() {

                $().toastmessage('showToast', {
                    text: 'Bodega: ' +this.bodega+ ' Articulo: ' + this.artcode + 'Proveedor: ' + this.prvcode +
                          ' Stock: ' + this.cantidad + ' Mínimo: ' + this.stockmin,
                    sticky:true,
                    position:'top-right',
                    type:'error',
                    closeText:'',
                    close:function () {
                        console.log("toast is closed ...");
                    }
                });
            });

        },
        error:function (data) {
            showError();
        }
    });


    // Handler for .ready() called.

});

function genChart() {
    var bdg = $("#bodegaid").val();
    if (!bdg) {
        bdg = "";
    }
    var params = {
        bodegaid:bdg
    };


    $.ajax({
        cache:false,
        url:'visualization/index',
        data:params,
        success:function (data) {
            $("#img-chart").attr("src", data);
        },
        error:function (data) {
            showError();
        }
    });

}

function buildWindown(url) {
    var windowWidth = 750;
    var windowHeight = 450;
    var centerWidth = (window.screen.width - windowWidth) / 2;
    var centerHeight = (window.screen.height - windowHeight) / 2;

    var recipe = window.open('', '', 'resizable=0,width=' + windowWidth +
        ',height=' + windowHeight +
        ',left=' + centerWidth +
        ',top=' + centerHeight);

    var html = "<!DOCTYPE html><html><head><title>Grafica</title></head><body><img src='" + url + "'/></body></html>";
    recipe.document.open();
    recipe.document.write(html);
    recipe.document.close();
}  