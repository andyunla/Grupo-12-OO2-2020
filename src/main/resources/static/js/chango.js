const urlChango = host + '/chango/';
/* Flags para detectar si ya se puede habilitar la facturación */
var clienteElegido = false;
var changoConItem = false;
var idChango = 0;   // Id del chango actual

async function traerProductosDisponibles() {
     let url = urlChango + 'productos-disponibles/' + idChango;
     try {
          let response = await fetch(url);
          let productosHTML = await response.text();
          document.getElementById("productosDisponibles").innerHTML = productosHTML;
     } catch (e) {
          console.error(e);
          alert("No se pudo conectar al servidor");
     }
}

async function traerItems() {
     let url = urlChango + 'items/' + idChango;
     try {
          let response = await fetch(url);
          let itemsHTML = await response.text();
          document.getElementById("itemsChango").innerHTML = itemsHTML;
     } catch (e) {
          console.error(e);
          alert("No se pudo conectar al servidor");
     } finally {
          actualizarTotal();
     }
}

function sumar(idItem, cantidad) {
     
}

async function agregarItem(idProducto) {
     let urlNuevoItem = urlChango + 'nuevo-item/' + idChango + '/' + idProducto;
     let boton = document.getElementById('botonProducto' + idProducto);
     try {
          let response = await fetch(urlNuevoItem, { method: 'POST' });
          // Verifica si el item fue creado, puede que no si ya existía
          if (response.status != 201) {
               throw new Error(response);
          }
          let htmlItem = await response.text();
          // Agrega el item a la tabla del chango
          let template = document.createElement('template');
          template.innerHTML = htmlItem.trim();
          document.getElementById("itemsChango").appendChild(template.content.firstChild);
          // Deshabilita el boton de agregar en la lista de productos
          boton.innerText = "LISTO";
          boton.disabled = true;
          boton.classList.remove('btn-info');
          boton.classList.add('btn-success');
          actualizarTotal();
          //actualizarStock(idProducto, 1);
     }
     catch (e) {
          alert("ERROR, no se pudo agregar");
          console.error(e);
     }
}

async function eliminarItem(idItem, idProducto) {
     let urlDeleteItem = urlChango + 'eliminar-item/' + idItem;
     try {
          let response = await fetch(urlDeleteItem, { method: 'POST' });
          if (response.status == 200) {
               // Devuelvo el stock disponible
               //actualizarStock(e.dataset.idproducto, 0);
               // Elimino el item de la vista
               let filaItem = document.getElementById('idItem' + idItem);
               filaItem.parentElement.removeChild(filaItem);
               // Rehabilita el boton de agregar en la tabla de productos
               let botonProducto = document.getElementById("botonProducto" + idProducto);
               botonProducto.classList.remove('btn-success');
               botonProducto.classList.add('btn-info');
               botonProducto.innerText = "AGREGAR";
               botonProducto.disabled = false;
               actualizarTotal();
          } else {
               throw new Error();
          }
     }
     catch (e) {
          alert("Error, no se pudo eliminar");
     }
}

/* AGREGA UN ITEM DE LA TABLA DE PRODUCTOS DISPONIBLES AL CHANGO 
async function agregarItem(element) {
     let urlNewItem = urlChango + 'nuevo-item/' + element.dataset.idchango + '/' + element.dataset.idproducto;
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

/* ELIMINA UN ITEM DE LA TABLA DEL CHANGO 
async function eliminarItem(e) {
     let urlDeleteItem = urlChango + 'eliminar-item/' + e.dataset.iditem;
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
               throw new Error();
          }
     }
     catch (e) {
          alert("Error, no se pudo eliminar");    // TODO: A cambiar por un alert con con estilos
     }
}

/* MODIFICA LA CANTIDAD DE UN ITEM 
async function modificarCantidad(element, valor = 0) {
     // El valor es la cantidad a sumar o restar de la cantidad actual
     let cantidadInput = document.getElementById("cantidad-item" + element.dataset.iditem);
     let nuevaCantidad = parseInt(cantidadInput.value) + valor;
     // Verifica si hay un valor negativo o cero, de ser así lo devuelve a su valor ultimo
     if (nuevaCantidad < 1) {
          cantidadInput.value = cantidadInput.dataset.lastvalue;
     } else {
          let url = urlChango + 'modificar-item/' + element.dataset.iditem + '/' + nuevaCantidad;
          // Desabilita los botones y el campo de modificación hasta obtener una respuesta del servidor
          element.disabled = true;
          try {
               let response = await fetch(url, { method: 'POST' });
               if (response.status == 200) {
                    cantidadInput.value = nuevaCantidad;
                    cantidadInput.dataset.lastvalue = nuevaCantidad;
                     // Actualizo el stock
                    let filaDelItem = document.getElementById("item" + element.dataset.iditem);
                    let idProductoDelItem = filaDelItem.dataset.idproducto;
                    actualizarStock(idProductoDelItem, nuevaCantidad);
               } else {
                    // Si el servidor no responde OK se asume que es por falta de stock
                    let htmlAlert =
                         '<div id="alert" class="p-4 alert alert-danger alert-dismissible fade show" role="alert">' +
                         '<strong>STOCK SUPERADO:</strong> No se pudo modificar cantidad' +
                         '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                         '<span aria-hidden="true">&times;</span>' +
                         '</button>' +
                         '</div> ';
                    document.getElementById("alertContainer").innerHTML = htmlAlert;;
                    // Se reestablece la cantidad en la vista a la cantidad que tiene en la base de datos
                    cantidadInput.value = await response.json();
                    let alert = document.getElementById("alert");
                    fadeOutEffect(alert);
               }
               actualizarTotal();
          } catch (e) {
               console.error(e);
          } finally {
               element.disabled = false;
          }
     }
}

/* AGREGA O RESTA UNA UNIDAD A UN ITEM 
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

/* ACTUALIZA EL TOTAL QUE SE MUESTRA */
function actualizarTotal() {
     let itemsElements = document.getElementsByClassName("item");
     let total = 0;
     for (let element of itemsElements) {
          // Obtengo el precio de la lista de items en chango y lo multiplico por la cantidad
          let precio = element.children[2].innerText;
          let cantidad = document.getElementById('cantidad-item' + element.dataset.iditem).value;
          total += (precio * cantidad);
     }
     document.getElementById("total").innerText = '$' + total;
     // Habilita el boton de confirmar la factura si ya hay un chango cargado y un cliente elegido
     changoConItem = total != 0;
     document.getElementById("botonConfirmar").disabled = !(changoConItem && clienteElegido);
}

/* ACTUALIZA EL STOCK QUE SE MUESTRA EN LA TABLA PRODUCTOS 
function actualizarStock(idProducto, cantidadEnItem) {
    let filaProducto = document.getElementById("producto" + idProducto);
    let columnaStock = filaProducto.children[4];
    columnaStock.innerText = parseInt(columnaStock.dataset.stockinicial) - cantidadEnItem;
}*/

/* BUSCA UN PRODUCTO POR SU NOMBRE */
function buscar(e) {
     let valorBuscado = e.value;
     console.log(valorBuscado)
     // Obtiene la lista <tr> de filas de la tabla
     let elementosProducto = document.getElementById("productosDisponibles").children;
     if (valorBuscado != "") {
          for (let filaProducto of elementosProducto) {
               // Si el producto de la fila incluye el valor buscado será visible
               let nombreProducto = filaProducto.children[0].innerText;
               console.log(nombreProducto)
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

// Efecto de desvanecido
function fadeOutEffect(fadeTarget) {
     let escala = 0.003;
     var fadeEffect = setInterval(function () {
         if (!fadeTarget.style.opacity) {
             fadeTarget.style.opacity = 1;
         }
         if (fadeTarget.style.opacity > 0) {
             fadeTarget.style.opacity -= escala;
             if(fadeTarget.style.opacity < 0.9) {
                  escala = 0.07;
             }
         } else {
             clearInterval(fadeEffect);
         }
     }, 100);
 }
/*
 function activarClienteElegido(e) {
     clienteElegido = true;
     // Habilita el boton de confirmar la factura si ya hay un chango cargado
     document.getElementById("botonConfirmar").disabled = !(changoConItem && clienteElegido);
 }
*/
 window.onload = () => {
     idChango = document.getElementById("idChango").innerText;
     traerProductosDisponibles();
     traerItems();
}
