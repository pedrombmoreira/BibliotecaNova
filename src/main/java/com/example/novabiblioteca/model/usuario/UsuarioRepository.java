package com.example.novabiblioteca.model.usuario;

import jakarta.validation.constraints.Email;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findUsuarioByUuid(UUID uuid);
    public void deleteUsuarioByUuid(UUID uuid);
    public Usuario findByEmail(String email);

    String email(@NonNull @Email(message = "O email fornecido está incorreto") String email);
}
