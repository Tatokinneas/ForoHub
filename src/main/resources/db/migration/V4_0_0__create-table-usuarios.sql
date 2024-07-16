CREATE TABLE usuarios(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL,
    perfil VARCHAR(100) not null,

    primary key(id)

);