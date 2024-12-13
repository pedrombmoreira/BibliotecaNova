package com.example.novabiblioteca.controller.biblioteca;

import com.example.novabiblioteca.model.emprestimo.Emprestimo;
import com.example.novabiblioteca.model.livro.Livro;
import com.example.novabiblioteca.service.biblioteca.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@Tag(name = "Emprestimos", description = "Path relacionado a operações de empréstimos")
public class EmprestimoController {

    private EmprestimoService service;
    public EmprestimoController(EmprestimoService service){ this.service = service;}

    //http://localhost:8080/nova-biblioteca/emprestimo/listar
    @Operation(summary = "Listar todos os empréstimos", description = "Retorna uma lista de todos os empréstimos cadastrados.")
    @GetMapping("/listar")
    public List<Emprestimo> listar(){ return this.service.listar();}

    @Operation(summary = "Buscar empréstimo por UUID", description = "Retorna um empréstimo específico pelo seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo encontrado"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    })
    @GetMapping("/uuid/{uuid}")
    public Emprestimo emprestimo(
            @Parameter(description = "UUID do empréstimo a ser buscado", required = true)
            @PathVariable String uuid){
        return this.service.findByUUID(uuid);
    }

    @Operation(summary = "Salvar novo empréstimo", description = "Cadastra um novo empréstimo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empréstimo cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emprestimo.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados")
    })
    @Transactional
    @PostMapping()
    public void salvar(@RequestBody @Valid Emprestimo emprestimo){ this.service.salvar(emprestimo);}
    public ResponseEntity<Emprestimo> salvar(@RequestBody @Valid Emprestimo emprestimo, UriComponentsBuilder uriBuilder) {
        this.service.salvar(emprestimo);
        URI uri = uriBuilder.path("emprestimo/{uuid}").buildAndExpand(emprestimo.getUuid()).toUri();
        return ResponseEntity.created(uri).body(emprestimo);
    }

    @Operation(summary = "Atualizar empréstimo", description = "Atualiza as informações de um empréstimo existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empréstimo atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emprestimo.class))),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    })
    @PutMapping("/uuid")
    public ResponseEntity<Emprestimo> atualizar(@RequestBody @Valid Emprestimo emprestimo){
        this.service.atualizarUUID(emprestimo);
        return ResponseEntity.ok(emprestimo);}

    @Operation(summary = "Deletar empréstimo", description = "Remove um empréstimo pelo seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empréstimo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    })
    @DeleteMapping("deletar/{uuid}")
    public void deletar(
            @Parameter(description = "UUID do empréstimo a ser deletado", required = true)
            @PathVariable String uuid){
        this.service.excluirUUID(uuid);
    }
}
