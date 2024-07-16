package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.respuesta.Respuesta;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotBlank
    private Date fechaCreacion;
    @NotBlank
    private String status;
    @ManyToOne
    private Usuario autor;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Curso> cursos;
    @ManyToOne
    private Respuesta respuesta;

}
