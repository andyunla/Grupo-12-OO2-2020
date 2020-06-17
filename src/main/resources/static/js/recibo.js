function descargar() {
     var facturaBody = document.getElementById('contenedor');
     let nombreArchivo = 'recibo-' + document.getElementById("fecha").innerText
          + '-' + document.getElementById("legajo").innerHTML + '.pdf';
     var opt = {
          margin: 0,
          filename: nombreArchivo,
          enableLinks: true,
          image: { type: 'jpeg', quality: 0.99 },
          html2canvas: {
               scale: 3,
               allowTaint: true
          },
          jsPDF: {
               orientation: 'p',
               unit: 'mm',
               format: [210, 297],
               putOnlyUsedFonts: true
          }
     };
     html2pdf().from(facturaBody).set(opt).save();
}

window.onload = () => {
     // Una vez cargada la pagina remueve el spinner y muestra la factura
     document.getElementsByTagName("main")[0].classList.remove("invisible");
     let spinnerContainer = document.getElementById("spinnerContainer");
     spinnerContainer.parentElement.removeChild(spinnerContainer);
}