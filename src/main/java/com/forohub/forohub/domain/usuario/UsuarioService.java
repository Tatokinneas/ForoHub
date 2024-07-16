package com.foroHub.foroHub.domain.usuario;

import com.foroHub.foroHub.domain.usuario.validaciones.UsuariosValidate;
import com.foroHub.foroHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<UsuariosValidate> validadores;

    public ResponseEntity<DtsResponseUsuario> registarUsuario(DtsRegisterUsuario dtsRegisterUsuario,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(dtsRegisterUsuario);
        validadores.forEach(v -> v.validar(dtsRegisterUsuario));
        Usuario usuarioConId = usuarioRepository.save(usuario);

        DtsResponseUsuario dtsResponseUsuario = new DtsResponseUsuario(usuarioConId);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioConId.getId()).toUri();

        return ResponseEntity.created(url).body(dtsResponseUsuario);
    }

    public ResponseEntity<DtsResponseUsuario> actualizarUsuario(DtsUpdateUsuario dtsUpdateUsuario, Long id, UriComponentsBuilder uriComponentsBuilder) {

        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }

        Usuario usuario = usuarioRepository.getReferenceById(id);

        DtsRegisterUsuario dtsRegisterUsuario = realizarCopiaActualizado(usuario, dtsUpdateUsuario);

        validadores.forEach(v -> v.validar(dtsRegisterUsuario));

        if (dtsUpdateUsuario.nombre() != null){
            usuario.setNombre(dtsUpdateUsuario.nombre());
        }
        if (dtsUpdateUsuario.email() != null){
            usuario.setEmail(dtsUpdateUsuario.email());
        }
        if (dtsUpdateUsuario.clave() != null){
            usuario.setClave(dtsUpdateUsuario.clave());
        }
        if (dtsUpdateUsuario.perfil() != null){
            usuario.setPerfil(Perfil.fromString(dtsUpdateUsuario.perfil()));
        }

        DtsResponseUsuario dtsResponseUsuario = new DtsResponseUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(dtsResponseUsuario);

    }

    public ResponseEntity<Page> listarUsuarios(Pageable paginacion) {

        return ResponseEntity.ok(usuarioRepository.listarUsuarios(paginacion)
                .map(DtsResponseUsuario::new));
    }

    public ResponseEntity eliminarUsuario(Long id) {

        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private DtsRegisterUsuario realizarCopiaActualizado(Usuario usuario, DtsUpdateUsuario dtsUpdateUsuario){
        String nombre = usuario.getNombre();
        String email = usuario.getEmail();
        String clave = usuario.getClave();
        String perfil = usuario.getPerfil().toString();

        if (dtsUpdateUsuario.nombre() != null){
            nombre = dtsUpdateUsuario.nombre();
        }
        if (dtsUpdateUsuario.email() != null){
            email = dtsUpdateUsuario.email();
        }
        if (dtsUpdateUsuario.clave() != null){
            clave = dtsUpdateUsuario.clave();
        }
        if (dtsUpdateUsuario.perfil() != null){
            perfil = dtsUpdateUsuario.perfil();
        }

        DtsRegisterUsuario dtsRegisterUsuario = new DtsRegisterUsuario(nombre, email, clave, perfil);
        return dtsRegisterUsuario;
    }

}
