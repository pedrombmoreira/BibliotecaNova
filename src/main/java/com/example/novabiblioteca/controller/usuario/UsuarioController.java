package com.example.novabiblioteca.controller.usuario;

import com.example.novabiblioteca.model.usuario.Usuario;
import com.example.novabiblioteca.service.usuario.UsuarioService;
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
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Path relacionado a operações de usuários")
public class UsuarioController {

        private UsuarioService service;
        public UsuarioController(UsuarioService service){ this.service = service;}

        //http://localhost:8080/nova-biblioteca/usuario/listar
        @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
        @GetMapping("/listar")
        public List<Usuario> listar(){ return this.service.listar();}

        @Operation(summary = "Buscar usuário por UUID", description = "Retorna um usuário específico pelo seu UUID.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        })
        @GetMapping("/uuid/{uuid}")
        public ResponseEntity usuario(
                @Parameter(description = "UUID do usuário a ser buscado", required = true)
                @PathVariable String uuid){
                Optional<Usuario> usuario = Optional.ofNullable(this.service.findByUUID(uuid));
                return usuario.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
                }

        @Operation(summary = "Salvar novo usuário", description = "Cadastra um novo usuário.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Usuario.class))),
                @ApiResponse(responseCode = "400", description = "Erro na validação dos dados")
        })
        @Transactional
        @PostMapping()
        public ResponseEntity salvar(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder){
                this.service.salvar(usuario);
                URI uri = uriBuilder.path("/usuario/{uuid}").buildAndExpand(usuario.getUuid()).toUri();
                return ResponseEntity.created(uri).body(usuario);
        }

        @Operation(summary = "Atualizar usuário", description = "Atualiza as informações de um usuário existente.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Usuario.class))),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        })
        @PutMapping("/uuid")
        @Transactional
        public ResponseEntity atualizar(@RequestBody @Valid Usuario usuario){
                this.service.atualizarUUID(usuario);
                return ResponseEntity.ok(usuario);}

        @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo seu UUID.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        })
        @DeleteMapping("{uuid}")
        @Transactional
        public void deletar(
                @Parameter(description = "UUID do usuário a ser deletado", required = true)
                @PathVariable String uuid){
                this.service.excluirUUID(uuid);
        }


}
