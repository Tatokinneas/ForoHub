package com.foroHub.foroHub.domain.respuesta;

import java.time.LocalDateTime;

public record DtsListResponseTopico(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        String autor,
        Boolean solucion
) {
    public DtsListResponseTopico(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha_creacion(),
                respuesta.getAutor().getNombre(), respuesta.getSolucion());
    }
}
