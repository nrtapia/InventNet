
$(document).ready(function() {    
    
  $("a[rel^='prettyPhoto']").prettyPhoto({ social_tools:'', theme:'facebook' });
    
  $(".tpoArt").change(calculate);
  $(".clsArt").change(calculate);
  $(".gnrArt").change(calculate);
  $(".cldArt").change(calculate);
  $("#referencia").blur(calculate);
    
});

function calculate(){
  var code  = "";
  var tipo  = "0"+getValueDoc("tipoArticulo.id");
  var clase = "0"+getValueDoc("claseArticulo.id");
  var gen   = getValueDoc("genero.id");
  var cld   = "0"+getValueDoc("cualidadesArticulo.id");
  var ref   = getValueDoc("referencia");
  
  code = tipo + gen + clase + cld + ref ; 
  
  $("#codigo").val(code);
}
