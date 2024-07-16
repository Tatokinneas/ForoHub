ALTER TABLE topicos
ADD CONSTRAINT fk_topico_curso FOREIGN KEY(curso_id) REFERENCES cursos(id),
ADD CONSTRAINT fk_topico_usuario FOREIGN KEY(usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE;

ALTER TABLE respuestas
ADD CONSTRAINT fk_respuesta_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
ADD CONSTRAINT fk_respuesta_topico_id FOREIGN KEY(topico_id) REFERENCES topicos(id) ON DELETE CASCADE;
