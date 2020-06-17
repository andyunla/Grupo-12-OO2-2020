
window.onload = () => {
     var fadeTarget = document.getElementById("alertChangoEliminado");
     if(fadeTarget !=null) {
          fadeOutEffect(fadeTarget);
     }
}

function fadeOutEffect(fadeTarget) {
     let escala=0.002;
     var fadeEffect=setInterval(function () {
          if (!fadeTarget.style.opacity) {
               fadeTarget.style.opacity=1;
          }
          if (fadeTarget.style.opacity > 0) {
               fadeTarget.style.opacity -=escala;
               if(fadeTarget.style.opacity < 0.9) {
                    escala=0.07;
               }
          }
          else {
               clearInterval(fadeEffect);
          }
     }
     , 100);
}