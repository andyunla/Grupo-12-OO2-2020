let tipoNotificacion = document.getElementById("tipoNotificacion");
let estadoNotificacion = document.getElementById("estadoNotificacion");
let localActual = document.getElementById("current-idLocal");

function actualizarBotones() {
    agregarListenerABotonesAceptar();
    agregarListenerABotonesRechazar();
}

// Añade los listener a los botones para aceptar el pedido
function agregarListenerABotonesAceptar() {
    let botonesAceptar = document.querySelectorAll('.botonAceptar');
    botonesAceptar.forEach(boton => boton.addEventListener('click', (e) => {
        $("*").addClass("esperando");
        var detalleEnvio = {
            idNotificacion: e.target.dataset.idNotificacion,
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
        $("*").addClass("esperando");
        var detalleEnvio = {
            idNotificacion: e.target.dataset.idNotificacion,
            userOferente: null,
            userSolicitante: e.target.dataset.userFrom,
            aceptado: false,
            idProducto: e.target.dataset.idProducto,
            cantidad: e.target.dataset.cantidad,
            to: e.target.dataset.userFrom
        };
        realizarSolicitudPedido(detalleEnvio);
    }));
}

// 1ro se realiza el pedido y obtenemos el id en caso que se haya realizado con éxito.
// Luego se envía la respuesta
async function realizarSolicitudPedido(detalleEnvio) {
    // Se construye un Objeto msg que contiene los datos para enviar en la respuesta; no para realizar el pedido
    var respuesta = {
        id: detalleEnvio.idNotificacion,
        tipo: "RESPUESTA",
        estado: detalleEnvio.aceptado? "ACEPTADO" : "RECHAZADO",
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
    let urlSolicitud = url_pedido + "/solicitar/" + userSolicitante + "/" + userOferente + "/" + fueAceptado + "/" + idProducto + "/" + cantidad;
    try {
        let response = await fetch(urlSolicitud, { method: 'POST' });
        if (response.ok) { // 200 - 299
            let text = await response.text();
            console.log("Solicitud realizada");
            respuesta.detalleNotificacion = JSON.parse(text); // El id del pedido y el chango realizado
        } else {
            respuesta.estado = "ERROR AL REALIZAR LA SOLICITUD";
            throw new Error();
        }
    } catch(e) {
        console.error(e);
    }
    // Enviamos la respuesta
    enviarRespuesta(respuesta);

    // Refrescmos la vista
    setTimeout(listarNotificaciones, 1000);
}

function listarNotificaciones() {
    estadoNotificacion.setAttribute("disabled", "disabled");
    listaEstados = ["todos", "pendiente", "aceptado", "rechazado"];
    if (tipoNotificacion.selectedIndex == 0) { // Todos
        var url = host + "/notificacion/consultar";
    } else if (tipoNotificacion.selectedIndex == 1) { // Solicitudes
        estadoNotificacion.removeAttribute("disabled");
        let idLocalActual = localActual.value;
        let estadoSeleccionado = listaEstados[estadoNotificacion.selectedIndex];
        var url = host + "/notificacion/consultar/" + idLocalActual + "/" + estadoSeleccionado;
    } else { // Respuestas
        let usuarioActual = document.getElementById("current-username").value;
        var url = host + "/notificacion/consultar/" + usuarioActual;
    }

    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.querySelector("tbody").innerHTML = html;
            actualizarBotones();
        })
        .catch((e) => {
            console.log(e);
        }).finally( () => {
            $("*").removeClass("esperando");
        });
}

actualizarBotones();
tipoNotificacion.addEventListener('change', listarNotificaciones);
estadoNotificacion.addEventListener('change', listarNotificaciones);
