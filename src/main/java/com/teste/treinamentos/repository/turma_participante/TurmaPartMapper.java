package com.teste.treinamentos.repository.turma_participante;

import com.teste.treinamentos.entity.TurmaParticipante;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TurmaPartMapper implements RowMapper<TurmaParticipante> {
    @Override
    public TurmaParticipante mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TurmaParticipante(
                rs.getInt("codigo"),
                rs.getInt("fk_turma_cod"),
                rs.getInt("fk_funcionario_cod")
        );
    }
}
