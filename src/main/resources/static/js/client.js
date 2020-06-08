const TIME_LOOP = 4000;
const host = "http://localhost:8080";
const url_api = host + "/api/v1/notificacion"
const url_pedido = host + "/pedido"

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

function enviarRespuesta(msg) {
    let url = url_api + "/responder/" + msg.from + "/" + msg.to + "/" + msg.aceptado;
    fetch(url, { method: 'GET' })
        .then(res => {
            if (res.status == 200 || res.status == 201) {
                //listarMasCercanos(); // Refrescamos la lista de locales cercanos
                console.log("Respuesta enviada");
            } else {
                // Si ocurre un error
                msg.texto = "ERROR: HUBO UN PROBLEMA EN LA TRANSACCIÓN"
            }
        })
        .catch(e => { console.error(e) });
    confirmarRespuestas(msg.idNotificacion); // Le mandamos el id para que lo marque como leída
    // Eliminamos la notificación de la lista
    document.getElementById("notificacion-" + msg.idNotificacion).remove();
}

// Añade los listener a los botones para aceptar el pedido
function agregarListenerABotonesAceptar() {
    let botonesAceptar = document.querySelectorAll('.botonAceptar');
    botonesAceptar.forEach(boton => boton.addEventListener('click', (e) => {
        // Se construye un Objeto msg que contiene los datos para enviar en la respuesta; no para realizar el pedido
        var msg = {
            idNotificacion: e.target.dataset.id,
            aceptado: true,
            texto: "ACEPTADO",
            from: document.getElementById("current-username").value, // El username del usuario actual
            to: e.target.dataset.userFrom // El dueño que envío la solicitud; le devolvemos la respuesta
        };
        // Se realiza la factura
        let userOferente = document.getElementById("current-username").value
        let userSolicitante = e.target.dataset.userFrom; // El dueño que envío la solicitud; le devolvemos la respuesta
        let aceptado = true;
        let idProducto = e.target.dataset.idProducto;
        let cantidad = e.target.dataset.cantidad;
        let urlSolicitud = url_pedido + "/solicitar/" + userOferente + "/" + userSolicitante + "/" + aceptado + "/" + idProducto + "/" + cantidad;
        fetch(urlSolicitud, { method: 'POST' })
            .then(res => {
                if (res.status == 200 || res.status == 201) {
                    //listarMasCercanos(); // Refrescamos la lista de locales cercanos
                    console.log("Solicitud realizada");
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
        // Se construye un Objeto msg que contiene los datos para enviar en la respuesta; no para realizar el pedido
        var msg = {
            idNotificacion: e.target.dataset.id,
            aceptado: false,
            texto: "RECHAZADO",
            from: document.getElementById("current-username").value, // El username del usuario actual
            to: e.target.dataset.userFrom // El dueño que envío la solicitud; le devolvemos la respuesta
        };
        // Se crea solo el pedido sin factura
        let idNotificacion = e.target.dataset.id;
        let userOferente = document.getElementById("current-username").value
        let userSolicitante = e.target.dataset.userFrom; // El dueño que envío la solicitud; le devolvemos la respuesta
        let aceptado = false; // Al ser false no se creará la factura
        let idProducto = e.target.dataset.idProducto;
        let cantidad = e.target.dataset.cantidad;
        let urlSolicitud = url_pedido + "/solicitar/" + userOferente + "/" + userSolicitante + "/" + aceptado + "/" + idProducto + "/" + cantidad;
        fetch(urlSolicitud, { method: 'POST' })
            .then(res => {
                if (res.status == 200 || res.status == 201) {
                    //listarMasCercanos(); // Refrescamos la lista de locales cercanos
                    console.log("Solicitud realizada");
                } else {
                    // Si ocurre un error
                    msg.texto = "ERROR: HUBO UN PROBLEMA EN LA TRANSACCIÓN"
                }
            })
            .catch(e => { console.error(e) });
        enviarRespuesta(msg);
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
            if(obj.tipo.toUpperCase() === "solicitud".toUpperCase()) { // Solicitud
                let idLocal = document.getElementById('current-idLocal').value;
                if(obj.toLocal == idLocal) { // Si la solicitud es para este local
                    var html = renderizarAHTML(obj);
                    var htmlAnterior = document.getElementById("notificationContainer").innerHTML
                    document.getElementById("notificationContainer").innerHTML = html + htmlAnterior;
                }
            } else { // Si lo que me llega es una respuesta
                if(obj.to == document.getElementById("current-username").value) // Si son mensajes para el usuario actual
                    alertarRespuesta(obj);
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
    html = '<div class="dropdown-item" id="notificacion-' + obj.id + '">' + 
             '<strong>' + msg + '</strong>' +
             '<a class="dropdown-item botonAceptar" data-id="' + obj.id + '" data-user-from="' + obj.from + 
                '" data-id-producto="' + obj.detalleNotificacion.idProducto + '" data-cantidad="' + obj.detalleNotificacion.cantidad + '" href="#"> Aceptar</a>' +
             '<a class="dropdown-item botonRechazar" data-id="' + obj.id + '" data-user-from="' + obj.from + 
                '" data-id-producto="' + obj.detalleNotificacion.idProducto + '" data-cantidad="' + obj.detalleNotificacion.cantidad + '" href="#"> Rechazar</a>' +
           '</div>' +
           '<div class="dropdown-divider"></div>'
    return html;
}

// Inserta el nodo alert en el DOM
function alertarRespuesta(obj) {
    let alertCloseButton = '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                           '<span aria-hidden="true">&times;</span></button>';
    let htmlAlert = "";
    switch(obj.texto.toUpperCase()) {
      case "ACEPTADO":
        htmlAlert = '<div data-id-notificacion="' + obj.id + '" class="p-4 alert alert-success alert-dismissible fade show" role="alert">' +
                    '<strong>EXITO</strong> El pedido ha sido aceptado' + alertCloseButton + '</div> ';
        break;
      case "RECHAZADO":
        htmlAlert = '<div data-id-notificacion="' + obj.id + '" class="p-4 alert alert-warning alert-dismissible fade show" role="alert">' +
                    '<strong>ERROR</strong> El pedido ha sido rechazado' + alertCloseButton + '</div> ';
        break;
      default: // Algún error
        htmlAlert = '<div data-id-notificacion="' + obj.id + '" class="p-4 alert alert-warning alert-dismissible fade show" role="alert">' +
                    '<strong>ERROR</strong> Hubo un problema al crear el pedido' + alertCloseButton + '</div> ';
    }
    document.getElementById("alertContainer").innerHTML = htmlAlert;
}

function confirmarRespuestas(idNotificacion) {
    if(idNotificacion === null) { // Automáticamente; para confirmar respuestas
        let children = document.getElementById("alertContainer").children;
        let idNotificacion = children[0].dataset.idNotificacion;
    }
    let url = url_api + "/confirmar/" + idNotificacion;
    fetch(url, { method: 'GET' })
        .then(res => {
            if (res.status == 200 || res.status == 201) {
                console.log("Mensaje de respuesta confirmado");
            } else {
                // Si ocurre un error
                console.log("ERROR: HUBO UN PROBLEMA EN LA TRANSACCIÓN")
            }
        })
        .catch(e => { console.error(e) });
}

setInterval(function(){ comprobar() }, TIME_LOOP);
