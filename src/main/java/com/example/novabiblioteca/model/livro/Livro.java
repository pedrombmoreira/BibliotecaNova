package com.example.novabiblioteca.model.livro;

import com.example.novabiblioteca.model.autor.Autor;
import com.example.novabiblioteca.model.categoria.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um livro no sistema")
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de um livro", example = "1")
    private Long IDlivro;

    @UuidGenerator
    @Column(nullable = false, unique = true, updatable = false)
    @Schema(description = "UUID do livro", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;

    @NonNull
    @Column(nullable = false)
    @Schema(description = "Título do livro", example = "Coraline")
    private String titulo;

    @NonNull
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "IDaut", nullable = false)
    @Schema(description = "Autor do livro", example = "Neil Gaiman")
    private Autor autor;

    @NonNull
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDcat", nullable = false)
    @Schema(description = "Categoria do livro", example = "Mistério")
    private Categoria categoria;

    @NonNull
    @Column(nullable = false)
    @Schema(description = "Número de exemplares do livro", example = "5")
    private int quantidade;
}
