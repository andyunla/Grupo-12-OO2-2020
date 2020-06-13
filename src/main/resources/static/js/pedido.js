// Agrega la id del pedido a la URL para facturarlo
function prepararPedidoId(idPedido) {
     document.getElementById("factura-pedido-form").setAttribute("action", "/factura/confirmarPedido/" + idPedido);
}

// Habilitar facturacion de pedido
function habilitarFacturacion() {
     document.getElementById("botonConfirmar").disabled = false;
}