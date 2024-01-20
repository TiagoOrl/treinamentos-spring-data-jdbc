package com.teste.treinamentos.repository.curso;

import com.teste.treinamentos.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepo implements ICurso {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
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
        var sql = """
                INSERT INTO curso (nome, descricao, duracao)
                VALUES (?, ?, ?)
                """;

        try {
            return jdbcTemplate.update(
                    sql,
                    curso.getNome(),
                    curso.getDescricao(),
                    curso.getDuracao()
            );
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateNome(String nome, Integer id) {
        var sql = """
                UPDATE curso
                SET nome = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, nome, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateDescricao(String descricao, Integer id) {
        var sql = """
                UPDATE curso
                SET descricao = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, descricao, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public Integer updateDuracao(Integer duracao, Integer id) {
        var sql = """
                UPDATE curso
                SET duracao = ?
                WHERE codigo = ?;
                """;

        try {
            return jdbcTemplate.update(sql, duracao, id);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
