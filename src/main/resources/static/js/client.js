let stompClient = null;

function connect() {
    disconnect();
    var socket = new SockJS('/notificaciones');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/respuesta', function (json) {
            loadNotifications(JSON.parse(json.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
       stompClient.disconnect();
       console.log("Disconnected");
    }
}

function enviarRespuesta(msg) {
    // Transformamos el JSON a un string para poder enviarlo
    stompClient.send("/app/peticion", {}, JSON.stringify(msg));
}

function loadNotifications(json) {
    if(json.type == "respuesta") { // Si lo que me llega es una respuesta
        if(json.to == document.getElementById("current-username").value) // Si son mensajes para el usuario actual
            alertarResultadoDeSolicitud(json.text);
    } else { // Solicitud
        let idLocal = document.getElementById('current-idLocal').value;
        if(json.toLocal == idLocal) { // Si la solicitud es para este local
            html = renderizarAHTML(json);
            document.querySelector("notificationContainer").innerHTML = html;
        }
    }
    alert("DEBUG: NOTIFICACION RECIBIDA");
}

// Añade los listener a los botones para aceptar el pedido
function agregarListenerABotonesAceptar() {
    let botonesAceptar = document.querySelectorAll('.botonAceptar');
    botonesAceptar.forEach(boton => boton.addEventListener('click', (e) => {
        // Se construye un Objeto msg que contiene la información que el servidor necesita procesar del cliente.
        var msg = {
            id: e.target.dataset.id + 1,
            type: "respuesta",
            status: true, // true = aceptado
            text: "ACEPTADO",
            from: document.getElementById("current-username").value, // El username del usuario actual
            to: e.target.dataset.userFrom // El dueño que envío la solicitud; le devolvemos la respuesta
        };
        // Se realiza la factura
        let urlSolicitud = host + "/pedido/solicitar/" + legajo + "/" + idLocal2 + "/"  + idProducto + "/" + cantidad;
            fetch(urlSolicitud, { method: 'POST' })
                .then(res => {
                    if (res.status == 200 || res.status == 201) {
                        listarMasCercanos(); // Refrescamos la lista de locales cercanos
                    } else {
                        // Si ocurre un error
                        msg.status = false;
                        msg.text = "ERROR: HUBO UN PROBLEMA EN LA TRANSACCIÓN"
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
            id: e.target.dataset.id + 1,
            type: "respuesta",
            status: false, // true = aceptado
            text: "RECHAZADO",
            from: document.getElementById("current-username").value, // El username del usuario actual
            to: e.target.dataset.userFrom // El dueño que envío la solicitud; le devolvemos la respuesta
        };
        enviarRespuesta(msg);
    }));
}

function renderizarAHTML(json) {
    html = '<div class="dropdown-item">' + 
             '<a class="dropdown-item botonAceptar" data-id="' + json.id + '" data-user-from="' + json.from + '" href="#"> Aceptar</a>' +
             '<a class="dropdown-item botonRechazar" data-id="' + json.id + '" data-user-from="' + json.from + '" href="#"> Rechazar</a>' +
           '</div>' +
           '<div class="dropdown-divider"></div>'
    return html;
}

// Inserta el nodo alert en el DOM
function alertarResultadoDeSolicitud(respuesta) {
    let alertCloseButton = '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                           '<span aria-hidden="true">&times;</span></button>';
    let htmlAlert = ""
    switch(respuesta) {
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

window.onload = () => {
    setTimeout(() => { connect(); }, 3000);
    agregarListenerABotonesAceptar();
    agregarListenerABotonesRechazar();
}
