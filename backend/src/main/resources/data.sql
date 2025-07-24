-- Inserts para la entidad Persona
INSERT INTO Persona (nombre, genero, identificacion, direccion, telefono, edad)
VALUES ('Juan Pérez', 'M', '1234567890', 'Calle Principal 123', '0987654321', 35);

INSERT INTO Persona (nombre, genero, identificacion, direccion, telefono, edad)
VALUES ('Lucía Gómez', 'F', '0987612345', 'Av. Libertad 456', '0991234567', 28);

-- Inserts para la entidad Cliente
INSERT INTO Cliente (nombre_usuario, contrasena, estado, id)
VALUES ('juanp', 'juan123', 'activo', 1);

INSERT INTO Cliente (nombre_usuario, contrasena, estado, id)
VALUES ('luciag', 'lucia456', 'inactivo', 2);

-- Inserts para la entidad Cuenta
INSERT INTO Cuenta (numero, tipo, saldo_disponible, estado, cliente_id)
VALUES ('000111222333', 'ahorros', 1200.00, 'activa', 1);

INSERT INTO Cuenta (numero, tipo, saldo_disponible, estado, cliente_id)
VALUES ('999888777666', 'corriente', 500.00, 'suspendida', 2);

-- Inserts para la entidad Movimiento
INSERT INTO Movimiento (tipo, fecha, monto, saldo_inicial, saldo_final, estado, cuenta_id)
VALUES ('credito', '2025-07-20', 500.00, 1000.00, 1500.00, 'completado', 1);

INSERT INTO Movimiento (tipo, fecha, monto, saldo_inicial, saldo_final, estado, cuenta_id)
VALUES ('debito', '2025-07-21', 300.00, 1500.00, 1200.00, 'completado', 1);
