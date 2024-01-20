package com.teste.treinamentos.dto.curso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCursoDTO {
    @NotNull(message = "campo codigo faltando")
    private Integer codigo;

    @Size(min = 3, max = 255, message = "campo nome inválido")
    private String nome;

    @Size(min = 5, max = 4000, message = "campo descricao invalido")
    private String descricao;

    @Min(value = 1, message = "campo duracao deve ser maior que 0")
    private Integer duracao;
}
