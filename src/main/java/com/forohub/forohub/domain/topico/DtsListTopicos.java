package com.foroHub.foroHub.domain.topico;


import java.time.LocalDateTime;

public record DtsListTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        String estado,
        String curso,
        String autor
) {

    public DtsListTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion(),
                topico.getStatus().toString(), topico.getCurso().getNombre(), topico.getAutor().getNombre());
    }


}
