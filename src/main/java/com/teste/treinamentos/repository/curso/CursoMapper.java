package com.teste.treinamentos.repository.curso;

import com.teste.treinamentos.entity.Curso;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoMapper implements RowMapper<Curso> {
    @Override
    public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Curso(
                rs.getInt("codigo"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getInt("duracao")
        );
    }
}
