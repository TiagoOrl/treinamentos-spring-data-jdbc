package com.teste.treinamentos.dto.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTurmaDTO {
    @NotBlank(message = "campo inicio faltando")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String inicio;

    @NotBlank(message = "campo fim faltando")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String fim;

    @NotBlank(message = "campo localizacao faltando")
    @Size(min = 4, max = 200, message = "campo localizacao invalido")
    private String localizacao;

    @NotNull(message = "campo cursoCodigo faltando")
    private Integer cursoCodigo;
}
