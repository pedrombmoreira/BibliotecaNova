package com.example.novabiblioteca.model.emprestimo;

import com.example.novabiblioteca.model.livro.Livro;
import com.example.novabiblioteca.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "emprestimos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um empréstimo no sistema")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de um empréstimo", example = "1")
    private Long IDemp;

    @UuidGenerator
    @Column(nullable = false, unique = true, updatable = false)
    @Schema(description = "UUID do empréstimo", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;

    @NonNull
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDlivro", nullable = false)
    @Schema(description = "Livro que foi retirado", example = "Coraline")
    private Livro livro;

    @NonNull
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDuser", nullable = false)
    @Schema(description = "Usuário que fez o empréstimo", example = "Paulo")
    private Usuario usuario;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Schema(description = "Data do empréstimo", example = "xx/xx/xxxx")
    private Date dataemp;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Schema(description = "Data de devolução do livro", example = "xx/xx/xxxx")
    private Date datadev;

}
