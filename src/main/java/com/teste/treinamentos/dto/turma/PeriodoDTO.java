package com.teste.treinamentos.dto.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PeriodoDTO {
    @NotBlank(message = "campo inicio faltando")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "padrao de data: AAAA-MM-DD")
    private String inicio;

    @NotBlank(message = "campo fim faltando")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "padrao de data: AAAA-MM-DD")
    private String fim;
}
