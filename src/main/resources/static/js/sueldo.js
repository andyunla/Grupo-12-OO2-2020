let fechaSeleccionada = document.getElementById("fechaSueldo");

function listarMasCercanos() {
    if (fechaSeleccionada.value !== "") {
        let fecha = fechaSeleccionada.value; // Del tipo aaaa-mm
        let patt1 = /^(\d{1,4})\-(\d{1,2})$/; // Patrón
        if(fecha.match(patt1) !== null) { // Si se ingresaron los datos correctamente
            let url = host + "/sueldos/";
            fetch(url + "traer/" + fecha)
                .then(response => response.text())
                .then(html => {
                    document.querySelector("tbody").innerHTML = html;
                })
                .catch((e) => {
                    console.log(e);
                });
        }
    }
}

fechaSeleccionada.addEventListener('change', listarMasCercanos);
