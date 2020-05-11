window.onload = () => {
     let localDesde = document.getElementById("localDesde");
     let localHasta = document.getElementById("localHasta");
     let distancia = document.getElementById("distancia");

     function calcularDistancia() {
          // Condicion para verificar que hay dos locales elegidos 
          if (localDesde.selectedIndex != 0 && localHasta.selectedIndex != 0) {
               let idLocalDesde = localDesde.options[localDesde.selectedIndex].value;
               let idLocalHasta = localHasta.options[localHasta.selectedIndex].value;
               distancia.innerText = "cargando";
               fetch("http://localhost:8080/api/v1/local/distancia/" + idLocalDesde + "/" + idLocalHasta)
                    .then((response) => {
                         return response.json();
                    })
                    .then((res) => {
                         distancia.innerText = res.toFixed(2) + "km";
                    })
                    .catch((e) => {
                         console.log(e);
                    });
          }
     }

     localDesde.addEventListener('change', calcularDistancia);
     localHasta.addEventListener('change', calcularDistancia);
}