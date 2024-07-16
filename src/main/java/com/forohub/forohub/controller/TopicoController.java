package com.foroHub.foroHub.controller;

import com.foroHub.foroHub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DtsRespuestaTopico> registrarTopico(@RequestBody @Valid DtsRegisterTopico dtsRegisterTopico, UriComponentsBuilder uriComponentsBuilder){

        return  topicoService.registrar(dtsRegisterTopico, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page> listadoTopicos(@PageableDefault(size = 10)Pageable paginacion){

        return topicoService.listarTopicos(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtsListTopicoAndRespuestas> listarDetalleTopico(@PathVariable Long id){

        return topicoService.listarDetalleTopicos(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtsRespuestaTopico> actualizaTopico(@RequestBody @Valid DtsUpdateTopico dtsUpdateTopico, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        return topicoService.actualizarTopico(dtsUpdateTopico, id, uriComponentsBuilder);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){

        return topicoService.eliminarTopico(id);
    }

}


