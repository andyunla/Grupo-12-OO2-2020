USE `Grupo-12-BDD-OO2-2020`;

-- PERSONAS:
INSERT INTO persona 
	(nombre, apellido, dni, fecha_nacimiento)
VALUES
	('Carlos', 'Carrizo', 10000001, '1990-01-01'),	-- Clientes (3)
	('Casimiro', 'Camaño', 10000002, '1990-01-01'),
	('Cecilia', 'Caballero', 10000003, '1990-01-01'),
	('Ernesto', 'Espinoza', 10000001, '1990-04-01'),	-- Empleados (6)
	('Edmundo', 'Escobar', 20000002, '1990-04-02'),
	('Ernestina', 'Epo', 20000003, '1990-04-03'),
	('Eriberto', 'Estrada', 20000004, '1990-04-04'),
	('Eduardo', 'Espejo', 20000005, '1990-04-05'),
	('Elisa', 'Estrada', 20000006, '1990-04-06'),
	('Gaston', 'Gimenez', 30000001, '1980-03-01'),	-- Gerentes (3)
	('Gabriel', 'Gimenez', 30000002, '1980-03-02'),
	('Graciela', 'Garcia', 30000003, '1980-03-03');

-- CLIENTES:
INSERT INTO cliente 
	(id_persona, email)
VALUES
	(1, 'cliente1@email.com'),
	(2, 'cliente2@email.com'),
	(3, 'cliente3@email.com');

-- LOCAL (Los gerente_legajo se definen luego de cargar empleados)
INSERT INTO locales
	(nombre_local, latitud, longitud, direccion, telefono, gerente_legajo)
VALUES 	
	('Local 1', 100, 100, "Av. Local 1", 41111111, 7),
	('Local 2', 100, 200, "Av. Local 2", 42222222, 8),
	('Local 3', 100, 250, "Av. Local 3", 43333333, 9);

-- EMPLEADOS:
INSERT INTO empleado
	(id_persona, horario_desde, horario_hasta, sueldo_basico, id_local, tipo_empleado)
VALUES
	(4, '08:00:00', '16:00:00', 30000, 1, 0),	-- Empleados
	(5, '16:00:00', '20:00:00', 30000, 1, 0),
	(6, '08:00:00', '16:00:00', 30000, 2, 0),
	(7, '16:00:00', '20:00:00', 30000, 2, 0),
	(8, '08:00:00', '16:00:00', 30000, 3, 0),
	(9, '16:00:00', '20:00:00', 30000, 3, 0),
	(10, '10:00:00', '18:00:00', 80000, 1, 1),	-- Gerentes
	(11, '10:00:00', '18:00:00', 80000, 2, 1),
	(12, '10:00:00', '18:00:00', 80000, 3, 1);

-- PRODUCTO
INSERT INTO producto
	(nombre, descripcion, precio, talle)
VALUES
	('producto uno', 'producto uno descripcion', 100, 1),	
	('producto dos', 'producto dos descripcion', 200, 2),
	('producto tres', 'producto tres descripcion', 300, 3),
	('producto cuatro', 'producto cuatro descripcion', 400, 1);
	
-- LOTE
INSERT INTO lote 
	(cantidad_inicial, cantidad_actual, fecha_ingreso, activo, id_producto, id_local)
VALUES
	(10, 8, '2020-01-01', 1, 1, 1),	
	(10, 5, '2020-01-01', 1, 2, 1),	
	(5, 5, '2020-01-01', 1, 1, 2),	
	(1, 1, '2020-01-01', 1, 1, 3);	
	
-- PEDIDO STOCK
INSERT INTO pedido_stock
	(cantidad, aceptado, solicitante_legajo, oferente_legajo, id_producto)
VALUES
	( 2, 1, 8, 4, 1),
	( 5, 1, 9, 4, 2);	

-- CHANGO
INSERT INTO chango 
	(id_pedido_stock, id_local)
VALUES 
	(1, 3),	
	(2, 3);	
	
-- ITEM
INSERT INTO item 
	(cantidad, id_chango, id_producto)
VALUES
	(2, 1, 1),	
	(5, 2, 2);	
	
-- FACTURA
INSERT INTO factura 
	(fecha_factura, coste_total, id_local, empleado_legajo, nro_cliente, id_chango)
VALUES
	('2020-01-30', 200, 3, 8, 1, 1),
	('2020-01-31', 1000, 3, 9, 2, 2);
