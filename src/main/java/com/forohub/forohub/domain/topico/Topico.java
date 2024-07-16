package com.foroHub.foroHub.domain.topico;

import com.foroHub.foroHub.domain.respuesta.Respuesta;
import com.foroHub.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.foroHub.foroHub.domain.curso.Curso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario autor;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    List<Respuesta> respuestas;

    public Topico(DtsRegisterTopico dtsRegisterTopico) {
        this.titulo = dtsRegisterTopico.titulo();
        this.mensaje = dtsRegisterTopico.mensaje();

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaFormateada = ahora.format(formateador);
        this.fecha_creacion = LocalDateTime.parse(horaFormateada, formateador);

        this.status = Status.PENDIENTE;

    }

    public void actualizarDatos(DtsUpdateTopico dtsUpdateTopico, Curso curso, Usuario autor){
        if (dtsUpdateTopico.titulo() != null) {
            this.titulo = dtsUpdateTopico.titulo();
        }
        if (dtsUpdateTopico.mensaje() != null){
            this.mensaje = dtsUpdateTopico.mensaje();
        }
        if (curso != null){
            this.curso = curso;
        }
        if (autor != null){
            this.autor = autor;
        }
        if (dtsUpdateTopico.estado() != null){
            this.status = Status.fromString(dtsUpdateTopico.estado());
        }
    }
}
