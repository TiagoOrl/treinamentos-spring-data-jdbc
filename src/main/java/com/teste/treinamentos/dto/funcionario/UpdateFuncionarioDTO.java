package com.teste.treinamentos.dto.funcionario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdateFuncionarioDTO {

    @NotNull(message = "campo código faltando")
    private Integer codigo;

    @Size(min = 1, max = 200, message = "campo nome inválido")
    private String nome;

    @CPF(message = "campo cpf inválido")
    private String cpf;

    @Size(min = 1, max = 100, message = "campo cargo inválido")
    private String cargo;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String admissao;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String nascimento;
}
