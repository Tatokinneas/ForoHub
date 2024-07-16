package com.foroHub.foroHub.controller;

import com.foroHub.foroHub.domain.usuario.DtsUpdateUsuario;
import com.foroHub.foroHub.domain.usuario.DtsRegisterUsuario;
import com.foroHub.foroHub.domain.usuario.DtsResponseUsuario;
import com.foroHub.foroHub.domain.usuario.UsuarioService;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DtsResponseUsuario> registrarUsuario(@RequestBody @Valid DtsRegisterUsuario dtsRegisterUsuario, UriComponentsBuilder uriComponentsBuilder){
        return usuarioService.registarUsuario(dtsRegisterUsuario, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtsResponseUsuario> actualizarUsuario(@RequestBody DtsUpdateUsuario dtsUpdateUsuario, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){
        return usuarioService.actualizarUsuario(dtsUpdateUsuario, id, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion){

        return usuarioService.listarUsuarios(paginacion);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){

        return usuarioService.eliminarUsuario(id);
    }

}
