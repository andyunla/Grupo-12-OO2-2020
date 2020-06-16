const urlFactura = host + '/factura/';

function abrirFactura(e) {
     window.location.href = urlFactura + 'ver/' + e.firstElementChild.innerText;
}

// Detecta si se aplica el filtro por facturas
document.getElementById("botonFiltro").addEventListener('click', async (e) => {
     let empleado = document.getElementsByTagName("select")[0].value;
     let fechaDesde = document.getElementById("fechaDesde").value;
     let fechaHasta = document.getElementById("fechaHasta").value;
     console.log(fechaDesde)
     if(fechaDesde == "") {
          fechaDesde = '1900-01-01';
     }
     if(fechaHasta == "") {
          fechaHasta = '2100-01-01';
     }
     try {
          let response  = await fetch(urlFactura + 'filtrar/' + empleado + '/' + fechaDesde + '/' + fechaHasta);
          let htmlFacturas = await response.text();
          document.getElementsByTagName("tbody")[0].innerHTML = htmlFacturas;
     } catch {
          alert("No se pudo conectar");
     }
     
});



/* TRAER FACTURAS DE UN EMPLEADO 
async function filtrarPorEmpleado() {
     let selectDeEmpleado = document.getElementsByTagName("select")[0];
     try {
           let response  = await fetch(urlFactura + 'empleado/' + selectDeEmpleado.value);
           let htmlList = await response.text();
           document.getElementsByTagName("tbody")[0].innerHTML = htmlList;
     } catch (e) {
          alert("No se pudo conectar");
     }
}*/