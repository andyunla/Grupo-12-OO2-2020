const host = "http://localhost:8080/chango/";

window.onload = async () => {
     let filasDeChangos = document.querySelectorAll('.chango-row');
     // Agrega el listener para redireccionar las filas a la vista de modificar o ver chango
     filasDeChangos.forEach( chango => {
          chango.addEventListener('click', (e) => {
               let fila =  e.target.parentNode;
               fila.className += ' border border-primary';
               console.log(fila.lastElementChild.innerText);
               if(fila.lastElementChild.innerText == 'Facturado') {
                    window.location.href = host + 'ver/' + fila.firstElementChild.innerText;
               } else {
                    window.location.href = host + fila.firstElementChild.innerText;
               }
          });
     });

     // Agrega el listener para el checkbox 'solo changos abiertos'
     document.getElementById("soloChangosAbiertos").addEventListener('change', (e) => {
          if(e.target.checked) {
               for(let fila of filasDeChangos) {
                    if(fila.children[3].innerText == "Facturado") {
                         fila.classList.add('d-none');
                    }
               }
          } else {
               for(let fila of filasDeChangos) {
                    if(fila.children[3].innerText == "Facturado") {
                         fila.classList.remove('d-none');
                    }
               }
          }
     })
}