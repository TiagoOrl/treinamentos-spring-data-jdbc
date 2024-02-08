package com.teste.treinamentos.repository;

import com.teste.treinamentos.repository.funcionario.FuncionarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

@DataJdbcTest
@TestPropertySource(locations="classpath:application-test.properties")
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

    }
}
