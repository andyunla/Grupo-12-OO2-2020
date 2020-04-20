USE `bd-sistema-de-democratizacion-de-stock`;

-- PERSONAS:
INSERT INTO persona VALUES
	(1, 'Carlos', 'Carrizo', 10000001, '1990-01-01'),	-- Clientes (3)
    (2, 'Casimiro', 'Camaño', 10000002, '1990-01-01'),
    (3, 'Cecilia', 'Caballero', 10000003, '1990-01-01'),
	(4, 'Ernesto', 'Espinoza', 10000001, '1990-04-01'),	-- Empleados (6)
    (5, 'Edmundo', 'Escobar', 20000002, '1990-04-02'),
    (6, 'Ernestina', 'Epo', 20000003, '1990-04-03'),
	(7, 'Eriberto', 'Estrada', 20000004, '1990-04-04'),
	(8, 'Eduardo', 'Espejo', 20000005, '1990-04-05'),
	(9, 'Elisa', 'Estrada', 20000006, '1990-04-06'),
	(10, 'Gaston', 'Gimenez', 30000001, '1980-03-01'),	-- Gerentes (3)
	(11, 'Gabriel', 'Gimenez', 30000002, '1980-03-02'),
	(12, 'Graciela', 'Garcia', 30000003, '1980-03-03');

-- CLIENTES:
INSERT INTO cliente VALUES
	(1, 'cliente1@email.com', 1),
	(2, 'cliente2@email.com', 2),
	(3, 'cliente3@email.com', 3);
    
-- LOCAL
INSERT INTO local VALUES 	
	(1, 'Local 1', 100, 100, "Av. Local 1", 41111111, null),
	(2, 'Local 2', 100, 200, "Av. Local 2", 42222222, null),
	(3, 'Local 2', 100, 250, "Av. Local 3", 43333333, null);
    
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
	(1, 'producto uno', 'producto uno descripcion', 100, 1),
	(2, 'producto dos', 'producto dos descripcion', 200, 2),
	(3, 'producto tres', 'producto tres descripcion', 300, 3),
	(4, 'producto cuatro', 'producto cuatro descripcion', 400, 1);
    
-- LOTE
INSERT INTO lote VALUES
	(1, 10, 10, '2020-01-01', true, 1, 1),	-- Local 1
	(2, 10, 10, '2020-01-01', true, 2, 1),
	(3, 10, 10, '2020-01-01', true, 3, 1),
	(4, 5, 5, '2020-01-01', true, 1, 2),	-- Local 2
	(5, 5, 5, '2020-01-01', true, 2, 2),
	(6, 5, 5, '2020-01-01', true, 3, 2),
	(7, 0, 0, '2020-01-01', true, 1, 3),	-- Local 3
	(8, 2, 2, '2020-01-01', true, 2, 3),
	(9, 2, 2, '2020-01-01', true, 3, 3);

-- PEDIDO STOCK
INSERT INTO pedidoStock VALUES
	(1, 2, true, 8, 4, 1),	-- Empleado id 8 del local 3 pide al empleado id:4 del local 1 2 unidades de producto 1: aceptado
	(2, 10, false, 9, 4, 2); -- Empleado id 9 del local 3 pide al empleado id:4 del local 1 10 unidades de producto 2: rechazada

-- CHANGO
INSERT INTO chango VALUES 
	(1, 1, 3),	-- Chango del local 3, pedido id 1
    (2, 2, 3);

-- ITEM
INSERT INTO item VALUES
	(1, 2, 1, 1),	-- Item del chango 1, 2 productos de id:1
    (2, 10, 2, 2);	-- Item del chango 2, 10 productos de id: 2

-- FACTURA
INSERT INTO factura VALUES
	(1, '2020-01-30', 200, 3, 8, 1, 1);	-- Factura del chango 1 (con 2 productos de id 1), cliente 1, empleado 8, sucursal 3

-- Prueba
SELECT 
	f.idFactura as Factura,
    f.fechaFactura as fecha,
    f.costeTotal,
    p.nombre as comprador,
    p2.nombre as vendedor,
    l.nombreLocal as local
FROM factura f 
INNER JOIN persona p ON f.idCliente = p.idPersona
INNER JOIN persona p2 ON f.idEmpleado = p2.idPersona
INNER JOIN local l ON f.idLocal = l.idLocal;
