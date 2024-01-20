package com.teste.treinamentos.repository.turma_participante;

import com.teste.treinamentos.entity.TurmaParticipante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class TurmaPartRepo implements ITurmaParticipante {
    private final JdbcTemplate template;

    @Autowired
    public TurmaPartRepo(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public List<TurmaParticipante> getTurmasByFuncionarioId(Integer codFuncionario) {
        var sql = """
                SELECT * FROM turma_participante
                WHERE fk_funcionario_cod = ?
                """;

        return template.query(sql, new TurmaPartMapper(), codFuncionario);
    }

    @Override
    public Integer insertFuncionario(Integer funcionarioId, Integer turmaId) {
        var sql = """
                INSERT INTO turma_participante (fk_funcionario_cod, fk_turma_cod)
                VALUES (?, ?);
                """;

        try {
            return template.update(sql, funcionarioId, turmaId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer removeFuncionarioFromTurma(Integer funcionarioId, Integer turmaId) {
        var sql = """
                DELETE FROM turma_participante
                WHERE fk_funcionario_cod = ? AND fk_turma_cod = ?;
                """;

        try {
            return template.update(sql, funcionarioId, turmaId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer removeAllByTurmaId(Integer turmaId) {
        var sql = """
                DELETE FROM turma_participante
                WHERE fk_turma_cod = ?;
                """;

        try {
            return template.update(sql, turmaId);

        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
