package com.example.novabiblioteca.model.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    public Livro findLivroByUuid(UUID uuid);
    public void deleteLivroByUuid(UUID uuid);
}
