package com.foroHub.foroHub.domain.topico;


public record DtsUpdateTopico(
        String titulo,
        String mensaje,
        String nombreCurso,
        Long usuario_id,
        String estado
) {
}
