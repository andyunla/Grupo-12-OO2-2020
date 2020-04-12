<div align="center">
  <h1>Sistema de democratizacion de stock</h1>
</div>


### 1ro - Clonar el repositorio del proyecto
```bash
git clone https://github.com/andyunla/sistema-de-democratizacion-de-stock.git
```

### 2do - Realizar configuraciones
```bash
cd sistema-de-democratizacion-de-stock         # Es para ingresar a la carpeta del proyecto descargada
git config user.name "nombre_usuario"          # Esto, al igual que el email hacerlo una vez. Luego no harán falta
git config user.email "tu_direccion@email.com"
```
___Nota:___ Los pasos anteriores son solo al principio. Para clonar el repositorio y configurarlo

<br>
<hr>

### 3ro - Crear una nueva rama o branch basada en master cada vez que se hace un nuevo trabajo
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
git push -u origin NOMBRE-RAMA  # Cambiar NOMBRE-RAMA por el nombre del actual branch. Ej: git push origin agregar-sql
```

<<br><hr>
<div align="center">
  <h2>REALIZAR SIEMPRE UNA ACTUALIZACIÓN DE LOS DATOS</h1>
  <h3>Se debe traer los cambios que están en master ya que es la rama que se actualiza a cada momento.</h3>
</div>

```bash
git pull origin master  # Trae los nuevos cambios que están en master
```
