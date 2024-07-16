package com.foroHub.foroHub.controller;

import com.foroHub.foroHub.domain.respuesta.*;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DtsRespuesta> registrarRespuesta(@RequestBody @Valid DtsRegisterRespuesta dtsRegisterRespuesta, UriComponentsBuilder uriComponentsBuilder){

        return  service.registrarRespuesta(dtsRegisterRespuesta, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtsRespuesta> actualizaRespuesta(@RequestBody @Valid DtsActualizarRespuesta dtsActualizarRespuesta,
                                                           @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        return service.actualizarRespuesta(dtsActualizarRespuesta, id, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page> listadoRespuesta(@PageableDefault(size = 10) Pageable paginacion){

        return service.listarRespuestas(paginacion);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){

        return service.eliminarRespuesta(id);
    }
}
