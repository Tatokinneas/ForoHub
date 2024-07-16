package com.foroHub.foroHub.domain.respuesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.foroHub.foroHub.domain.topico.Status;
import com.foroHub.foroHub.domain.topico.Topico;
import com.foroHub.foroHub.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario autor;
    private Boolean solucion;

    public Respuesta(DtsRegisterRespuesta dtsRegisterRespuesta, Topico topico, Usuario autor) {
        this.mensaje = dtsRegisterRespuesta.mensaje();

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaFormateada = ahora.format(formateador);
        this.fecha_creacion = LocalDateTime.parse(horaFormateada, formateador);

        this.topico = topico;
        this.autor = autor;
        this.solucion = false;
    }

    public void actualizarDatos(DtsActualizarRespuesta dtsActualizarRespuesta, Topico topico, Usuario autor) {
        if (dtsActualizarRespuesta.mensaje() != null){
            this.mensaje = dtsActualizarRespuesta.mensaje();
        }
        if (topico != null){
            this.topico = topico;
        }
        if (autor != null){
            this.autor = autor;
        }
        if (dtsActualizarRespuesta.solucion() != null){
            if (dtsActualizarRespuesta.solucion()){
                this.solucion = true;
                this.topico.setStatus(Status.POSTEADO);
            }else{
                this.solucion = false;
            }
        }
    }
}
