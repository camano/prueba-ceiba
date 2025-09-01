CREATE DATABASE BTG;

show BTG;
-- Tabla cliente
CREATE TABLE cliente (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         apellidos VARCHAR(100) NOT NULL,
                         ciudad VARCHAR(100) NOT NULL
);

-- Tabla producto
CREATE TABLE producto (
                          id SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          tipoProducto VARCHAR(100) NOT NULL
);

-- Tabla sucursal
CREATE TABLE sucursal (
                          id SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          ciudad VARCHAR(100) NOT NULL
);

-- Tabla inscripcion (relación cliente-producto)
CREATE TABLE inscripcion (
                             idProducto INT NOT NULL,
                             idCliente INT NOT NULL,
                             PRIMARY KEY (idProducto, idCliente),
                             FOREIGN KEY (idProducto) REFERENCES producto(id),
                             FOREIGN KEY (idCliente) REFERENCES cliente(id)
);

-- Tabla disponibilidad (relación sucursal-producto)
CREATE TABLE disponibilidad (
                                idSucursal INT NOT NULL,
                                idProducto INT NOT NULL,
                                PRIMARY KEY (idSucursal, idProducto),
                                FOREIGN KEY (idSucursal) REFERENCES sucursal(id),
                                FOREIGN KEY (idProducto) REFERENCES producto(id)
);

-- Tabla visitan (relación cliente-sucursal con fecha)
CREATE TABLE visitan (
                         idSucursal INT NOT NULL,
                         idCliente INT NOT NULL,
                         fechaVisita DATE NOT NULL,
                         PRIMARY KEY (idSucursal, idCliente, fechaVisita),
                         FOREIGN KEY (idSucursal) REFERENCES sucursal(id),
                         FOREIGN KEY (idCliente) REFERENCES cliente(id)
);
