package com.example.novabiblioteca.model.categoria;

import com.example.novabiblioteca.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    public Categoria findCategoriaByIDcat(Long IDcat);
    public void deleteCategoriaByIDcat(Long IDcat);
}

