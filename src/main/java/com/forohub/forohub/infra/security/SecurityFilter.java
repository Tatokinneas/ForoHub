package com.foroHub.foroHub.infra.security;

import com.foroHub.foroHub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtener el token del header
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("Header: " + headerName + " = " + request.getHeader(headerName));
        }
        var authHeader = request.getHeader("Authorization");
        System.out.println(request.getHeader("Authorization"));
        if (authHeader != null){
        System.out.println("request.getHeader()");
            var token = authHeader.replace("Bearer ", "");       // Nombre del header, y quita la palabra "Bearer" del log y q devuelva solo el token
            var username = tokenService.getSubject(token);
            if (username != null){
                // Token es válido
                System.out.println(username);
                var usuario = usuarioRepository.findByEmail(username);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());      // Forza un inicio de sesión
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        System.out.println("nulo aque es nulo");
        filterChain.doFilter(request, response);    // Llama al siguiente filtro (son dos) para poder mostrar los datos

    }
}
