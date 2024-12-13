package com.example.novabiblioteca.service.biblioteca;

import com.example.novabiblioteca.model.autor.Autor;
import com.example.novabiblioteca.model.autor.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {

    private AutorRepository repository;

    public AutorService(AutorRepository repository){this.repository = repository; }
    public void salvar(Autor autor) { this.repository.save(autor);}
    public List<Autor> listar(){ return this.repository.findAll();}
    public Autor findByID(Long id){
        return this.repository.findAutorByIDaut(id);}
    public void atualizarID(Autor autor){
        Autor a = this.repository.findAutorByIDaut(autor.getIDaut());
        a.setNomeAut(autor.getNomeAut());
        this.repository.save(a);
    }
    public void excluirID(Long id){ this.repository.deleteAutorByIDaut(id);}

}
