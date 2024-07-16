CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaCreacion DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    curso_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    respuestas_id BIGINT NOT NULL,
    PRIMARY KEY(id)
);

