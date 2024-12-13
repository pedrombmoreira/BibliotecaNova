package com.example.novabiblioteca.controller.biblioteca;

import com.example.novabiblioteca.model.autor.Autor;
import com.example.novabiblioteca.service.biblioteca.AutorService;
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
import java.util.Optional;

@RestController
@RequestMapping("/autor")
@Tag(name = "Autores", description = "Path relacionado a operações de autores")
public class AutorController {

    private AutorService service;

    public AutorController(AutorService service) {this.service = service;}

    //http://localhost:8080/nova-biblioteca/autor/listar
    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de todos os autores cadastrados.")
    @GetMapping("/listar")
    public List<Autor> listar(){ return this.service.listar();}

    @Operation(summary = "Buscar autor por ID", description = "Retorna um autor específico por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity autor(
            @Parameter(description = "ID do autor a ser buscado.", required = true)
            @PathVariable Long id){
        Optional<Autor> autor = Optional.ofNullable(this.service.findByID(id));
        return autor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar novo autor", description = "Cadastra um novo autor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor cadastrado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Autor.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados")
    })
    @Transactional
    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Valid Autor autor, UriComponentsBuilder uriBuilder){
        this.service.salvar(autor);
        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getIDaut()).toUri();
        return ResponseEntity.created(uri).body(autor);
    }

    @Operation(summary = "Atualizar autor", description = "Atualiza as informações de um autor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Autor.class))),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Autor autor){
        this.service.atualizarID(autor);
        return ResponseEntity.ok(autor);}

    @Operation(summary = "Deletar autor", description = "Remove um autor pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @DeleteMapping("deletar/{id}")
    @Transactional
    public void deletar(
            @Parameter(description = "ID do autor a ser deletado", required = true)
            @PathVariable Long id){
        this.service.excluirID(id);
    }
}
