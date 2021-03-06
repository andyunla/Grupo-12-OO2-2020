const TIME_LOOP = 4000; // Cada 4s
const host = "http://localhost:8080";
const url_api = host + "/api/v1/notificacion";
const url_pedido = host + "/pedido";
var cant = 0; // Para determinar si recibimos notificaciones

/* Comprobar si hay nuevas notificaciones para el usuario */
function comprobar() {
    cant = 0; // En cada comprobación reiniciamos
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
    let notificacionItem = document.getElementById("notificacion-" + respuesta.id);
    if(notificacionItem) {
        notificacionItem.remove();
    }
}

function cargarNotificaciones(lista) {
    var size = lista.length;
    if(size !== 0) {
        var i;
        // Borramos todo el contenido del área de notificación
        document.getElementById("notificationContainer").innerHTML = "";
        for (i = 0; i < size; i++) {
            var obj = lista[i];
            let html = renderizarAHTML(obj);
            var htmlAnterior = document.getElementById("notificationContainer").innerHTML;
            document.getElementById("notificationContainer").innerHTML = html + htmlAnterior;
        }
        console.log("Notificación recibida");
        if(cant >= 1) {
            document.getElementById("img-bell").src = "/images/bell-ring.png";
        } else {
            document.getElementById("img-bell").src = "/images/bell.png";
        }
    } else {
        let html = document.getElementById("notificationContainer").innerHTML; // Obtenemos lo anterior
        if(document.getElementById("notificationContainer").childElementCount === 0) {
            html += "<small> No hay notificaciones </small>";
        }
        document.getElementById("notificationContainer").innerHTML = html;
    }
}

function renderizarAHTML(obj) {
    var paraEsteDestinatario = false; // Para no cargar respuestas vacías
    var alert_tipo = "alert-primary";
    if(obj.tipo.toUpperCase() === "SOLICITUD") {
        var msgCorto = `El usuario ${obj.from} ha realizado una solicitud de un producto`;
        paraEsteDestinatario = true;
        var url = `/notificacion/ver?id=${obj.id}`;
    } else { // Respuestas
        if(obj.to == document.getElementById("current-username").value) { // Si son mensajes para el usuario actual
            switch(obj.estado.toUpperCase()) {
                case "ACEPTADO":
                    var msgCorto = `El pedido ha sido aceptado`;
                    alert_tipo = "alert-success";
                    break;
                case "RECHAZADO":
                    var msgCorto = `El pedido ha sido rechazado`;
                    alert_tipo = "alert-danger";
                    break;
                default:
                    var msg = obj.estado;
                    alert_tipo = "alert-danger";
                    break;
            }
            paraEsteDestinatario = true;
            var url = `/pedido/ver?id=${obj.detalleNotificacion.idPedidoStock}&msgid=${obj.id}`;
        }
    }
    let html = "";
    // En el caso que sea una respuesta hay que prevenir que no se envíen a todos
    // los usuarios del local actual; sólo al que le corresponde
    if(paraEsteDestinatario) {
        var row = `<a class="dropdown-item alert ${alert_tipo}" role="alert" href="${url}"> ${msgCorto}</a>`;
        cant += 1;
    }
    html += row;
    return html;
}

function confirmarRespuestas(idNotificacion) {
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

setInterval(function(){ comprobar() }, TIME_LOOP);
