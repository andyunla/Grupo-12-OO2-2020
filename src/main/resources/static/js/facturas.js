const host = 'http://localhost:8080/factura/';

function abrirFactura(e) {
     window.location.href = "http://localhost:8080/factura/ver/" + e.firstElementChild.innerText;
}

/* TRAER FACTURAS DE UN EMPLEADO */
async function filtrarPorEmpleado() {
     let selectDeEmpleado = document.getElementsByTagName("select")[0];
     try {
           let response  = await fetch(host + 'empleado/' + selectDeEmpleado.value);
           let htmlList = await response.text();
           document.getElementsByTagName("tbody")[0].innerHTML = htmlList;
     } catch (e) {
          alert("No se pudo conectar");
     }
}