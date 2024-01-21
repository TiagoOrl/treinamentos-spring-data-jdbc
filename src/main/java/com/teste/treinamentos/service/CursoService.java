package com.teste.treinamentos.service;

import com.teste.treinamentos.dto.curso.CreateCursoDTO;
import com.teste.treinamentos.dto.curso.GetCursoDTO;
import com.teste.treinamentos.dto.curso.UpdateCursoDTO;
import com.teste.treinamentos.entity.Curso;
import com.teste.treinamentos.entity.Turma;
import com.teste.treinamentos.repository.curso.CursoRepo;
import com.teste.treinamentos.repository.funcionario.FuncionarioRepo;
import com.teste.treinamentos.repository.turma.TurmaRepo;
import com.teste.treinamentos.repository.turma_participante.TurmaPartRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepo repository;
    private final TurmaRepo turmaRepository;
    private final TurmaPartRepo turmaPartRepository;
    private final FuncionarioRepo funcionarioRepo;
    private final ModelMapper mapper;

    @Autowired
    public CursoService(
            CursoRepo repository,
            TurmaRepo turmaRepository,
            TurmaPartRepo turmaPartRepository,
            FuncionarioRepo funcionarioRepo,
            ModelMapper mapper
    ) {
        this.repository = repository;
        this.turmaRepository = turmaRepository;
        this.turmaPartRepository = turmaPartRepository;
        this.funcionarioRepo = funcionarioRepo;
        this.mapper = mapper;
    }

    public List<GetCursoDTO> getAll() {
        return repository.getAll().stream().map(
                curso -> {
                    List<Turma> turmas = turmaRepository.getAllByCourseId(curso.getCodigo());
                    turmas.forEach(turma -> {
                        var funcionario = turmaPartRepository.getAllByTurmaId(turma.getCodigo()).stream().map(
                                turmaPart -> {
                                    var opt = funcionarioRepo.getById(turmaPart.getCodigoFuncionario());
                                    if (opt.isPresent())
                                        return opt.get();
                                    else
                                        return null;
                                }
                        ).toList();


                        turma.setParticipantes(funcionario);
                    });
                    curso.setTurmas(turmas);
                    curso.setQuantidadeTurmas(turmas.size());
                    return mapper.map(curso, GetCursoDTO.class);
                }
        ).toList();
    }

    public GetCursoDTO getById(Integer id) {
        var cursoOpt = repository.getById(id);

        if (cursoOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso com este id não encontrado");

        var curso = cursoOpt.get();
        List<Turma> turmas = turmaRepository.getAllByCourseId(curso.getCodigo());
        turmas.forEach(turma -> {
            var funcionario = turmaPartRepository.getAllByTurmaId(turma.getCodigo()).stream().map(
                    turmaPart -> {
                        var opt = funcionarioRepo.getById(turmaPart.getCodigoFuncionario());
                        if (opt.isPresent())
                            return opt.get();
                        else
                            return null;
                    }
            ).toList();


            turma.setParticipantes(funcionario);
        });
        curso.setTurmas(turmas);
        curso.setQuantidadeTurmas(turmas.size());

        return mapper.map(curso, GetCursoDTO.class);
    }

    public List<GetCursoDTO> getByName(String name) {
        return repository.getByName(name).stream().map(
                curso -> {
                    List<Turma> turmas = turmaRepository.getAllByCourseId(curso.getCodigo());
                    turmas.forEach(turma -> {
                        var funcionario = turmaPartRepository.getAllByTurmaId(turma.getCodigo()).stream().map(
                                turmaPart -> {
                                    var opt = funcionarioRepo.getById(turmaPart.getCodigoFuncionario());
                                    if (opt.isPresent())
                                        return opt.get();
                                    else
                                        return null;
                                }
                        ).toList();


                        turma.setParticipantes(funcionario);
                    });
                    curso.setTurmas(turmas);
                    curso.setQuantidadeTurmas(turmas.size());
                    return mapper.map(curso, GetCursoDTO.class);
                }
        ).toList();
    }

    public CreateCursoDTO createOne(CreateCursoDTO dto) {
        repository.insertOne(mapper.map(dto, Curso.class));

        return dto;
    }

    public UpdateCursoDTO updateOne(UpdateCursoDTO dto) {
        var id = dto.getCodigo();
        var opt = repository.getById(id);

        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "curso com este id não encontrado");

        if (dto.getNome() != null)
            repository.updateNome(dto.getNome(), id);
        if (dto.getDescricao() != null)
            repository.updateDescricao(dto.getDescricao(), id);
        if (dto.getDuracao() != null) {
            repository.updateDuracao(dto.getDuracao(), id);
        }

        return dto;
    }
}








