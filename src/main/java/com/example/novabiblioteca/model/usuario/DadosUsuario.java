package com.example.novabiblioteca.model.usuario;

public record DadosUsuario(Long id, String login, Boolean permissao) {

    public DadosUsuario(Usuario usuario) { this(usuario.getIDuser(), usuario.getEmail(), usuario.getPermissao());}
}
