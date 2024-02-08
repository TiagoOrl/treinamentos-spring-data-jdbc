package com.teste.treinamentos.repository;

import com.teste.treinamentos.entity.Funcionario;
import com.teste.treinamentos.repository.funcionario.FuncionarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.*;

@DataJdbcTest
@TestPropertySource(locations="classpath:application-test.properties")
@Sql(scripts={"classpath:squema.sql"})
public class FuncionarioRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    private FuncionarioRepo funcionarioRepo;

    @Autowired
    public FuncionarioRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.funcionarioRepo = new FuncionarioRepo(jdbcTemplate);
    }

    @Test
    public void shouldSaveFuncionario() {
        var funcionario = new Funcionario();
        funcionario.setNome("Tiago");
        funcionario.setCargo("Analista de Dados Pleno");
        funcionario.setAdmissao("2015-03-25");
        funcionario.setNascimento("1996-05-21");
        funcionario.setCpf("88055692084");

        var rowsAffected = funcionarioRepo.insertOne(funcionario);

        assertThat(rowsAffected).isEqualTo(1);
    }
}
