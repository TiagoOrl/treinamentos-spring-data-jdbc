package com.teste.treinamentos.repository;

import com.teste.treinamentos.entity.Funcionario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class FuncionarioMapper implements RowMapper<Funcionario> {
    @Override
    public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Funcionario(
                rs.getInt("codigo"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("cargo"),
                LocalDate.parse(rs.getString("admissao")),
                LocalDate.parse(rs.getString("nascimento")),
                rs.getBoolean("status")
        );
    }
}
