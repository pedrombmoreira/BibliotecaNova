package com.example.novabiblioteca.model.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    public Emprestimo findEmprestimoByUuid(UUID uuid);
    public void deleteEmprestimoByUuid(UUID uuid);
}
