package com.teste.treinamentos.dto.curso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCursoDTO {
    @NotBlank(message = "campo nome faltando")
    @Size(min = 3, max = 255, message = "campo nome inválido")
    private String nome;

    @NotBlank(message = "campo descricao faltando")
    @Size(min = 5, max = 4000, message = "campo descricao invalido")
    private String descricao;

    @NotNull(message = "campo duracao faltando")
    @Min(value = 1, message = "campo duracao deve ser maior que 0")
    private Integer duracao;
}
