const host = "http://localhost:8080";

window.onload = () => {
     let legajoUser = document.getElementById("legajoUser");
     let localActual = document.getElementById("localActual");
     let productoDeLotes = document.getElementById("productoDeLotes");
     let cantidadProducto = document.getElementById("cantidadProducto");

     // Añade los listener a los botones de solicitar
     function agregarListenerABotonesSolicitar() {
          let botonesSolicitar = document.querySelectorAll('.botonSolicitar');
          botonesSolicitar.forEach(boton => boton.addEventListener('click', (e) => {
               let legajo = legajoUser.value;
               let idLocal2 = e.target.dataset.idlocal;
               let idProducto = productoDeLotes.value;
               let cantidad = cantidadProducto.value;
               let urlSolicitud = host + "/pedido/solicitar/" + legajo + "/" + idLocal2 + "/"  + idProducto + "/" + cantidad;
               fetch(urlSolicitud, { method: 'POST' })
                    .then(res => {
                         if (res.status == 200 || res.status == 201) {
                              // let estado = await res.json();
                              listarMasCercanos(); // Refrescamos la lista de locales cercanos
                         }
                         alertarResultadoDeSolicitud(res.status);
                    })
                    .catch(e => { console.error(e) });
          }));
     }

     // Inserta el nodo alert en el DOM, segun el resultado booleano que reciba
     function alertarResultadoDeSolicitud(resultado) {
          let alertCloseButton = '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
               '<span aria-hidden="true">&times;</span></button>';
          let htmlAlert = (resultado == 201) ?
               '<div class="p-4 alert alert-success alert-dismissible fade show" role="alert">' +
               '<strong>EXITO</strong> El pedido se agregó correctamente' + alertCloseButton + '</div> '
               :
               '<div class="p-4 alert alert-warning alert-dismissible fade show" role="alert">' +
               '<strong>ERROR</strong> Hubo un problema al crear el pedido' + alertCloseButton + '</div> ';
          document.getElementById("alertContainer").innerHTML = htmlAlert;
     }

     function listarMasCercanos() {
          if (productoDeLotes.selectedIndex != 0 && cantidadProducto.value > 0) {
               let idLocalActual = localActual.value;
               let idProductoDeLotes = productoDeLotes.options[productoDeLotes.selectedIndex].value;
               let cantidad = cantidadProducto.value;
               let url = host + "/distancia/";
               fetch(url + "traer/" + idLocalActual + "/" + idProductoDeLotes + "/" + cantidad)
                    .then(response => response.text())
                    .then(html => {
                         document.querySelector("tbody").innerHTML = html;
                         agregarListenerABotonesSolicitar();
                    })
                    .catch((e) => {
                         console.log(e);
                    });
          } else { // Cuando se selecciona la opción "Seleccione producto"
               document.querySelector("tbody").innerHTML = "";
          }
     }

     productoDeLotes.addEventListener('change', listarMasCercanos);
     cantidadProducto.addEventListener('input', listarMasCercanos);
}
