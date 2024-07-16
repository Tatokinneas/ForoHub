package com.forohub.forohub.domain.perfil;

import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

public enum Perfil {
    ADMINISTRADOR("Administrador"),
    MODERADOR("Moderador"),
    INSTRUCTOR("Instructor"),
    AYUDANTE("Ayudante"),
    ESTUDIANTE("Estudiante");

    private String perfil;

    Perfil(String perfil) {
        this.perfil = perfil;
    }

    public static Perfil fromString(String text) {
        for (Perfil perfil : Perfil.values()) {
            if (perfil.perfil.equalsIgnoreCase(text)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Ningún perfil fue encontrado: " + text);
    }


}
