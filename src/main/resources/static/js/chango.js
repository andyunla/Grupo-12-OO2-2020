const urlChango = host + '/chango/';
/* Flags para detectar si ya se puede habilitar la facturación */
var clienteElegido = false;
var changoConItem = false;
var idChango = 0;   // Id del chango actual
var spinner = '<tr id="spinnerProductos" class="spinner-border text-dark"' +
'style="width: 3rem; height: 3rem;" role="status">' +
'<span class="sr-only m-5">Loading...</span></tr>';

async function traerProductosDisponibles() {
     let url = urlChango + 'productos-disponibles/' + idChango;
     let productosDisponibles = document.getElementById("productosDisponibles");
     productosDisponibles.innerHTML = spinner;
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

//Deshabilita o habilita el boton de agregar producto disponible
function actualizarBoton(idProducto) {
     let boton = document.getElementById('botonProducto' + idProducto);
     boton.innerText = (boton.innerText == "LISTO") ? "AGREGAR" : "LISTO";
     boton.disabled = !boton.disabled;
     boton.classList.toggle('btn-info');
     boton.classList.toggle('btn-success');
}

// Agrega un nuevo item al chango
async function agregarItem(idProducto) {
     let urlNuevoItem = urlChango + 'nuevo-item/' + idChango + '/' + idProducto;
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
          actualizarBoton(idProducto);
          actualizarTotal();
     }
     catch (e) {
          alert("ERROR, no se pudo agregar");
          console.error(e);
     } finally {
          actualizarStock(idProducto);
     }
}

async function eliminarItem(idItem, idProducto) {
     let urlDeleteItem = urlChango + 'eliminar-item/' + idItem;
     try {
          let response = await fetch(urlDeleteItem, { method: 'POST' });
          if (response.status == 200) {
               //actualizarStock(e.dataset.idproducto, 0); ******************************
               // Elimino el item de la vista
               let filaItem = document.getElementById('idItem' + idItem);
               filaItem.parentElement.removeChild(filaItem);
               actualizarBoton(idProducto);
               actualizarTotal();
               actualizarStock(idProducto);
          } else {
               throw new Error();
          }
     }
     catch (e) {
          alert("Error, no se pudo eliminar");
     }
}

// Agrega una unidad o resta una unidad a un item
function sumar(element, idItem, cantidad, idProducto) {
     let inputCantidad = document.getElementById("cantidad-item" + idItem);
     inputCantidad.value = parseInt(inputCantidad.value) + cantidad;
     cambiarCantidad(element, idItem, idProducto);
}

// Cambia la cantidad de un item
async function cambiarCantidad(element, idItem, idProducto) {
     let cantidadInput = document.getElementById("cantidad-item" + idItem);
     // Verifica que no se intente cambiar a un valor negativo o cero
     if (cantidadInput.value > 0) {
          let url = urlChango + 'modificar-item/' + idItem + '/' + cantidadInput.value;
          element.disabled = true; // Deshabilita el boton que está modificando hasta terminar la operación
          try {
               let response = await fetch(url, { method: 'POST' });
               if (response.status == 200) {
                    cantidadInput.dataset.lastvalue = cantidadInput.value;
                    actualizarTotal();
               } else {
                    throw new Error();
               }
          } catch (e) {
               // Se reestablece la cantidad en la vista a la cantidad que tiene en la base de datos
               cantidadInput.value = cantidadInput.dataset.lastvalue;
               let htmlAlert =
                    '<div id="alert" class="p-4 alert alert-danger alert-dismissible fade show" role="alert">' +
                    '<strong>STOCK SUPERADO:</strong> No se pudo seleccionar esa cantidad pero' + 
                    '<a href="http://localhost:8080/pedido/"> puede hacer un pedido a otro local</a>' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span>' +
                    '</button>' +
                    '</div> ';
               let alertContainer = document.getElementById("alertContainer");
               alertContainer.innerHTML = htmlAlert;
               fadeOutEffect(document.getElementById("alert"));
          } finally {
               actualizarStock(idProducto);
               element.disabled = false;
          }
     } else {
          cantidadInput.value = cantidadInput.dataset.lastvalue;
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

async function actualizarStock(idProducto) {
     let url = urlChango + 'stock/' + idProducto;
     try {
          let response = await fetch(url);
          let stock = await response.text();
          let filaProducto = document.getElementById("producto" + idProducto);
          let columnaStock = filaProducto.children[3];
          columnaStock.innerHTML = stock;
     }
     catch(e) {
          alert("Hubo un problema para actualizar el stock");
     }
}

/* BUSCA UN PRODUCTO POR SU NOMBRE */
function buscar(e) {
     let valorBuscado = e.value;
     // Obtiene la lista <tr> de filas de la tabla
     let elementosProducto = document.getElementById("productosDisponibles").children;
     if (valorBuscado != "") {
          for (let filaProducto of elementosProducto) {
               // Si el producto de la fila incluye el valor buscado será visible
               let nombreProducto = filaProducto.children[0].innerText;
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

function activarClienteElegido(e) {
     clienteElegido = true;
     // Habilita el boton de confirmar la factura si ya hay un chango cargado
     document.getElementById("botonConfirmar").disabled = !(changoConItem && clienteElegido);
}

// Efecto de desvanecido
function fadeOutEffect(fadeTarget) {
     let escala = 0.0025;
     var fadeEffect = setInterval(function () {
          if (!fadeTarget.style.opacity) {
               fadeTarget.style.opacity = 1;
          }
          if (fadeTarget.style.opacity > 0) {
               fadeTarget.style.opacity -= escala;
               if (fadeTarget.style.opacity < 0.9) {
                    escala = 0.07;
               }
          } else {
               clearInterval(fadeEffect);
          }
     }, 100);
}

window.onload = () => {
     idChango = document.getElementById("idChango").innerText;
     traerProductosDisponibles();
     traerItems();
}
