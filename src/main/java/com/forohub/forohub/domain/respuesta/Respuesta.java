package com.forohub.forohub.domain.respuesta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.usuario.Usuario;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String mensaje;
    @NotBlank
    @OneToMany(mappedBy = "respuesta",cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Topico> topico;
    @NotBlank
    private Date fechaCreación;
    @NotBlank

    private Usuario autor;
    @NotBlank
    private String solucion;
}
