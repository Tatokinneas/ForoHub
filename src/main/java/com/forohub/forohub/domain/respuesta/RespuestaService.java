package com.foroHub.foroHub.domain.respuesta;

import com.foroHub.foroHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.foroHub.foroHub.domain.topico.DtsListTopicos;
import com.foroHub.foroHub.domain.topico.Topico;
import com.foroHub.foroHub.domain.topico.TopicoRepository;
import com.foroHub.foroHub.domain.usuario.Usuario;
import com.foroHub.foroHub.domain.usuario.UsuarioRepository;

import java.net.URI;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ResponseEntity<DtsRespuesta> registrarRespuesta(DtsRegisterRespuesta dtsRegisterRespuesta,
                                                           UriComponentsBuilder uriComponentsBuilder) {

        if (topicoRepository.findById(dtsRegisterRespuesta.topico_id()).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico de la respuesta no fue encontrado. Revise el id.");
        }

        if (usuarioRepository.findById(dtsRegisterRespuesta.usuario_id()).isEmpty()){
            throw new ValidacionDeIntegridad("El autor de la respuesta no fue encontrado. Revise el id.");
        }

        Topico topico = topicoRepository.getReferenceById(dtsRegisterRespuesta.topico_id());
        Usuario autor = usuarioRepository.getReferenceById(dtsRegisterRespuesta.usuario_id());
        Respuesta respuesta = new Respuesta(dtsRegisterRespuesta, topico, autor);
        Respuesta respuestaRet = respuestaRepository.save(respuesta);

        DtsRespuesta dtsRespuesta = new DtsRespuesta(respuestaRet.getId(), respuestaRet.getMensaje(),
                respuestaRet.getFecha_creacion(), new DtsListTopicos(respuestaRet.getTopico()),
                respuestaRet.getAutor().getNombre(), respuestaRet.getSolucion());

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuestaRet.getId()).toUri();

        return ResponseEntity.created(url).body(dtsRespuesta);

    }

    public ResponseEntity<DtsRespuesta> actualizarRespuesta(DtsActualizarRespuesta dtsActualizarRespuesta,
                                                            Long id, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = null;
        Usuario usuario = null;
        if (respuestaRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("La respuesta no fue encontrada. Verifique el id.");
        }

        if (dtsActualizarRespuesta.topico_id() != null){
            if (topicoRepository.findById(dtsActualizarRespuesta.topico_id()).isEmpty()){
                throw new ValidacionDeIntegridad("El tópico de la respuesta no fue encontrado. Verifique el id.");
            }
            topico = topicoRepository.findById(dtsActualizarRespuesta.topico_id()).get();
        }

        if (dtsActualizarRespuesta.usuario_id() != null){
            if (usuarioRepository.findById(dtsActualizarRespuesta.usuario_id()).isEmpty()){
                throw new ValidacionDeIntegridad("El usuario de la respuesta no fue encontrado. Verifique el id.");
            }
            usuario = usuarioRepository.findById(dtsActualizarRespuesta.usuario_id()).get();
        }

        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarDatos(dtsActualizarRespuesta, topico, usuario);

        DtsRespuesta dtsRespuesta = new DtsRespuesta(respuesta.getId(), respuesta.getMensaje(),
        respuesta.getFecha_creacion(), new DtsListTopicos(respuesta.getTopico()), respuesta.getAutor().getNombre(), respuesta.getSolucion());

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(url).body(dtsRespuesta);
    }

    public ResponseEntity<Page> listarRespuestas(Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.listarRespuestas(paginacion)
                .map(DtsListRespuesta::new));
    }


    public ResponseEntity eliminarRespuesta(Long id) {

        if (respuestaRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("La respuesta no fue encontrada. Verifique el id.");
        }

        respuestaRepository.borrarRespuesta(id);

        return ResponseEntity.noContent().build();
    }

}
