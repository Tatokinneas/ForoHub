package com.forohub.forohub.controller;


import com.forohub.forohub.domain.usuario.DtoRegisterUsuario;
import com.forohub.forohub.domain.usuario.DtoResponseUsuario;
import com.forohub.forohub.domain.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoResponseUsuario> registrarUsuario(@RequestBody @Valid DtoRegisterUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        return service.registerUsuario(datosRegistroUsuario, uriComponentsBuilder);
    }
}
