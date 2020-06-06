function abrirFactura(e) {
     window.location.href = "http://localhost:8080/factura/ver/" + e.firstElementChild.innerText;
}