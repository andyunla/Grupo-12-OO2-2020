window.onload = () => {
	let buttonAdd = document.getElementById("buttonAgregar");
	let isFormOpen = false;
	//Listener to add
	buttonAdd.addEventListener('click', () => {
		isFormOpen = !isFormOpen;
		buttonAdd.innerText = (isFormOpen) ? "Cerrar" : "Agregar";
	});
}

