$(document).ready(function(){
	if ($("#radio-cliente-baja").attr("checked")){
		$("#detalles-cliente-baja").css("display", "block");
		$("#detalles-cliente-alta").css("display", "none");
    }else {
    	$("#detalles-cliente-baja").css("display", "none");
		$("#detalles-cliente-alta").css("display", "block");
    }
    
    // Tenemos que ocultar los formularios de Baja y Modificación
    $("#formulario-baja").css("display", "none");
	$("#formulario-modificacion").css("display", "none");
});

function mostrar_por_tipo(tipo_cliente){
	if (tipo_cliente == 'baja') {
		$("#detalles-cliente-baja").css("display", "block");
		$("#detalles-cliente-alta").css("display", "none");
	} else {
		$("#detalles-cliente-baja").css("display", "none");
		$("#detalles-cliente-alta").css("display", "block");
	}
}

var alerta_info = "<div class='alert alert-info'><strong>Info!</strong> Todo ok.</div>"
var alerta_warning = "<div class='alert alert-warning'><strong>Info!</strong> Puede haber ocurrido un problema.</div>"
var alerta_success = "<div class='alert alert-success'><strong>Info!</strong> Todo satisfactorio.</div>"
var alerta_danger = "<div class='alert alert-danger'><strong>Advertencia!</strong> Ha ocurrido un error.</div>"

// Para elegir el formulario apropiado
function seleccionarABM(action) {
	if (action == 'A') {
		$("#btn-abm").html("<a>Alta</a>");
		$("#formulario-alta").css("display", "block");
		$("#formulario-baja").css("display", "none");
		$("#formulario-modificacion").css("display", "none");
	} else if (action == 'B') {
		$("#btn-abm").html("<a>Baja</a>");
		$("#formulario-alta").css("display", "none");
		$("#formulario-baja").css("display", "block");
		$("#formulario-modificacion").css("display", "none");
	} else {
		$("#btn-abm").html("<a>Modificación</a>");
		$("#formulario-alta").css("display", "none");
		$("#formulario-baja").css("display", "none");
		$("#formulario-modificacion").css("display", "block");
	}
}
