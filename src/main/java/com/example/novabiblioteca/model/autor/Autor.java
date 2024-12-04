package com.example.novabiblioteca.model.autor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um autor no sistema")
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de um autor", example = "1")
    private Long IDaut;

    @NonNull
    @Column(nullable = false)
    @Schema(description = "Nome de um autor", example = "Neil Gaiman")
    private String nomeAut;

}
