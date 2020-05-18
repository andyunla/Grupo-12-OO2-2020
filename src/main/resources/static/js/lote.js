const url = 'http://localhost:8080/lote/';

// Modifica el color de las filas de lotes inactivos
function cambiarColorALotesInactivos() {
     let lotes = document.querySelectorAll('.lote-estado');
     for (let lote of lotes) {
          if (lote.dataset.activo == 'false') {
               lote.parentElement.classList.add('text-danger')
          }
     }
}

// Añade los listener a los botones de eliminar
function agregarListenerABotonesEliminar() {
     let botonesEliminar = document.querySelectorAll('.botonEliminar');
     botonesEliminar.forEach(boton => boton.addEventListener('click', (e) => {
          fetch(url + 'eliminar/' + e.target.dataset.idlote, { method: 'POST' })
               .then(res => {
                    if (res.status == 200) {
                         // Saca la fila eliminada de la tabla 
                         let fila = e.target.parentElement.parentElement;
                         let tabla = fila.parentElement;
                         tabla.removeChild(fila);
                    }
                    alertarResultadoDeEliminacion(res.status == 200);
               })
               .catch(e => { console.error(e) });
     }));
}

// Inserta el nodo alert en el DOM, segun el resultado booleano que reciba
function alertarResultadoDeEliminacion(resultado) {
     let alertCloseButton = '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
          '<span aria-hidden="true">&times;</span></button>';
     let htmlAlert = (resultado) ?
          '<div class="p-4 alert alert-success alert-dismissible fade show" role="alert">' +
          '<strong>EXITO</strong> El lote se eliminó correctamente' + alertCloseButton + '</div> '
          :
          '<div class="p-4 alert alert-warning alert-dismissible fade show" role="alert">' +
          '<strong>ERROR</strong> El lote no pudo ser eliminado' + alertCloseButton + '</div> ';
     document.getElementById("alertContainer").innerHTML = htmlAlert;
}

// Añade los listener a cada opcion de la lista de lotes
function agregarListenerAOpciones() {
     // Opciones del DOM
     let selectDeLocal = document.getElementById("localDeLotes");
     let selectDeProducto = document.getElementById("productoDeLotes");
     let checkboxSoloActivos = document.getElementById("soloLotesActivos");
     let opcionesDeVisalizacion = [selectDeLocal, selectDeProducto, checkboxSoloActivos];
     // Agrego los listener al cambio de cada una de las opciones
     opcionesDeVisalizacion.forEach(opcion => opcion.addEventListener('change', () => {
          let idLocal = selectDeLocal.value;
          let idProducto = selectDeProducto.value;
          let soloActivos = checkboxSoloActivos.checked;
          fetch(url + 'traer/' + idLocal + '/' + idProducto + '/' + soloActivos)
               .then(response => response.text())
               .then(html => {
                    document.querySelector("tbody").innerHTML = html;
                    // Reestablezco los colores y botones de eliminacion
                    cambiarColorALotesInactivos();
                    agregarListenerABotonesEliminar();
               })
               .catch((e) => {
                    alert(e);
               });
     }));
}
window.onload = () => {
     // Establece por defecto la fecha de hoy al formulario de ingresar nuevo lote
     document.getElementById('nuevoLoteFecha').valueAsDate = new Date();
     cambiarColorALotesInactivos();
     agregarListenerABotonesEliminar();
     agregarListenerAOpciones();

}