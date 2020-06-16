const urlFactura = host + '/factura/';

function abrirFactura(e) {
     window.location.href = urlFactura + 'ver/' + e.firstElementChild.innerText;
}

// Detecta si se aplica el filtro por facturas
document.getElementById("botonFiltro").addEventListener('click', async (e) => {
     let empleado = document.getElementsByTagName("select")[0].value;
     let fechaDesde = document.getElementById("fechaDesde").value;
     let fechaHasta = document.getElementById("fechaHasta").value;
     if(fechaDesde == "") {
          fechaDesde = '1900-01-01';
     }
     if(fechaHasta == "") {
          fechaHasta = '2100-01-01';
     }
     // Verifica que la fechaDesde sea anterior o igual a fechaHasta
     if(new Date(fechaDesde) <= new Date(fechaHasta)){
          try {
               let response  = await fetch(urlFactura + 'filtrar/' + empleado + '/' + fechaDesde + '/' + fechaHasta);
               let htmlFacturas = await response.text();
               document.getElementsByTagName("tbody")[0].innerHTML = htmlFacturas;
          } catch {
               alert("No se pudo conectar");
          }
     } else {
          let htmlAlert =
                    '<div id="alert" class="p-4 alert alert-danger alert-dismissible fade show" role="alert">' +
                    '<strong>FECHA NO VÁLIDA:</strong> La fecha "desde" debe ser anterior o igual a la fecha "hasta"' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span>' +
                    '</button>' +
                    '</div> ';
               let alertContainer = document.getElementById("alertContainer");
               alertContainer.innerHTML = htmlAlert;
               fadeOutEffect(document.getElementById("alert"));
     }
});

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
     // Muestra el mensaje de ayuda y luego de 5 seg. lo deshabilita
     $('#contenedorBotonFiltro').tooltip('show');
     setTimeout( () => { 
          $('#contenedorBotonFiltro').tooltip('hide');
          $('#contenedorBotonFiltro').tooltip('disable');
     }, 5500);
}