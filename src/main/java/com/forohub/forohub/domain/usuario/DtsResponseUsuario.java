package com.foroHub.foroHub.domain.usuario;


public record DtsResponseUsuario(
        Long id,
        String nombre,
        String email,
        String perfil
) {
    public DtsResponseUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPerfil().toString());
    }
}
