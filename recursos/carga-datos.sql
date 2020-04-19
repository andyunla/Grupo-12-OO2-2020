USE `bd-sistema-de-democratizacion-de-stock`;

-- PERSONAS:
INSERT INTO persona VALUES
	(default, 'Carlos', 'Carrizo', 10000001, '1990-01-01'),	-- Clientes (3)
    (default, 'Casimiro', 'Camaño', 10000002, '1990-01-01'),
    (default, 'Cecilia', 'Caballero', 10000003, '1990-01-01'),
	(default, 'Ernesto', 'Espinoza', 10000001, '1990-04-01'),	-- Empleados (6)
    (default, 'Edmundo', 'Escobar', 20000002, '1990-04-02'),
    (default, 'Ernestina', 'Epo', 20000003, '1990-04-03'),
	(default, 'Eriberto', 'Estrada', 20000004, '1990-04-04'),
	(default, 'Eduardo', 'Espejo', 20000005, '1990-04-05'),
	(default, 'Elisa', 'Estrada', 20000006, '1990-04-06'),
	(default, 'Gaston', 'Gimenez', 30000001, '1980-03-01'),	-- Gerentes (3)
	(default, 'Gabriel', 'Gimenez', 30000002, '1980-03-02'),
	(default, 'Graciela', 'Garcia', 30000003, '1980-03-03');

-- CLIENTES:
INSERT INTO cliente VALUES
	(1, 'cliente1@email.com', 1),
	(2, 'cliente2@email.com', 2),
	(3, 'cliente3@email.com', 3);
    
-- LOCAL
INSERT INTO local VALUES 	
	(default, 'Local 1', 100, 100, "Av. Local 1", 41111111, null),
	(default, 'Local 2', 100, 200, "Av. Local 2", 42222222, null),
	(default, 'Local 3', 100, 250, "Av. Local 3", 43333333, null);
    
-- EMPLEADOS:
INSERT INTO empleado VALUES
	(4, 1, '08:00:00', '16:00:00', 30000, 1),	-- Empleados
	(5, 2, '16:00:00', '20:00:00', 30000, 1),
	(6, 3, '08:00:00', '16:00:00', 30000, 2),
	(7, 4, '16:00:00', '20:00:00', 30000, 2),
	(8, 5, '08:00:00', '16:00:00', 30000, 3),
	(9, 6, '16:00:00', '20:00:00', 30000, 3),
	(10, 7, '10:00:00', '18:00:00', 80000, 1),	-- Gerentes
	(11, 8, '10:00:00', '18:00:00', 80000, 2),
	(12, 9, '10:00:00', '18:00:00', 80000, 3);

-- Definir gerentes de locales
UPDATE local SET gerente_idEmpleado = 10 WHERE idLocal = 1;
UPDATE local SET gerente_idEmpleado = 11 WHERE idLocal = 2;
UPDATE local SET gerente_idEmpleado = 12 WHERE idLocal = 3;

-- PRODUCTO
INSERT INTO producto VALUES
	(default, 'producto uno', 'producto uno descripcion', 100, 1),	-- precio 100, talle 1
	(default, 'producto dos', 'producto dos descripcion', 200, 2),
	(default, 'producto tres', 'producto tres descripcion', 300, 3),
	(default, 'producto cuatro', 'producto cuatro descripcion', 400, 1);
    
-- LOTE
INSERT INTO lote VALUES
	(default, 10, 8, '2020-01-01', 1, 1, 1),	
	(default, 10, 5, '2020-01-01', 1, 2, 1),	-- Local 1, producto 2
	(default, 5, 5, '2020-01-01', 1, 1, 2),	
	(default, 1, 1, '2020-01-01', 1, 1, 3);	
    
-- PEDIDO STOCK
INSERT INTO pedidoStock VALUES
	(default, 2, 1, 8, 4, 1),	-- Empleado id 8 del local 3 pide al empleado id:4 del local 1 2 unidades de producto 1: aceptado
	(default, 5, 1, 9, 4, 2);	-- Empleado id 9 (del local 3) pide al empleado id 4 (del local 1) 5 unidades de producto 2: aceptado

-- CHANGO
INSERT INTO chango VALUES 
	(default, 1, 3),	-- Chango del local 3, pedido id 1
    (default, 2, 3);	
    
-- ITEM
INSERT INTO item VALUES
	(default, 2, 1, 1),	-- Item del chango 1: 2 productos de id 1
    (default, 5, 2, 2);	-- Item del chango 2: 5 productos de id 2
    
-- FACTURA
INSERT INTO factura VALUES
	(default, '2020-01-30', 200, 3, 8, 1, 1),	-- Factura de $200, del local 3, empleado 8, chango 1, cliente 1
    (default, '2020-01-31', 1000, 3, 9, 2, 2);