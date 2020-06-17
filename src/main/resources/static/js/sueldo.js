let fechaSeleccionada = document.getElementById("fechaSueldo");
const MAX_YEAR = new Date().getFullYear();
const MIN_YEAR = 2000;

function listarSueldos() {
    if (fechaSeleccionada.value !== "") {
        let fecha = fechaSeleccionada.value; // Del tipo aaaa-mm
        let patt1 = /^(\d{4})\-(\d{1,2})$/; // Patrón
        if(fecha.match(patt1) !== null) { // Si se ingresaron los datos correctamente
            let parts = fecha.split("-");
            if(parts[1] <= 12 && parts[0] <= MAX_YEAR && parts[0] >= MIN_YEAR) {
                let url = host + "/sueldos/";
                fetch(url + "traer/" + fecha)
                    .then(response => response.text())
                    .then(html => {
                        document.querySelector("tbody").innerHTML = html;
                    })
                    .catch((e) => {
                        console.log(e);
                    });
            } else {
                document.querySelector("tbody").innerHTML = "<small>No hay sueldos cargados en la fecha elegida</small>";
            }
        } else {
            document.querySelector("tbody").innerHTML = "<small>ERROR: Debe ingresar una fecha correcta(aaaa-mm)</small>";
        }
    }
}

fechaSeleccionada.addEventListener('change', listarSueldos);

async function abrirRecibo(legajo) {
    if (fechaSeleccionada.value !== "") {
        let fecha = fechaSeleccionada.value; // Del tipo aaaa-mm
        let patt1 = /^(\d{4})\-(\d{1,2})$/; // Patrón
        if(fecha.match(patt1) !== null) { // Si se ingresaron los datos correctamente
            let parts = fecha.split("-");
            if(parts[1] <= 12 && parts[0] <= MAX_YEAR && parts[0] >= MIN_YEAR) {  
                let url = host + "/sueldos/ver-recibo/" + legajo + "/" + fecha;
                window.location.href = url;
            }
        }
    } else {
        let fecha = new Date();
        let fechaString = fecha.getFullYear() + '-' + (fecha.getMonth() + 1);
        let url = host + "/sueldos/ver-recibo/" + legajo + "/" + fechaString;
        window.location.href = url;
    }
}