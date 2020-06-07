const TIME_LOOP = 4000;
const host = "http://localhost:8080";
const url_api = host + "/api/v1/notificacion"
const url_pedido = host + "/pedido"

/* Comprobar si hay nuevas notificaciones para el usuario */
function comprobar() {
    comprobarSolicitudes();
    comprobarRespuestas();
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

function enviarRespuesta(msg) {
    let url = url_api + "/responder/" + msg.from + "/" + msg.to + "/" + msg.aceptado;
    fetch(url, { method: 'GET' })
        .then(res => {
            if (res.status == 200 || res.status == 201) {
                listarMasCercanos(); // Refrescamos la lista de locales cercanos
            } else {
                // Si ocurre un error
                msg.texto = "ERROR: HUBO UN PROBLEMA EN LA TRANSACCIÓN"
            }
        })
        .catch(e => { console.error(e) });
}

// Añade los listener a los botones para aceptar el pedido
function agregarListenerABotonesAceptar() {
    let botonesAceptar = document.querySelectorAll('.botonAceptar');
    botonesAceptar.forEach(boton => boton.addEventListener('click', (e) => {
        // Se construye un Objeto msg que contiene la información que el servidor necesita procesar del cliente.
        var msg = {
            aceptado: true,
            texto: "ACEPTADO",
            from: document.getElementById("current-username").value, // El username del usuario actual
            to: e.target.dataset.userFrom // El dueño que envío la solicitud; le devolvemos la respuesta
        };
        // Se realiza la factura
        let userOferente = document.getElementById("current-username").value
        let userSolicitante = e.target.dataset.userFrom // El dueño que envío la solicitud; le devolvemos la respuesta
        let aceptado = true;
        let urlSolicitud = url_pedido + "/solicitar/" + userOferente + "/" + userSolicitante + "/" + aceptado + "/" + idProducto + "/" + cantidad;
        fetch(urlSolicitud, { method: 'POST' })
            .then(res => {
                if (res.status == 200 || res.status == 201) {
                    listarMasCercanos(); // Refrescamos la lista de locales cercanos
                } else {
                    // Si ocurre un error
                    msg.texto = "ERROR: HUBO UN PROBLEMA EN LA TRANSACCIÓN"
                }
            })
            .catch(e => { console.error(e) });
        // Enviamos la respuesta
        enviarRespuesta(msg);
    }));
}

function agregarListenerABotonesRechazar() {
    let botonesRechazar = document.querySelectorAll('.botonRechazar');
    botonesRechazar.forEach(boton => boton.addEventListener('click', (e) => {
        // Se construye un Objeto msg que contiene la información que el servidor necesita procesar del cliente.
        var msg = {
            aceptado: false,
            texto: "RECHAZADO",
            from: document.getElementById("current-username").value, // El username del usuario actual
            to: e.target.dataset.userFrom // El dueño que envío la solicitud; le devolvemos la respuesta
        };
        enviarRespuesta(msg);
    }));
}

function cargarNotificaciones(lista) {
    var size = lista.length;
    if(size !== 0) {
        var i;
        document.getElementById("notificationContainer").innerHTML = "";
        for (i = 0; i < size; i++) {
            var obj = lista[i];
            if(obj.tipo.toUpperCase() === "solicitud".toUpperCase()) { // Solicitud
                let idLocal = document.getElementById('current-idLocal').value;
                if(obj.toLocal == idLocal) { // Si la solicitud es para este local
                    var html = renderizarAHTML(obj);
                    var htmlAnterior = document.getElementById("notificationContainer").innerHTML
                    document.getElementById("notificationContainer").innerHTML = html + htmlAnterior;
                }
            } else { // Si lo que me llega es una respuesta
                if(obj.to == document.getElementById("current-username").value) // Si son mensajes para el usuario actual
                    alertarRespuesta(obj.texto);
            }
        }
        agregarListenerABotonesAceptar();
        agregarListenerABotonesRechazar();
        console.log("Notificación recibida");
    }
}

function renderizarAHTML(obj) {
    var msg = "El usuario " + obj.from + " necesita " + obj.detalleNotificacion.cantidad + " unidades del producto " + 
              obj.detalleNotificacion.idProducto;
    html = '<div class="dropdown-item">' + 
             '<strong>' + msg + '</strong>' +
             '<a class="dropdown-item botonAceptar" data-id="' + obj.id + '" data-user-from="' + obj.from + '" href="#"> Aceptar</a>' +
             '<a class="dropdown-item botonRechazar" data-id="' + obj.id + '" data-user-from="' + obj.from + '" href="#"> Rechazar</a>' +
           '</div>' +
           '<div class="dropdown-divider"></div>'
    return html;
}

// Inserta el nodo alert en el DOM
function alertarRespuesta(respuesta) {
    let alertCloseButton = '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                           '<span aria-hidden="true">&times;</span></button>';
    let htmlAlert = "";
    switch(respuesta.toUpperCase()) {
      case "ACEPTADO":
        htmlAlert = '<div class="p-4 alert alert-success alert-dismissible fade show" role="alert">' +
                    '<strong>EXITO</strong> El pedido ha sido aceptado' + alertCloseButton + '</div> ';
        break;
      case "RECHAZADO":
        htmlAlert = '<div class="p-4 alert alert-warning alert-dismissible fade show" role="alert">' +
                    '<strong>ERROR</strong> El pedido ha sido rechazado' + alertCloseButton + '</div> ';
        break;
      default: // Algún error
        htmlAlert = '<div class="p-4 alert alert-warning alert-dismissible fade show" role="alert">' +
                    '<strong>ERROR</strong> Hubo un problema al crear el pedido' + alertCloseButton + '</div> ';
    }
    document.getElementById("alertContainer").innerHTML = htmlAlert;
}

setInterval(function(){ comprobar() }, TIME_LOOP);
