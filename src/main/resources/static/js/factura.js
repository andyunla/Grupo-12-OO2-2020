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
     var element = document.getElementById('container-container');
     var opt = {
          margin: 1,
          filename: 'factura.pdf',
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
     html2pdf().from(element).set(opt).save();
}