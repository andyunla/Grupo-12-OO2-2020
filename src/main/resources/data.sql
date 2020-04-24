USE `bd-sistema-de-democratizacion-de-stock`;

-- PERSONAS:
INSERT INTO persona 
	(nombre, apellido, dni, fechaNacimiento)
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
	(idCliente, email, nroCliente)
VALUES
	(1, 'cliente1@email.com', 1),
	(2, 'cliente2@email.com', 2),
	(3, 'cliente3@email.com', 3);
    
-- LOCAL (Los gerentes_idEmpleado se definen luego de cargar empleados)
INSERT INTO local
	(nombreLocal, latitud, longitud, direccion, telefono, gerente_idEmpleado)
VALUES 	
	('Local 1', 100, 100, "Av. Local 1", 41111111, null),
	('Local 2', 100, 200, "Av. Local 2", 42222222, null),
	('Local 3', 100, 250, "Av. Local 3", 43333333, null);
        
-- EMPLEADOS:
INSERT INTO empleado 
	(idEmpleado, legajo, horarioDesde, horarioHasta, sueldoBasico, idLocal, gerente_idLocal)
VALUES
	(4, 1, '08:00:00', '16:00:00', 30000, 1, null),	-- Empleados
	(5, 2, '16:00:00', '20:00:00', 30000, 1, null),
	(6, 3, '08:00:00', '16:00:00', 30000, 2, null),
	(7, 4, '16:00:00', '20:00:00', 30000, 2, null),
	(8, 5, '08:00:00', '16:00:00', 30000, 3, null),
	(9, 6, '16:00:00', '20:00:00', 30000, 3, null),
	(10, 7, '10:00:00', '18:00:00', 80000, 1, 1),	-- Gerentes
	(11, 8, '10:00:00', '18:00:00', 80000, 2, 2),
	(12, 9, '10:00:00', '18:00:00', 80000, 3, 3);

-- Definir gerentes de locales
UPDATE local SET gerente_idEmpleado = 1 WHERE idLocal = 1;
UPDATE local SET gerente_idEmpleado = 2 WHERE idLocal = 2;
UPDATE local SET gerente_idEmpleado = 3 WHERE idLocal = 3;

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
	(cantidadInicial, cantidadActual, fechaIngreso, activo, idProducto, idLocal)
VALUES
	(10, 8, '2020-01-01', 1, 1, 1),	
	(10, 5, '2020-01-01', 1, 2, 1),	
	(5, 5, '2020-01-01', 1, 1, 2),	
	(1, 1, '2020-01-01', 1, 1, 3);	
    
-- PEDIDO STOCK
INSERT INTO pedidoStock
	(cantidad, aceptado, solicitante_idEmpleado, oferente_idEmpleado, idProducto)
VALUES
	( 2, 1, 8, 4, 1),
	( 5, 1, 9, 4, 2);	

-- CHANGO
INSERT INTO chango 
	(idPedidoStock, idLocal)
VALUES 
	(1, 3),	
    (2, 3);	
    
-- ITEM
INSERT INTO item 
	(cantidad, idChango, idProducto)
VALUES
	(2, 1, 1),	
    (5, 2, 2);	
    
-- FACTURA
INSERT INTO factura 
	(fechaFactura, costeTotal, idLocal, idEmpleado, idCliente, idChango)
VALUES
	('2020-01-30', 200, 3, 8, 1, 1),	
    ('2020-01-31', 1000, 3, 9, 2, 2);
