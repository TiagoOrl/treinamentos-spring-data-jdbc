package com.teste.treinamentos.repository.turma;

import com.teste.treinamentos.entity.Turma;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public class TurmaRepo implements ITurma {
    private final JdbcTemplate template;

    public TurmaRepo(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Turma> getAll() {
        var sql = """
                SELECT * FROM turma
                ORDER BY inicio ASC
                LIMIT 100;
                """;

        return template.query(sql, new TurmaMapper());
    }

    @Override
    public Optional<Turma> getById(Integer id) {
        var sql = """
                SELECT * FROM turma
                WHERE codigo = ?;
                """;

        return template.query(sql, new TurmaMapper(), id).stream().findFirst();
    }

    @Override
    public List<Turma> getAllByCourseId(Integer courseId) {
        var sql = """
                SELECT * FROM turma
                WHERE fk_curso_cod = ?
                ORDER BY inicio ASC
                LIMIT 100;
                """;

        return template.query(sql, new TurmaMapper(), courseId);
    }

    @Override
    public List<Turma> getAllByCourseIdAndFuncionarioId(Integer courseId, Integer funcionarioId) {
        var sql = """
                SELECT turma.*, turma_participante.*
                FROM turma
                INNER JOIN turma_participante ON turma.codigo = turma_participante.fk_turma_cod
                WHERE turma.fk_curso_cod = ? AND turma_participante.fk_funcionario_cod = ?
                LIMIT 100;
                """;

        return template.query(sql, new TurmaMapper(), courseId, funcionarioId);
    }


    @Override
    public Integer insertOne(Turma turma) {
        var sql = """
                INSERT INTO turma (inicio, fim, localizacao, fk_curso_cod)
                VALUES (?, ?, ?, ?);
                """;

        try {
            return template.update(sql, turma.getInicio(), turma.getFim(), turma.getLocalizacao(), turma.getCursoCodigo());
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer deleteOneById(Integer id) {
        var sql = """
                DELETE FROM turma
                WHERE codigo = ?
                """;
        try {
            return template.update(sql, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer deleteAllByCourseId(Integer courseId) {
        var sql = """
                DELETE FROM turma
                WHERE fk_curso_cod = ?
                """;

        try {
            return template.update(sql, courseId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
