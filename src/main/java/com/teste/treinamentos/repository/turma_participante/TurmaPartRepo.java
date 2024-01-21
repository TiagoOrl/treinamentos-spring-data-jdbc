package com.teste.treinamentos.repository.turma_participante;

import com.teste.treinamentos.entity.TurmaParticipante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public List<TurmaParticipante> getAllByTurmaId(Integer turmaId) {
        var sql = """
                SELECT * FROM turma_participante
                WHERE fk_turma_cod = ?
                """;

        return template.query(sql, new TurmaPartMapper(), turmaId);
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
    public Integer removeAllStudentsByTurmaId(Integer turmaId) {
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

    @Override
    public Optional<TurmaParticipante> checkIfTurmaContainsFuncionario(Integer turmaId, Integer funcionarioId) {
        var sql = """
                SELECT * FROM turma_participante
                WHERE fk_turma_cod = ? AND fk_funcionario_cod = ?;
                """;

        return template.query(sql, new TurmaPartMapper(), turmaId, funcionarioId).stream().findFirst();
    }

    /**
     * Exclui um funcionario de turmas futuras
     * @param funcionarioId
     * @return
     */
    @Override
    public Integer removeStudentFromAllTurmas(Integer funcionarioId) {
        var sql = """
                DELETE turma_participante
                FROM turma_participante
                INNER JOIN turma ON turma_participante.fk_turma_cod = turma.codigo
                WHERE turma_participante.fk_funcionario_cod = ? AND turma.inicio > ?
                """;

        return template.update(sql, funcionarioId, LocalDate.now());
    }
}
