package com.teste.treinamentos.dto.funcionario;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
public class CreateFuncionarioDTO {
    @NotBlank(message = "campo nome faltando")
    @Size(min = 1, max = 200, message = "campo nome inválido")
    private String nome;

    @NotBlank
    @CPF(message = "campo cpf inválido")
    private String cpf;

    @NotNull(message = "campo nascimento vazio")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String nascimento;

    @NotBlank(message = "campo cargo faltando")
    @Size(min = 1, max = 100, message = "campo cargo inválido")
    private String cargo;

    @NotNull(message = "campo admissao vazio")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String admissao;
}
