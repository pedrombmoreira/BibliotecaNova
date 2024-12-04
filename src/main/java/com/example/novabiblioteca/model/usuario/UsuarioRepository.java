package com.example.novabiblioteca.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findUsuarioByUuid(UUID uuid);
    public void deleteUsuarioByUuid(UUID uuid);

}
