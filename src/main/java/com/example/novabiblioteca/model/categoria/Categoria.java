package com.example.novabiblioteca.model.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa uma categoria no sistema")
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de uma categoria", example = "1")
    private Long IDcat;

    @NonNull
    @Column(nullable = false)
    @Schema(description = "Nome de uma categoria", example = "Mistério")
    private String nomeCat;
}
