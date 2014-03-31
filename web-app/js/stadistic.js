$(document).ready(function() {
   
    createCalendar('fechabegin');
    createCalendar('fechaend');
});


function fillTitle( obj ){
    $("#title-head").text( obj.value );
}