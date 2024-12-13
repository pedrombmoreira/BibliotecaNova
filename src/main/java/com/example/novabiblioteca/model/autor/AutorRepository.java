package com.example.novabiblioteca.model.autor;

import com.example.novabiblioteca.model.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    public Autor findAutorByIDaut(Long IDaut);
    public void deleteAutorByIDaut(Long IDaut);

}
