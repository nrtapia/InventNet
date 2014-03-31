function getValue( id ){
    return $("#"+id).val();
}    

function getValueDoc( id ){
    return document.getElementById(id).value;
}

    
function deleteRow( obj ){
    var td = obj.parentNode;
    var tr = td.parentNode;
    var table = tr.parentNode;
    table.removeChild(tr);    
}

function showError(){
    alert('Se ha producido un error, intentelo mas tarde');
}


function createCalendar( id ){    

    $( "#"+id ).datepicker( {
        changeMonth: true,
        changeYear: true,   
        dateFormat: 'dd/mm/yy',
        gotoCurrent: true,
        dayNamesMin:     ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
        monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic']
    });   

    $("#"+id).datepicker('setDate', new Date());

}

function formatNumber(num,prefix){
    prefix = prefix ||'';
    num += '';
    var splitStr = num.split('.');
    var splitLeft = splitStr[0];
    var splitRight = splitStr.length > 1 ? '.' + splitStr[1] : '';
    var regx = /(\d+)(\d{3})/;
    while (regx.test(splitLeft)) {
        splitLeft = splitLeft.replace(regx, '$1' + ',' + '$2');
    }
    return prefix + splitLeft + splitRight;
}