package com.teste.treinamentos.repository.curso;

import com.teste.treinamentos.entity.Curso;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class CursoRepo implements ICurso {
    private final JdbcTemplate jdbcTemplate;

    public CursoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Curso> getAll() {
        var sql = """
                SELECT *
                FROM curso
                LIMIT 100;
                """;

        return jdbcTemplate.query(sql, new CursoMapper());
    }

    @Override
    public Optional<Curso> getById(Integer id) {
        var sql = """
                SELECT *
                FROM curso
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.query(sql, new CursoMapper(), id).stream().findFirst();
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public List<Curso> getByName(String name) {
        var sql = """
                SELECT *
                FROM curso
                WHERE name LIKE ?;
                """;

        return jdbcTemplate.query(sql, new CursoMapper(), "%" + name + "%");
    }

    @Override
    public Integer insertOne(Curso curso) {
        return null;
    }

    @Override
    public Integer updateNome(String nome) {
        return null;
    }

    @Override
    public Integer updateDescricao(String descricao) {
        return null;
    }

    @Override
    public Integer updateDuracao(Integer duracao) {
        return null;
    }
}
