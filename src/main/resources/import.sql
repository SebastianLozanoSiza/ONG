INSERT INTO productos (nombre, tipo) VALUES ('Manzana', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Plátano', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Leche', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Pan', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Pollo', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Arroz', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Salmón', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Zanahoria', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Papa', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Jugo de naranja', 0);
INSERT INTO productos (nombre, tipo) VALUES ('Medicamento A', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Medicamento B', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Analgésico', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Antibiótico', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Vitamina C', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Antiácido', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Jarabe para la tos', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Medicamento para alergias', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Insulina', 1);
INSERT INTO productos (nombre, tipo) VALUES ('Antidepresivo', 1);


INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('6789012345', 'Juan', 'Hernández', 'juan.hernandez@example.com', '6789012345', 'to_be_encoded', 0);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('7890123456', 'Luisa', 'Gómez', 'luisa.gomez@example.com', '7890123456', 'to_be_encoded', 0);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('8901234567', 'Miguel', 'Jiménez', 'miguel.jimenez@example.com', '8901234567', 'to_be_encoded', 2);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('9012345678', 'Sofía', 'Torres', 'sofia.torres@example.com', '9012345678', 'to_be_encoded', 2);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('0123456789', 'Diego', 'Ruiz', 'diego.ruiz@example.com', '0123456789', 'to_be_encoded', 2);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('1234567890', 'Ana', 'González', 'ana.gonzalez@example.com', '1234567890', 'to_be_encoded', 1);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('2345678901', 'Pedro', 'Rodríguez', 'pedro.rodriguez@example.com', '2345678901', 'to_be_encoded', 1);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('3456789012', 'María', 'López', 'maria.lopez@example.com', '3456789012', 'to_be_encoded', 1);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('4567890123', 'Carlos', 'Martínez', 'carlos.martinez@example.com', '4567890123', 'to_be_encoded', 1);
INSERT INTO personas (cedula, nombre, apellido, email, telefono, pwd, rol) VALUES ('5678901234', 'Laura', 'Pérez', 'laura.perez@example.com', '5678901234', 'to_be_encoded', 1);
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('123456789', 'Juan', 'Perez', 'juan.perez@example.com', '123456789');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('234567890', 'Ana', 'Gomez', 'ana.gomez@example.com', '234567890');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('345678901', 'Pedro', 'Lopez', 'pedro.lopez@example.com', '345678901');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('456789012', 'Maria', 'Rodriguez', 'maria.rodriguez@example.com', '456789012');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('567890123', 'Luis', 'Martinez', 'luis.martinez@example.com', '567890123');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('678901234', 'Laura', 'Hernandez', 'laura.hernandez@example.com', '678901234');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('789012345', 'Carlos', 'Gonzalez', 'carlos.gonzalez@example.com', '789012345');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('890123456', 'Isabel', 'Perez', 'isabel.perez@example.com', '890123456');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('901234567', 'Fernando', 'Sanchez', 'fernando.sanchez@example.com', '901234567');
INSERT INTO personas (cedula, nombre, apellido, email, telefono) VALUES ('012345678', 'Elena', 'Ramirez', 'elena.ramirez@example.com', '012345678');


INSERT INTO ciudades (nombre) VALUES ('Bogotá');
INSERT INTO ciudades (nombre) VALUES ('Medellín');
INSERT INTO ciudades (nombre) VALUES ('Cali');
INSERT INTO ciudades (nombre) VALUES ('Barranquilla');
INSERT INTO ciudades (nombre) VALUES ('Cartagena');


INSERT INTO refugios (nombre, id_ciudad) VALUES ('Refugio de Animales San Francisco', 1);
INSERT INTO refugios (nombre, id_ciudad) VALUES ('Centro de Atención a la Mujer Maltratada', 2);
INSERT INTO refugios (nombre, id_ciudad) VALUES ('Casa del Migrante', 3);
INSERT INTO refugios (nombre, id_ciudad) VALUES ('Hogar de Ancianos San José', 4);
INSERT INTO refugios (nombre, id_ciudad) VALUES ('Centro de Rehabilitación Integral', 5);


INSERT INTO sedes (nombre_sede, direccion, id_ciudad, id_director) VALUES ('Sede Central', 'Calle Principal 123', 1, 6);
INSERT INTO sedes (nombre_sede, direccion, id_ciudad, id_director) VALUES ('Sede Norte', 'Avenida Norte 456', 2, 7);
INSERT INTO sedes (nombre_sede, direccion, id_ciudad, id_director) VALUES ('Sede Sur', 'Carrera Sur 789', 3, 8);
INSERT INTO sedes (nombre_sede, direccion, id_ciudad, id_director) VALUES ('Sede Este', 'Calle Este 101', 4, 9);
INSERT INTO sedes (nombre_sede, direccion, id_ciudad, id_director) VALUES ('Sede Oeste', 'Avenida Oeste 111', 5, 10);


INSERT INTO tipo_cuota (nombre, precio) VALUES ('minima', 10);
INSERT INTO tipo_cuota (nombre, precio) VALUES ('media', 20);
INSERT INTO tipo_cuota (nombre, precio) VALUES ('maxima', 30);


INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_ADMIN', 1);      
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_ADMIN', 2); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_AUXILIAR', 3); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_AUXILIAR', 4); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_AUXILIAR', 5); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_DIRECTOR', 6); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_DIRECTOR', 7); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_DIRECTOR', 8); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_DIRECTOR', 9); 
INSERT INTO roles (nombre_rol, id_persona) VALUES ('ROLE_DIRECTOR', 10); 



INSERT INTO socios (fecha_pago, cuenta_bancaria, id_tipo_cuota, id_persona, id_sede) VALUES ('2024-03-12', 'Nequi', 1, 11, 1);
INSERT INTO socios (fecha_pago, cuenta_bancaria, id_tipo_cuota, id_persona, id_sede) VALUES ('2024-03-12', 'Nequi', 2, 12, 2);
INSERT INTO socios (fecha_pago, cuenta_bancaria, id_tipo_cuota, id_persona, id_sede) VALUES ('2024-03-12', 'Nequi', 3, 13, 3);
INSERT INTO socios (fecha_pago, cuenta_bancaria, id_tipo_cuota, id_persona, id_sede) VALUES ('2024-03-12', 'Nequi', 1, 14, 4);
INSERT INTO socios (fecha_pago, cuenta_bancaria, id_tipo_cuota, id_persona, id_sede) VALUES ('2024-03-12', 'Nequi', 2, 15, 5);


INSERT INTO voluntarios (id_persona, id_sede) VALUES (16, 1);
INSERT INTO voluntarios (id_persona, id_sede) VALUES (17, 2);
INSERT INTO voluntarios (id_persona, id_sede) VALUES (18, 3);
INSERT INTO voluntarios (id_persona, id_sede) VALUES (19, 4);
INSERT INTO voluntarios (id_persona, id_sede) VALUES (20, 5);


INSERT INTO ocupaciones (nombre) VALUES ('Doctor');
INSERT INTO ocupaciones (nombre) VALUES ('Enfermero');
INSERT INTO ocupaciones (nombre) VALUES ('Administrativo');
INSERT INTO ocupaciones (nombre) VALUES ('Voluntario de Apoyo');
INSERT INTO ocupaciones (nombre) VALUES ('Logístico');


INSERT INTO voluntarios_h (disponibilidad, num_trabajos, id_ocupacion, id_voluntario) VALUES (true, 10, 1, 1);
INSERT INTO voluntarios_h (disponibilidad, num_trabajos, id_ocupacion, id_voluntario) VALUES (true, 8, 2, 2);
INSERT INTO voluntarios_h (disponibilidad, num_trabajos, id_ocupacion, id_voluntario) VALUES (true, 5, 3, 3);
INSERT INTO voluntarios_h (disponibilidad, num_trabajos, id_ocupacion, id_voluntario) VALUES (false, 0, 4, 4);
INSERT INTO voluntarios_h (disponibilidad, num_trabajos, id_ocupacion, id_voluntario) VALUES (true, 12, 5, 5);


INSERT INTO envios (fecha_salida, id_refugio) VALUES ('2024-04-10', 1);
INSERT INTO envios (fecha_salida, id_refugio) VALUES ('2024-04-11', 2);
INSERT INTO envios (fecha_salida, id_refugio) VALUES ('2024-04-12', 3);
INSERT INTO envios (fecha_salida, id_refugio) VALUES ('2024-04-13', 4);
INSERT INTO envios (fecha_salida, id_refugio) VALUES ('2024-04-14', 5);


INSERT INTO envio_sede (id_envio, id_sede) VALUES (1, 1);
INSERT INTO envio_sede (id_envio, id_sede) VALUES (2, 2);
INSERT INTO envio_sede (id_envio, id_sede) VALUES (3, 3);
INSERT INTO envio_sede (id_envio, id_sede) VALUES (4, 4);
INSERT INTO envio_sede (id_envio, id_sede) VALUES (5, 5);


INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (1, 1, 'Comida', 100.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (2, 2, 'Comida', 150.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (3, 3, 'Comida', 200.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (4, 4, 'Comida', 250.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (5, 5, 'Comida', 300.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (1, 6, 'Medicamento', 50.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (2, 7, 'Medicamento', 75.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (3, 8, 'Medicamento', 100.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (4, 9, 'Medicamento', 125.0);
INSERT INTO ayudas_materiales (id_envio, id_producto, tipo_material, cantidad) VALUES (5, 10, 'Medicamento', 150.0);


INSERT INTO ayudas_humanitarias (id_envio, id_ocupacion, cantidad) VALUES (1, 1, 10);
INSERT INTO ayudas_humanitarias (id_envio, id_ocupacion, cantidad) VALUES (2, 2, 15);
INSERT INTO ayudas_humanitarias (id_envio, id_ocupacion, cantidad) VALUES (3, 3, 20);
INSERT INTO ayudas_humanitarias (id_envio, id_ocupacion, cantidad) VALUES (4, 4, 25);
INSERT INTO ayudas_humanitarias (id_envio, id_ocupacion, cantidad) VALUES (5, 5, 30);


INSERT INTO ayuda_voluntario (id_ayuda, id_voluntarioh) VALUES (1, 1);
INSERT INTO ayuda_voluntario (id_ayuda, id_voluntarioh) VALUES (2, 2);
INSERT INTO ayuda_voluntario (id_ayuda, id_voluntarioh) VALUES (3, 3);
INSERT INTO ayuda_voluntario (id_ayuda, id_voluntarioh) VALUES (4, 4);
INSERT INTO ayuda_voluntario (id_ayuda, id_voluntarioh) VALUES (5, 5);



