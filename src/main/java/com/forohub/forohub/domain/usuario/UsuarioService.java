package com.forohub.forohub.domain.usuario;

import com.forohub.forohub.domain.usuario.validaciones.UserValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository userRepo;

    List<UserValidators> validatorsList;

    public ResponseEntity<DtoResponseUsuario> registerUsuario(DtoRegisterUsuario datosRegistroUsuario,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(datosRegistroUsuario);
        validatorsList.forEach(v -> v.validar(datosRegistroUsuario));
        Usuario usuarioId = userRepo.save(usuario);

        DtoResponseUsuario datosRespuestaUsuario = new DtoResponseUsuario(usuarioId);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioId.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }


}
