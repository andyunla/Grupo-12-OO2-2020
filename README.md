<div align="center">
  <h1>Sistema de democratizacion de stock</h1>
</div>


### 1ro - Clonar el repositorio del proyecto
```bash
git clone https://github.com/andyunla/Grupo-12-OO2-2020.git
```

### 2do - Realizar configuraciones
```bash
cd sistema-de-democratizacion-de-stock         # Es para ingresar a la carpeta del proyecto descargada
git config user.name "nombre_usuario"          # Esto, al igual que el email, hacerlo una vez.
git config user.email "tu_direccion@email.com"
```
___Nota:___ Los pasos anteriores son solo al principio; luego no harán falta. <br>
Son para clonar el repositorio y configurarlo

<hr>

### 3ro - Crear una nueva rama o branch basada en master cada vez que se hace un nuevo trabajo
```bash
git branch NOMBRE-RAMA    # El nombre del branch tiene que tener relación a los que se hace. Ej: agregar-sql
git checkout NOMBRE-RAMA  # Nos cambiamos a la nueva rama.
```
O la forma más rápida que realiza los 2 pasos a la vez:
```bash
git checkout -b NOMBRE-RAMA  # El nombre del branch tiene que tener relación a los que se hace. Ej: agregar-sql
```
___Nota:___ Todos las modificaciones se están realizando en la nueva rama. Así no trabajamos sobre master directamente. Una vez que la rama se usó no se debe usar más. Crear otra nueva por cada nuevo cambio que se desea agregar.

### 4to - Agregamos los archivos modificados/nuevos
```bash
git add .                   # Agrega todos los archivos modificados. También puede ser: git add --all o git add *
git commit -m "comentario"  # Dar una descripción de los cambios realizados
git status                  # OPCIONAL: es para saber si lo que agregamos anteriormente están listos
```

### 5to - Subimos los cambios
```bash
git push origin NOMBRE-RAMA  # Cambiar NOMBRE-RAMA por el nombre del actual branch. Ej: git push origin agregar-sql
```

### 6to - Los cambios estarán en la sección de _PULL REQUESTS_:
Una vez allí solo queda fusionarla con master.

<div align="center">
	<img src="https://imgur.com/MepGlwJl.png" />	
</div>

<br><hr>
<div align="center">
  <h2>REALIZAR SIEMPRE UNA ACTUALIZACIÓN DE LOS DATOS</h1>
  <h3>Se debe traer los cambios que están en master ya que es la rama que se actualiza a cada momento.</h3>
</div>

___Nota:___ Realizar esto cada vez que tienen que hacer cambios nuevos. Así tienen lo más actualizado y pueden ya modificar.
```bash
git pull origin master  # Trae los nuevos cambios que están en master
```
