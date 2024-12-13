package com.example.novabiblioteca.controller.biblioteca;

import com.example.novabiblioteca.model.livro.Livro;
import com.example.novabiblioteca.model.usuario.Usuario;
import com.example.novabiblioteca.service.biblioteca.LivroService;
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
@RequestMapping("/livro")
@Tag(name = "Livros", description = "Path relacionado a operações de livros")
public class LivroController {
    private LivroService service;
    public LivroController(LivroService service){ this.service = service;}

    //http://localhost:8080/nova-biblioteca/livro/listar
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista de todos os livros cadastrados.")
    @GetMapping("/listar")
    public List<Livro> listar(){ return this.service.listar();}

    @Operation(summary = "Buscar livro por UUID", description = "Retorna um livro específico pelo seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/uuid/{uuid}")
    public Livro livro(
            @Parameter(description = "UUID do livro a ser buscado", required = true)
            @PathVariable String uuid){
        return this.service.findByUUID(uuid);
    }

    @Operation(summary = "Salvar novo livro", description = "Cadastra um novo livro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Livro.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados")
    })
    @Transactional
    @PostMapping()
    public ResponseEntity<Livro> salvar(@RequestBody @Valid Livro livro, UriComponentsBuilder uriBuilder) {
        this.service.salvar(livro);
        URI uri = uriBuilder.path("livro/{uuid}").buildAndExpand(livro.getUuid()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @Operation(summary = "Atualizar livro", description = "Atualiza as informações de um livro existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Livro.class))),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @PutMapping("/uuid")
    public ResponseEntity<Livro> atualizar(@RequestBody @Valid Livro livro){
        this.service.atualizarUUID(livro);
        return ResponseEntity.ok(livro);}

    @Operation(summary = "Deletar livro", description = "Remove um livro pelo seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @Transactional
    @DeleteMapping("deletar/{uuid}")
    public void deletar(
            @Parameter(description = "UUID do livro a ser deletado", required = true)
            @PathVariable String uuid){
        this.service.excluirUUID(uuid);
    }
}
