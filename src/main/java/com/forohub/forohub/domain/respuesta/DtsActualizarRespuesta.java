package com.foroHub.foroHub.domain.respuesta;

public record DtsActualizarRespuesta(
        String mensaje,
        Long topico_id,
        Long usuario_id,
        Boolean solucion
) {
}
