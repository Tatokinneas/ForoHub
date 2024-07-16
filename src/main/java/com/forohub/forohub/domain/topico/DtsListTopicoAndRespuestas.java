package com.foroHub.foroHub.domain.topico;

import com.foroHub.foroHub.domain.respuesta.DtsListResponseTopico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DtsListTopicoAndRespuestas(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        String estado,
        String curso,
        String autor,
        List<DtsListResponseTopico> respuestas
) {

    public DtsListTopicoAndRespuestas(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion(),
                topico.getStatus().toString(), topico.getCurso().getNombre(), topico.getAutor().getNombre(),
                topico.getRespuestas().stream()
                        .map(r -> new DtsListResponseTopico(r))
                        .collect(Collectors.toList()));
    }


}
