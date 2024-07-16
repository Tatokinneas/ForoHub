package com.foroHub.foroHub.domain.topico.validaciones;

import com.foroHub.foroHub.domain.topico.DtsRegisterTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.foroHub.foroHub.domain.topico.Topico;
import com.foroHub.foroHub.domain.topico.TopicoRepository;

import java.util.List;

@Component
public class Duplicates implements TopicValidators {

    @Autowired
    public TopicoRepository topicoRepository;

    public void validar(DtsRegisterTopico dtsRegisterTopico){

        List<Topico> topicos = topicoRepository.findByTitulo(dtsRegisterTopico.titulo());
        topicos.stream()
                .filter(t -> t.getMensaje().equalsIgnoreCase(dtsRegisterTopico.mensaje()))
                .findFirst()
                .ifPresent(t -> {
                    throw new ValidationException("No se pueden crear tópicos duplicados");
                });

    }

}
