package com.teste.treinamentos.repository.turma;

import com.teste.treinamentos.entity.Turma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TurmaMapper implements RowMapper<Turma> {
    @Override
    public Turma mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Turma(
                rs.getInt("codigo"),
                LocalDate.parse(rs.getString("inicio")),
                LocalDate.parse(rs.getString("fim")),
                rs.getString("localizacao"),
                rs.getInt("fk_curso_cod")
        );

    }
}
