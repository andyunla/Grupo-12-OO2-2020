const host = "http://localhost:8080";
const url = host + "/distancia/traer";
//const url = host + "/api/v1/local/distancia/";

window.onload = () => {
     let localDesde = document.getElementById("localDesde");
     let productoDeLotes = document.getElementById("productoDeLotes");
     let cantidadProducto = document.getElementById("cantidadProducto");

     function listarMasCercanos() {
          if (localDesde.selectedIndex != 0 && productoDeLotes.selectedIndex != 0 && cantidadProducto.value > 0) {
               let idLocalDesde = localDesde.options[localDesde.selectedIndex].value;
               let idProductoDeLotes = productoDeLotes.options[productoDeLotes.selectedIndex].value;
               let cantidad = cantidadProducto.value;
               fetch(url + "/" + idLocalDesde + "/" + idProductoDeLotes + "/" + cantidad)
                    .then(response => response.text())
                    .then(html => {
                         document.querySelector("tbody").innerHTML = html;
                    })
                    .catch((e) => {
                         console.log(e);
                    });
          }
     }

     localDesde.addEventListener('change', listarMasCercanos);
     productoDeLotes.addEventListener('change', listarMasCercanos);
     cantidadProducto.addEventListener('input', listarMasCercanos);
}
