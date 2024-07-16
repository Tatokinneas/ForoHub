package com.foroHub.foroHub.domain.respuesta;


import com.foroHub.foroHub.domain.topico.DtsListTopicos;

import java.time.LocalDateTime;

public record DtsListRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        String autor,
        Boolean solucion,
        DtsListTopicos topico
) {
    public DtsListRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha_creacion(),
                respuesta.getAutor().getNombre(), respuesta.getSolucion(),
                new DtsListTopicos(respuesta.getTopico()));
    }
}
