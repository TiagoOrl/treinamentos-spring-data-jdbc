package com.teste.treinamentos.service;


import com.teste.treinamentos.configs.ModelMapperTest;
import com.teste.treinamentos.dto.curso.GetCursoDTO;
import com.teste.treinamentos.entity.Curso;
import com.teste.treinamentos.repository.curso.CursoRepo;
import com.teste.treinamentos.repository.funcionario.FuncionarioRepo;
import com.teste.treinamentos.repository.turma.TurmaRepo;
import com.teste.treinamentos.repository.turma_participante.TurmaPartRepo;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    CursoRepo cursoRepository;
    @Mock
    TurmaRepo turmaRepository;
    @Mock
    TurmaPartRepo turmaPartRepository;
    @Mock
    FuncionarioRepo funcionarioRepo;
    @InjectMocks
    CursoService cursoService;

    @Test
    public void shouldGetAllCursosInDTOFormat() {
        var cursos = List.of(
                new Curso(1, "Python", "iniciante...", 123, true, 0, List.of()),
                new Curso(2, "C++", "intermediario...", 180, true, 0, List.of())
        );

        var expectedDTOList = List.of(
            new GetCursoDTO(1, "Python", "iniciante...", 123, 0, List.of(), true),
                new GetCursoDTO(2, "C++", "intermediario...", 180, 0, List.of(), true)
        );

        Mockito.when(cursoRepository.getAll(any())).thenReturn(cursos);
        Mockito.when(turmaRepository.getAllByCourseId(any())).thenReturn(List.of());


        assertThat(cursoService.getAll(Optional.of(true))).isEqualTo(expectedDTOList);

    }

}
