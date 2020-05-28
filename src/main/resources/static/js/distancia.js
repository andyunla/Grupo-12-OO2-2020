const host = "http://localhost:8080";
const url = host + "/distancia/traer";
//const url = host + "/api/v1/local/distancia/";

window.onload = () => {
     let localActual = document.getElementById("localActual");
     let productoDeLotes = document.getElementById("productoDeLotes");
     let cantidadProducto = document.getElementById("cantidadProducto");

     function listarMasCercanos() {
          if (productoDeLotes.selectedIndex != 0 && cantidadProducto.value > 0) {
               let idLocalActual = localActual.value;
               let idProductoDeLotes = productoDeLotes.options[productoDeLotes.selectedIndex].value;
               let cantidad = cantidadProducto.value;
               fetch(url + "/" + idLocalActual + "/" + idProductoDeLotes + "/" + cantidad)
                    .then(response => response.text())
                    .then(html => {
                         document.querySelector("tbody").innerHTML = html;
                    })
                    .catch((e) => {
                         console.log(e);
                    });
          }
     }

     productoDeLotes.addEventListener('change', listarMasCercanos);
     cantidadProducto.addEventListener('input', listarMasCercanos);
}
