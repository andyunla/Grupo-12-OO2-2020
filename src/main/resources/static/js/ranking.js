const host = "http://localhost:8080";

window.onload = () => {
    let fecha1Select = document.getElementById("fecha1Ranking");
    let fecha2Select = document.getElementById("fecha2Ranking");

    function actualizarRanking() {
        if (fecha1Select.value != "" && fecha2Select.value != "") {
            var fecha1 = new Date(fecha1Select.value);
            var fecha2 = new Date(fecha2Select.value);
            if(fecha1 <= fecha2) {
                let url = host + "/reporte/" + fecha1Select.value + "/" + fecha2Select.value;
                fetch(url)
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

    fecha1Select.addEventListener('change', actualizarRanking);
    fecha2Select.addEventListener('change', actualizarRanking);
}
