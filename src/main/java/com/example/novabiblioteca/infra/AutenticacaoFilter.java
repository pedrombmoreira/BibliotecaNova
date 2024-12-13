package com.example.novabiblioteca.infra;

import com.example.novabiblioteca.service.usuario.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {


    private final TokenServiceJWT tokenServiceJWT;
    private final AutenticacaoService autenticacaoService;
    public AutenticacaoFilter(TokenServiceJWT tokenServiceJWT, AutenticacaoService autenticacaoService){
        this.tokenServiceJWT = tokenServiceJWT;
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filtro para autenticação e autorização");

        String token = recuperarToken(request);
        System.out.println("Token: " + token);

        if (token != null) {
            String subject = this.tokenServiceJWT.getSubject(token);
            System.out.println("login da req. " + subject);

            UserDetails userDetails = this.autenticacaoService.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.replace("Bearer", "").trim();
        }
        return null;
    }
}
