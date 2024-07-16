package com.foroHub.foroHub.domain.topico;

import com.foroHub.foroHub.domain.topico.validaciones.TopicValidators;
import com.foroHub.foroHub.domain.usuario.Usuario;
import com.foroHub.foroHub.domain.usuario.UsuarioRepository;
import com.foroHub.foroHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.foroHub.foroHub.domain.curso.Curso;
import com.foroHub.foroHub.domain.curso.CursoRepository;

import java.net.URI;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<TopicValidators> validadores;

    public ResponseEntity<DtsRespuestaTopico> registrar(DtsRegisterTopico dtsRegisterTopico, UriComponentsBuilder uriComponentsBuilder){

        if (cursoRepository.findByNombreContainsIgnoreCase(dtsRegisterTopico.nombreCurso()).isEmpty()){
            throw new ValidacionDeIntegridad("El curso no fue encontrado");
        }

        if (usuarioRepository.findById(dtsRegisterTopico.usuario_id()).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado");
        }

        validadores.forEach(v -> v.validar(dtsRegisterTopico));

        var topico = new Topico(dtsRegisterTopico);
        topico.setCurso(cursoRepository.findByNombreContainsIgnoreCase(dtsRegisterTopico.nombreCurso()).get());
        topico.setAutor(usuarioRepository.findById(dtsRegisterTopico.usuario_id()).get());
        Topico topicoRet = topicoRepository.save(topico);

        DtsRespuestaTopico dtsRespuestaTopico = new DtsRespuestaTopico(topicoRet.getId(), topicoRet.getTitulo(),
                topicoRet.getMensaje(), topicoRet.getFecha_creacion().toString(), topicoRet.getStatus().toString(),
                topicoRet.getCurso().getId(), topicoRet.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRet.getId()).toUri();

        return ResponseEntity.created(url).body(dtsRespuestaTopico);
    }

    public ResponseEntity<Page> listarTopicos(Pageable paginacion){

        return ResponseEntity.ok(topicoRepository.listarTopicos(paginacion)
                .map(DtsListTopicoAndRespuestas::new));
    }

    public ResponseEntity<DtsListTopicoAndRespuestas> listarDetalleTopicos(Long id){

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DtsListTopicoAndRespuestas(topico);

        return ResponseEntity.ok(datosTopico);
    }


    public ResponseEntity<DtsRespuestaTopico> actualizarTopico(DtsUpdateTopico dtsUpdateTopico,
                                                               Long id, UriComponentsBuilder uriComponentsBuilder) {

        Curso curso = null;
        Usuario usuario = null;

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        if (dtsUpdateTopico.nombreCurso() != null) {
            if (cursoRepository.findByNombreContainsIgnoreCase(dtsUpdateTopico.nombreCurso()).isEmpty()) {
                throw new ValidacionDeIntegridad("El curso no fue encontrado");
            }
            curso = cursoRepository.findByNombreContainsIgnoreCase(dtsUpdateTopico.nombreCurso()).get();
        }

        if (dtsUpdateTopico.usuario_id() != null) {
            if (usuarioRepository.findById(dtsUpdateTopico.usuario_id()).isEmpty()) {
                throw new ValidacionDeIntegridad("El usuario no fue encontrado");
            }
            usuario = usuarioRepository.findById(dtsUpdateTopico.usuario_id()).get();
        }

        Topico topico = topicoRepository.getReferenceById(id);

        DtsRegisterTopico dtsRegisterTopico = new DtsRegisterTopico(dtsUpdateTopico.titulo(),
                dtsUpdateTopico.mensaje(), dtsUpdateTopico.nombreCurso(),
                dtsUpdateTopico.usuario_id());

        validadores.forEach(v -> v.validar(dtsRegisterTopico));

        topico.actualizarDatos(dtsUpdateTopico, curso, usuario);

        DtsRespuestaTopico dtsRespuestaTopico = new DtsRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFecha_creacion().toString(), topico.getStatus().toString(),
                topico.getCurso().getId(), topico.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(dtsRespuestaTopico);

    }


    public ResponseEntity eliminarTopico(Long id) {

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        topicoRepository.borrarTopico(id);

        return ResponseEntity.noContent().build();
    }
}
