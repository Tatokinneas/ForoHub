package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.respuesta.Respuesta;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    private Long id;
    private String titulo;
    private String mensaje;
    private Date fechaCreacion;
    private String status;
    @ManyToOne
    private List<Usuario> autor;
    @OneToMany
    private List<Curso> cursos;
    @ManyToMany
    private Respuesta respuestas;
}
