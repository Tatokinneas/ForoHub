package com.forohub.forohub.domain.usuario;

import com.forohub.forohub.domain.perfil.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegisterUsuario(
        @NotBlank
        String nombre,
        @Email
        @NotNull
        String email,
        @NotBlank
        String contrasena,
        @NotBlank
        Perfil perfiles) {
        public DtoRegisterUsuario(String nombre, String email, String contrasena, Perfil perfiles) {
                this.nombre = nombre;
                this.email = email;
                this.contrasena = contrasena;
                this.perfiles = perfiles;
        }
}
