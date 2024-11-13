package com.example.novabiblioteca.service.biblioteca;

import com.example.novabiblioteca.model.livro.Livro;
import com.example.novabiblioteca.model.livro.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class LivroService {
    private LivroRepository repository;

    public LivroService(LivroRepository repository){this.repository = repository; }
    public void salvar(Livro livro) { this.repository.save(livro);}
    public List<Livro> listar(){ return this.repository.findAll();}
    public Livro findByUUID(String uuid){
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findLivroByUuid(uuidformatado);}
    public void atualizarUUID(Livro livro){
        Livro l = this.repository.findLivroByUuid(livro.getUuid());
        l.setTitulo(livro.getTitulo());
        l.setAutor(livro.getAutor());
        l.setCategoria(livro.getCategoria());
        l.setQuantidade(livro.getQuantidade());
        this.repository.save(l);
    }
    public void excluirUUID(String uuid){ this.repository.deleteLivroByUuid(UUID.fromString(uuid));}
}
