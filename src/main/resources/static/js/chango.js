const url = 'http://localhost:8080/chango/';

async function agregarItem(element) {
     let urlNewItem = url + 'nuevo-item/' + element.dataset.idchango + '/' + element.dataset.idproducto;
     try {
          let response = await fetch(urlNewItem, { method: 'POST' });
          // Verifica si el item fue creado, puede que no si ya existía
          if(response.status != 201){
               throw new Error(response);
          }
          let html = await response.text();
          // Agrega el item a la tabla del chango
          document.getElementById("tablaChango").innerHTML += html;
          // Deshabilita el boton de agregar en la lista de productos
          element.innerText = "LISTO";
          element.disabled = true;
          element.classList.remove('btn-info');
          element.classList.add('btn-success');
     }
     catch (e) {
          console.log("Error, no se pudo agregar");
     }
}

async function eliminarItem(e){
     let urlDeleteItem = url + 'eliminar-item/' + e.dataset.iditem;
     try {
          let response = await fetch(urlDeleteItem, { method: 'POST' });
          if(response.status == 200){
               // Elimina el item de la tabla del chango
               let filaItem = e.parentElement.parentElement.parentElement.parentElement.parentElement; 
               filaItem.parentElement.removeChild(filaItem);
               // Rehabilita el boton de agregar en la tabla de productos
               let botonProducto = document.getElementById("producto" + e.dataset.idproducto);
               botonProducto.classList.remove('btn-success');
               botonProducto.classList.add('btn-info');
               botonProducto.innerText = "AGREGAR";
               botonProducto.disabled = false;
          }
     }
     catch (e) {
          console.log("Error, no se pudo eliminar");
     }
}