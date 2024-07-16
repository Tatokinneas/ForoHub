package com.foroHub.foroHub.domain.respuesta;

import com.foroHub.foroHub.domain.topico.DtsListTopicos;

import java.time.LocalDateTime;

public record DtsRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        DtsListTopicos topico,
        String autor,
        Boolean solucion
) {
}
