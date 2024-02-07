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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private final CursoRepo cursoRepository;
    private final TurmaRepo turmaRepository;
    private final TurmaPartRepo turmaPartRepository;
    private final FuncionarioRepo funcionarioRepo;
    private final ModelMapper mapper;

    @Autowired
    public CursoService(
            CursoRepo repository,
            TurmaRepo turmaRepository,
            TurmaPartRepo turmaPartRepository,
            FuncionarioRepo funcionarioRepo
    ) {
        this.cursoRepository = repository;
        this.turmaRepository = turmaRepository;
        this.turmaPartRepository = turmaPartRepository;
        this.funcionarioRepo = funcionarioRepo;
        this.mapper = new ModelMapper();
    }

    public List<GetCursoDTO> getAll(Optional<Boolean> ativo) {
        return cursoRepository.getAll(ativo.orElse(true)).stream().map(
                curso -> {
                    List<Turma> turmas = turmaRepository.getAllByCourseId(curso.getCodigo());
                    turmas.forEach(turma -> {
                        var funcionariosDaTurma = turmaPartRepository.getAllByTurmaId(turma.getCodigo()).stream().map(
                                turmaPart -> {
                                    var opt = funcionarioRepo.getById(turmaPart.getCodigoFuncionario());
                                    if (opt.isPresent())
                                        return opt.get();
                                    else
                                        return null;
                                }
                        ).toList();


                        turma.setParticipantes(funcionariosDaTurma);
                    });
                    curso.setTurmas(turmas);
                    curso.setQuantidadeTurmas(turmas.size());
                    return mapper.map(curso, GetCursoDTO.class);
                }
        ).toList();
    }

    public GetCursoDTO getById(Integer id, Optional<Boolean> ativo) {
        var cursoOpt = cursoRepository.getById(id, ativo.orElse(true));

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

    public List<GetCursoDTO> getByName(String name, Optional<Boolean> ativo) {
        return cursoRepository.getByName(name, ativo.orElse(true)).stream().map(
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
        cursoRepository.insertOne(mapper.map(dto, Curso.class));

        return dto;
    }

    @Transactional
    public UpdateCursoDTO updateOne(UpdateCursoDTO dto) {
        var id = dto.getCodigo();
        var opt = cursoRepository.getById(id, true);

        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "curso com este id não encontrado");

        if (dto.getNome() != null)
            cursoRepository.updateNome(dto.getNome(), id);
        if (dto.getDescricao() != null)
            cursoRepository.updateDescricao(dto.getDescricao(), id);
        if (dto.getDuracao() != null) {
            cursoRepository.updateDuracao(dto.getDuracao(), id);
        }

        return dto;
    }

    @Transactional
    public GetCursoDTO deleteById(Integer cursoId, Optional<Boolean> force) {
        var opt = cursoRepository.getById(cursoId, true);
        if(opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso com este id nao encontrado: " + cursoId);

        var turmasEncontradas = turmaRepository.getAllByCourseId(cursoId);

        if (!turmasEncontradas.isEmpty())
            cursoRepository.updateStatus(false, cursoId);
        else
            cursoRepository.deleteById(cursoId);

        if(force.orElse(false)) {
            var turmas = turmaRepository.getAllByCourseId(cursoId);
            turmas.forEach(
                    turma -> turmaPartRepository.removeAllStudentsByTurmaId(turma.getCodigo())
            );
            turmaRepository.deleteAllByCourseId(cursoId);
        }


        return mapper.map(opt.get(), GetCursoDTO.class);
    }
}








