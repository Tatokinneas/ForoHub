package com.forohub.forohub.domain.usuario;

public record DtoResponseUsuario(Long id,
                                 String nombre,
                                 String email,
                                 String perfil) {
    public DtoResponseUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPerfiles().toString());
    }
}
