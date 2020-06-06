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