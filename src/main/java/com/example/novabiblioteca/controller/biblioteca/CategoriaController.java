package com.example.novabiblioteca.controller.biblioteca;

import com.example.novabiblioteca.model.categoria.Categoria;
import com.example.novabiblioteca.service.biblioteca.CategoriaService;

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
@RequestMapping("/categoria")
@Tag(name = "Categorias", description = "Path relacionado a operações de categorias")
public class CategoriaController {

    private CategoriaService service;
    public CategoriaController(CategoriaService service) {this.service = service;}

    //http://localhost:8080/nova-biblioteca/categoria/listar
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas.")
    @GetMapping("/listar")
    public List<Categoria> listar(){ return this.service.listar();}

    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity categoria(
            @Parameter(description = "ID da categoria a ser buscada.", required = true)
            @PathVariable Long id){
        Optional<Categoria> categoria = Optional.ofNullable(this.service.findByID(id));
        return categoria.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar nova categoria", description = "Cadastra uma nova categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados")
    })
    @Transactional
    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Valid Categoria categoria, UriComponentsBuilder uriBuilder){
        this.service.salvar(categoria);
        URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getIDcat()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @Operation(summary = "Atualizar categoria", description = "Atualiza as informações de uma categoria existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Categoria categoria){
        this.service.atualizarID(categoria);
        return ResponseEntity.ok(categoria);}

    @Operation(summary = "Deletar categoria", description = "Remove uma categoria pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("deletar/{id}")
    @Transactional
    public void deletar(
            @Parameter(description = "ID da categoria a ser deletada", required = true)
            @PathVariable Long id){
        this.service.excluirID(id);
    }

}
