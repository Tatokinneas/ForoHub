package com.foroHub.foroHub.domain.usuario;


public record DtsUpdateUsuario(
        String nombre,
        String email,
        String clave,
        String perfil
) {
}
