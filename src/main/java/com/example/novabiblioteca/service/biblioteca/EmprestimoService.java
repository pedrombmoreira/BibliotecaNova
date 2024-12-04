package com.example.novabiblioteca.service.biblioteca;

import com.example.novabiblioteca.model.emprestimo.Emprestimo;
import com.example.novabiblioteca.model.emprestimo.EmprestimoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {

    private EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository repository){this.repository = repository; }

    public void salvar(Emprestimo emprestimo) { this.repository.save(emprestimo);}
    public List<Emprestimo> listar(){ return this.repository.findAll();}
    public Emprestimo findByUUID(String uuid){
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findEmprestimoByUuid(uuidformatado);}
    public void atualizarUUID(Emprestimo emprestimo){
        Emprestimo e = this.repository.findEmprestimoByUuid(emprestimo.getUuid());
        e.setLivro(emprestimo.getLivro());
        e.setUsuario(emprestimo.getUsuario());
        e.setDataemp(emprestimo.getDataemp());
        e.setDatadev(emprestimo.getDatadev());
        this.repository.save(e);
    }
    public void excluirUUID(String uuid){ this.repository.deleteEmprestimoByUuid(UUID.fromString(uuid));}
}
