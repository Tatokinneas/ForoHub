CREATE TABLE respuestas(
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion VARCHAR(200) NOT NULL,
    usuario_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    PRIMARY KEY(id)
);