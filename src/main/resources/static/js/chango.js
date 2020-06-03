const host = 'http://localhost:8080/chango/';

async function agregarItem(element) {
     let urlNewItem = host + 'nuevo-item/' + element.dataset.idchango + '/' + element.dataset.idproducto;
     try {
          let response = await fetch(urlNewItem, { method: 'POST' });
          // Verifica si el item fue creado, puede que no si ya existía
          if (response.status != 201) {
               throw new Error(response);
          }
          let htmlItem = await response.text();
          // Agrega el item a la tabla del chango
          let template = document.createElement('template');
          template.innerHTML = htmlItem.trim();
          document.getElementById("tablaChango").appendChild(template.content.firstChild);
          // Deshabilita el boton de agregar en la lista de productos
          element.innerText = "LISTO";
          element.disabled = true;
          element.classList.remove('btn-info');
          element.classList.add('btn-success');
          actualizarTotal();
          actualizarStock(element.dataset.idproducto, 1);
     }
     catch (e) {
          console.log("Error, no se pudo agregar");
     }
}

async function eliminarItem(e) {
     let urlDeleteItem = host + 'eliminar-item/' + e.dataset.iditem;
     try {
          let response = await fetch(urlDeleteItem, { method: 'POST' });
          if (response.status == 200) {
               // Devuelvo el stock disponible
               actualizarStock(e.dataset.idproducto, 0);
               // Elimino el item de la vista
               let filaItem = e.parentElement.parentElement.parentElement.parentElement.parentElement;
               filaItem.parentElement.removeChild(filaItem);
               // Rehabilita el boton de agregar en la tabla de productos
               let botonProducto = document.getElementById("botonProducto" + e.dataset.idproducto);
               botonProducto.classList.remove('btn-success');
               botonProducto.classList.add('btn-info');
               botonProducto.innerText = "AGREGAR";
               botonProducto.disabled = false;
               
               // Recalculo el total
               actualizarTotal();
               
          } else {
               console.log("Aca termina")
          }
     }
     catch (e) {
          console.log("Error, no se pudo eliminar");
     }
}

function actualizarTotal() {
     let itemsElements = document.getElementsByClassName("item");
     let total = 0;
     for (let element of itemsElements) {
          // Obtengo el precio de la lista de items en chango y lo multiplico por la cantidad
          let precio = element.children[2].innerText.substring(1);
          let cantidad = document.getElementById('cantidad-' + element.id).value;
          total += (precio * cantidad);
     }
     document.getElementById("total").innerText = '$' + total;
}

function actualizarStock(idProducto, cantidadEnItem) {
    let filaProducto = document.getElementById("producto" + idProducto);
    console.log("FILA DEL PRODUCTO:");
    console.log(filaProducto);

    let columnaStock = filaProducto.children[4];
    console.log("STOCK");
    console.log(columnaStock);
    console.log("cant en item" + cantidadEnItem);
    columnaStock.innerText = parseInt(columnaStock.dataset.stockinicial) - cantidadEnItem;
}

async function modificarCantidad(element, valor = 0) {
     // El valor es la cantidad a sumar o restar de la cantidad actual
     let cantidadInput = document.getElementById("cantidad-item" + element.dataset.iditem);
     let nuevaCantidad = parseInt(cantidadInput.value) + valor;
     // Verifica si hay un valor negativo o cero, de ser así lo devuelve a su valor ultimo
     if (nuevaCantidad < 1) {
          cantidadInput.value = cantidadInput.dataset.lastvalue;
     } else {
          let url = host + 'modificar-item/' + element.dataset.iditem + '/' + nuevaCantidad;
          // Desabilita los botones y el campo de modificación hasta obtener una respuesta del servidor
          element.disabled = true;
          try {
               let response = await fetch(url, { method: 'POST' });
               if (response.status == 200) {
                    cantidadInput.value = nuevaCantidad;
                    cantidadInput.dataset.lastvalue = nuevaCantidad;
                     // Actualizo el stock
                    let filaItemx = document.getElementById("item" + element.dataset.iditem);
                    console.log(filaItemx)
                    let idProductoDelItem = filaItemx.dataset.idproducto;
                    console.log(idProductoDelItem)
                    actualizarStock(idProductoDelItem, nuevaCantidad);
               } else {
                    // Si el servidor no responde OK se asume que es por falta de stock
                    let htmlAlert =
                         '<div class="p-4 alert alert-danger alert-dismissible fade show" role="alert">' +
                         '<strong>STOCK SUPERADO:</strong> No se pudo modificar cantidad' +
                         '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                         '<span aria-hidden="true">&times;</span>' +
                         '</button>' +
                         '</div> ';
                    document.getElementById("alertContainer").innerHTML = htmlAlert;
                    // Se reestablece la cantidad en la vista a la cantidad que tiene en la base de datos
                    cantidadInput.value = await response.json();
               }
               actualizarTotal();
          } catch (e) {
               console.error(e);
          } finally {
               element.disabled = false;
          }
     }
}

function cambiarUnidad(element, valor) {
     // El valor es la cantidad a sumar o restar de la cantidad actual
     let cantidadInput = document.getElementById("cantidad-item" + element.dataset.iditem);
     let nuevaCantidad = parseInt(cantidadInput.value) + valor;
     // Verifica que la cantidad no llegue a cero en caso de estar restando
     if (nuevaCantidad > 0) {
          cantidadInput.value = nuevaCantidad;
          actualizarTotal();
     }
}

function buscar() {
     let valorBuscado = document.querySelector('input[type=search]').value;
     // Obtiene la lista <tr> de filas de la tabla
     let elementosProducto = document.getElementById("productosDisponibles").children;
     if (valorBuscado != "") {
          for (let filaProducto of elementosProducto) {
               // Si el producto de la fila incluye el valor buscado será visible
               let nombreProducto = filaProducto.children[1].innerText;
               if (nombreProducto.toUpperCase().includes(valorBuscado.toUpperCase())) {
                    filaProducto.classList.remove('d-none');
               } else {
                    // Si el producto de la fila no incluye el valor buscado se lo hará invisible
                    filaProducto.classList.add('d-none');
               }
          }
          //Si el valor buscado es una cadena vacía entonces visualizará todos los productos
     } else {
          for (let filaProducto of elementosProducto) {
               filaProducto.classList.remove('d-none');
          }
     }
}

window.onload = () => {
     actualizarTotal();
}