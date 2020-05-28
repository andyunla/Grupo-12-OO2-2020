const url = 'http://localhost:8080/chango/';

async function agregarItem(element) {
     let urlNewItem = url + 'nuevo-item/' + element.dataset.idchango + '/' + element.dataset.idproducto;
     try {
          let response = await fetch(urlNewItem, { method: 'POST' });
          // Verifica si el item fue creado, puede que no si ya existía
          if (response.status != 201) {
               throw new Error(response);
          }
          let htmlItem = await response.text();
          // Agrega el item a la tabla del chango
          let template = document.createElement('template');
          template.innerHTML =  htmlItem.trim();
          document.getElementById("tablaChango").appendChild(template.content.firstChild);
          // Deshabilita el boton de agregar en la lista de productos
          element.innerText = "LISTO";
          element.disabled = true;
          element.classList.remove('btn-info');
          element.classList.add('btn-success');
          actualizarTotal();
     }
     catch (e) {
          console.log("Error, no se pudo agregar");
     }
}

async function eliminarItem(e) {
     let urlDeleteItem = url + 'eliminar-item/' + e.dataset.iditem;
     try {
          let response = await fetch(urlDeleteItem, { method: 'POST' });
          if (response.status == 200) {
               // Elimina el item de la tabla del chango
               let filaItem = e.parentElement.parentElement.parentElement.parentElement.parentElement;
               filaItem.parentElement.removeChild(filaItem);
               // Rehabilita el boton de agregar en la tabla de productos
               let botonProducto = document.getElementById("producto" + e.dataset.idproducto);
               botonProducto.classList.remove('btn-success');
               botonProducto.classList.add('btn-info');
               botonProducto.innerText = "AGREGAR";
               botonProducto.disabled = false;
               actualizarTotal();
          }
     }
     catch (e) {
          console.log("Error, no se pudo eliminar");
     }
}

function actualizarTotal(element) {
     if( element != undefined && element.value < 1) {
          element.value = 1;
          actualizarTotal();
          return 0;
     }
     let itemsElements = document.getElementsByClassName("item");
     let total = 0;
     for (let element of itemsElements) {
          let precio = element.children[2].innerText;
          let cantidad = document.getElementById('cantidad-' + element.id).value;
          total += (precio * cantidad);
     }
     document.getElementById("total").innerText =  '$' + total;
}

function modificarCantidad(element, valor) {
     let cantidadInput = document.getElementById("cantidad-item" + element.dataset.iditem);
     let nuevaCantidad = parseInt(cantidadInput.value) + valor;
     if(nuevaCantidad > 0) {
          cantidadInput.value = nuevaCantidad;
          actualizarTotal();
     }
}