package com.example.novabiblioteca.service.biblioteca;

import com.example.novabiblioteca.model.categoria.Categoria;
import com.example.novabiblioteca.model.categoria.CategoriaRepository;
import com.example.novabiblioteca.model.livro.Livro;
import com.example.novabiblioteca.model.livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){this.repository = repository; }
    public void salvar(Categoria categoria) { this.repository.save(categoria);}
    public List<Categoria> listar(){ return this.repository.findAll();}
    public Categoria findByID(Long id){
        return this.repository.findCategoriaByIDcat(id);}
    public void atualizarID(Categoria categoria){
        Categoria c = this.repository.findCategoriaByIDcat(categoria.getIDcat());
        c.setNomeCat(categoria.getNomeCat());
        this.repository.save(c);
    }
    public void excluirID(Long id){ this.repository.deleteCategoriaByIDcat(id);}

}
