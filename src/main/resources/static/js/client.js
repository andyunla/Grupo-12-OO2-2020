const TIME_LOOP = 4000; // Cada 4s
const host = "http://localhost:8080";
const url_api = host + "/api/v1/notificacion";
const url_pedido = host + "/pedido";

/* Comprobar si hay nuevas notificaciones para el usuario */
function comprobar() {
    comprobarSolicitudes();
    comprobarRespuestas();
    // Para que no aparezcan varias veces los alerts que aparecen que se confirmó o no la solicitud
    confirmarRespuestas();
}

function comprobarSolicitudes() {
    let idLocal = document.getElementById("current-idLocal").value;
    let url = url_api + "/comprobar/solicitud/" + idLocal;
    fetch(url, { method: 'GET' })
        .then(response => response.text())
        .then(text => {
            cargarNotificaciones(JSON.parse(text));
        })
        .catch((e) => {
             console.log(e);
        });
}

function comprobarRespuestas() {
    let username = document.getElementById("current-username").value;
    let url = url_api + "/comprobar/respuesta/" + username;
    fetch(url, { method: 'GET' })
        .then(response => response.text())
        .then(text => {
            cargarNotificaciones(JSON.parse(text));
        })
        .catch((e) => {
             console.log(e);
        });
}

// 1ro se realiza el pedido y obtenemos el id en caso que se haya realizado con éxito.
// Luego se envía la respuesta
async function realizarSolicitudPedido(detalleEnvio) {
    // Se construye un Objeto msg que contiene los datos para enviar en la respuesta; no para realizar el pedido
    var respuesta = {
        id: detalleEnvio.idNotificacion,
        tipo: "RESPUESTA",
        texto: detalleEnvio.aceptado? "ACEPTADO" : "RECHAZADO",
        from: document.getElementById("current-username").value, // El username del usuario actual
        to: detalleEnvio.userSolicitante, // El dueño que envío la solicitud; le devolvemos la respuesta
        detalleNotificacion: {
            idPedidoStock: null
        }
    };
    // Se realiza la factura
    let userOferente = detalleEnvio.userOferente;
    let userSolicitante = detalleEnvio.userSolicitante; // El dueño que envío la solicitud; le devolvemos la respuesta
    let fueAceptado = detalleEnvio.aceptado;
    let idProducto = detalleEnvio.idProducto;
    let cantidad = detalleEnvio.cantidad;
    let urlSolicitud = url_pedido + "/solicitar/" + userOferente + "/" + userSolicitante + "/" + fueAceptado + "/" + idProducto + "/" + cantidad;
    try {
        let response = await fetch(urlSolicitud, { method: 'POST' });
        if (response.ok) { // 200 - 299
            let text = await response.text();
            console.log("Solicitud realizada");
            respuesta.detalleNotificacion.idPedidoStock = JSON.parse(text).idPedidoStock; // El id del pedido realizado
        } else {
            throw new Error();
        }
    } catch(e) {
        console.error(e);
    }
    // Enviamos la respuesta
    enviarRespuesta(respuesta);
}

function enviarRespuesta(respuesta) {
    $.ajax({
        type : "POST",
        url : url_api + "/responder",
        dataType: "json",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        data : JSON.stringify(respuesta),
        success : function(response) {
           console.log('Respuesta enviada');
        },
        error : function(e) {
           console.error("ERROR: " + e.message);   
        }
    });
    // Le mandamos el id para que lo marque como leída
    confirmarRespuestas(respuesta.id);
    // Eliminamos la notificación de la lista
    document.getElementById("notificacion-" + respuesta.id).remove();
}

// Añade los listener a los botones para aceptar el pedido
function agregarListenerABotonesAceptar() {
    let botonesAceptar = document.querySelectorAll('.botonAceptar');
    botonesAceptar.forEach(boton => boton.addEventListener('click', (e) => {
        var detalleEnvio = {
            idNotificacion: e.target.dataset.id,
            userOferente: document.getElementById("current-username").value,
            userSolicitante: e.target.dataset.userFrom,
            aceptado: true,
            idProducto: e.target.dataset.idProducto,
            cantidad: e.target.dataset.cantidad,
            to: e.target.dataset.userFrom
        };
        realizarSolicitudPedido(detalleEnvio);
    }));
}

function agregarListenerABotonesRechazar() {
    let botonesRechazar = document.querySelectorAll('.botonRechazar');
    botonesRechazar.forEach(boton => boton.addEventListener('click', (e) => {
        var detalleEnvio = {
            idNotificacion: e.target.dataset.id,
            userOferente: document.getElementById("current-username").value,
            userSolicitante: e.target.dataset.userFrom,
            aceptado: false,
            idProducto: e.target.dataset.idProducto,
            cantidad: e.target.dataset.cantidad,
            to: e.target.dataset.userFrom
        };
        realizarSolicitudPedido(detalleEnvio);
    }));
}

function cargarNotificaciones(lista) {
    var size = lista.length;
    if(size !== 0) {
        var i;
        // Borramos todo el contenido del área de notificación
        document.getElementById("notificationContainer").innerHTML = "";
        for (i = 0; i < size; i++) {
            var obj = lista[i];
            var html = renderizarAHTML(obj);
            var htmlAnterior = document.getElementById("notificationContainer").innerHTML
            document.getElementById("notificationContainer").innerHTML = html + htmlAnterior;
        }
        agregarListenerABotonesAceptar();
        agregarListenerABotonesRechazar();
        console.log("Notificación recibida");
    }
}

function renderizarAHTML(obj) {
    var cargado = false;
    if(obj.tipo.toUpperCase() === "SOLICITUD") { // Solicitud
        var msg = "El usuario <strong>" + obj.from + "</strong> necesita " + obj.detalleNotificacion.cantidad + " unidad(es) de " + 
                  "<strong>" + obj.detalleNotificacion.nombreProducto + "</strong>";
        var botones = '<a href="#" class="btn btn-xs btn-primary pull-right botonAceptar" data-id="' + obj.id + 
                           '" data-user-from="' + obj.from + '" data-id-producto="' + obj.detalleNotificacion.idProducto + 
                           '" data-cantidad="' + obj.detalleNotificacion.cantidad + '"> Aceptar</a>' +
                           '<a href="#" class="btn btn-xs btn-primary pull-right botonRechazar" data-id="' + obj.id + 
                           '" data-user-from="' + obj.from + '" data-id-producto="' + obj.detalleNotificacion.idProducto + 
                           '" data-cantidad="' + obj.detalleNotificacion.cantidad + '"> Rechazar</a>';
        cargado = true;
    } else { // Si lo que me llega es una respuesta
        if(obj.to == document.getElementById("current-username").value) { // Si son mensajes para el usuario actual
            switch(obj.texto.toUpperCase()) {
                case "ACEPTADO":
                    var msg = "<strong> El pedido ha sido aceptado </strong>";
                    break;
                case "RECHAZADO":
                    var msg = "<strong> El pedido ha sido rechazado </strong>";
                    break;
                default:
                    var msg = "<strong>" + obj.texto + "</strong>";
                    break;
            }
            let botonFacturar = '<a href="#" class="btn btn-xs btn-primary pull-right botonFacturar" data-id="' + obj.id + 
                                '" data-user-from="' + obj.from + '" data-id-producto="' + obj.detalleNotificacion.idProducto + 
                                '" data-cantidad="' + obj.detalleNotificacion.cantidad + '"> Facturar</a>';
            // En el caso de rechazo o error
            let botonCerrar = '<a href="#" class="btn btn-xs btn-primary pull-right botonCerrar" data-id="' + obj.id + 
                                '" data-user-from="' + obj.from + '" data-id-producto="' + obj.detalleNotificacion.idProducto + 
                                '" data-cantidad="' + obj.detalleNotificacion.cantidad + '"> Cerrar</a>';
            var botones = (obj.texto.toUpperCase() === "ACEPTADO")? botonFacturar : botonCerrar;
            cargado = true; // Para no enviar html cuando los botones no se establecieron
        }
    }
    if(cargado) {
        var html = '<div class="dropdown-item" id="notificacion-' + obj.id + '">' + 
                        '<div class="alert alert-info">' +
                            msg +
                            '<div class="alert alert-info">' +
                                botones +
                            '</div>' +
                        '<div class="dropdown-divider"></div>' +
                   '</div>';
    } else {
        var html = "";
    }
    return html;
}

function confirmarRespuestas(idNotificacion) {
    if(idNotificacion === undefined) { // Automáticamente; para confirmar respuestas
        let alertRespuesta = document.getElementById("alertRespuesta");
        if(alertRespuesta) {
            let children = alertRespuesta.children;
            if(children.length !== 0) {
                idNotificacion = children[0].dataset.idNotificacion;
            }
        }
    }
    if(idNotificacion) { // Si es que se logró resolver el undefined
        let url = url_api + "/confirmar/" + idNotificacion;
        fetch(url, { method: 'GET' })
            .then(res => {
                if (res.status == 200 || res.status == 201) {
                    console.log("Mensaje de respuesta confirmado");
                } else {
                    // Si ocurre un error
                    console.log("ERROR: HUBO UN PROBLEMA EN LA CONFIRMACIÓN DE LA RESPUESTA")
                }
            })
            .catch(e => { console.error(e) });
    }
}

setInterval(function(){ comprobar() }, TIME_LOOP);
