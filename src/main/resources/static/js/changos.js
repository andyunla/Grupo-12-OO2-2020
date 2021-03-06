const urlChango = host + '/chango/';

window.onload = async () => {
     let filasDeChangos = document.querySelectorAll('.chango-row');
     // Agrega el listener para redireccionar las filas a la vista de modificar o ver chango
     filasDeChangos.forEach( chango => {
          chango.addEventListener('click', (e) => {
               let fila =  e.target.parentNode;
               fila.className += ' border border-primary';
               console.log(fila.lastElementChild.innerText);
               if(fila.lastElementChild.innerText == 'Facturado') {
                    window.location.href = urlChango + 'ver/' + fila.firstElementChild.innerText;
               } else {
                    window.location.href = urlChango + fila.firstElementChild.innerText;
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
     });
     // Difumina el alert de chango eliminado, en caso que hubiese
     var fadeTarget = document.getElementById("alertChangoEliminado");
     if(fadeTarget != null) {
          fadeOutEffect(fadeTarget);
     }
}

function fadeOutEffect(fadeTarget) {
     let escala = 0.002;
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