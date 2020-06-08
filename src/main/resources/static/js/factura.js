JsBarcode(".codigo")
     .options({
          format: "CODE128",
          width: 1.5,
          height: 30,
          displayValue: true,
          fontOptions: "bold",
          textAlign: "center",
          textPosition: "bottom",
          textMargin: 4,
          fontSize: 10
     })
     .init();

function descargar() {
     var facturaBody = document.getElementsByClassName('container-container')[0];
     let nombreArchivo = 'factura-' + document.getElementsByClassName("nroFactura")[0].innerText + '.pdf';
     var opt = {
          margin: 1,
          filename: nombreArchivo,
          enableLinks: true,
          image: { type: 'jpeg', quality: 0.99 },
          html2canvas: {
               scale: 2,
               allowTaint: true
          },
          jsPDF: {
               orientation: 'p',
               unit: 'mm',
               format: [220, 311],
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
