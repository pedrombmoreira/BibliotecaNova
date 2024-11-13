package com.example.novabiblioteca.model.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um usuario no sistema")
public class Usuario {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do usuario", example = "1")
    private Long IDuser;

    @UuidGenerator
    @Column(nullable = false, updatable = false, unique = true)
    @Schema(description = "UUID do usuario", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;

    @NonNull
    @Column(name = "nomeuser", nullable = false, length = 50)
    @Size(max = 50, message = "O nome do usuário não pode ter mais de 50 caracteres")
    @Schema(description = "Nome do usuario", example = "Peterson")
    private String nomeUser;

    @NonNull
    @Column(nullable = false, length = 100, unique = true)
    @Email(message = "O email fornecido está incorreto")
    @Schema(description = "Email do usuario", example = "peterson@exemplo.com")
    private String email;

    @NonNull
    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "O tamanho da senha do usuário não pode ter mais de 100 caracteres")
    @Schema(description = "Senha do usuario", example = "1234")
    private String senha;

    @NonNull
    @Column(nullable = false, length = 11, unique = true)
    @Schema(description = "CPF do usuario", example = "999.999.999-99")
    private String cpf;

    @NonNull
    @Column(nullable = false)
    @Schema(description = "telefone do usuario", example = "(55)99999-9999")
    private String telefone;

    @NonNull
    @Column(nullable = false)
    @Schema(description = "Permissão do usuario", example = "true")
    private Boolean permissao;
}
