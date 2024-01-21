package com.teste.treinamentos.service;

import com.teste.treinamentos.dto.funcionario.GetFuncionarioDTO;
import com.teste.treinamentos.dto.turma.CreateTurmaDTO;
import com.teste.treinamentos.dto.turma.GetTurmaDTO;
import com.teste.treinamentos.dto.turma_part.AddTurmaParticipanteDTO;
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
public class TurmaService {
    private final TurmaRepo turmaRepository;
    private final TurmaPartRepo turmaPartRepository;
    private final FuncionarioRepo funcionarioRepository;
    private final CursoRepo cursoRepository;
    private final ModelMapper mapper;

    @Autowired
    public TurmaService(
            TurmaRepo turmaRepository,
            TurmaPartRepo turmaPartRepository,
            FuncionarioRepo funcionarioRepository,
            CursoRepo cursoRepository,
            ModelMapper mapper
            ) {
        this.turmaRepository = turmaRepository;
        this.turmaPartRepository = turmaPartRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.cursoRepository = cursoRepository;
        this.mapper = mapper;
    }

    public List<GetTurmaDTO> getAll() {
        return turmaRepository.getAll().stream().map(
                i -> mapper.map(i, GetTurmaDTO.class)
        ).toList();
    }

    public List<GetTurmaDTO> getAllTurmasByCourseId(Integer courseId) {
        var opt = cursoRepository.getById(courseId);

        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Codigo de curso não existente");

        return turmaRepository.getByCourseId(courseId).stream().map(
                i -> mapper.map(i, GetTurmaDTO.class)
        ).toList();
    }


    public CreateTurmaDTO createOne(CreateTurmaDTO dto) {
        var opt = cursoRepository.getById(dto.getCursoCodigo());

        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "tentando criar turma para um código de curso não existente");

        turmaRepository.insertOne(mapper.map(dto, Turma.class));

        return dto;
    }

    public AddTurmaParticipanteDTO addTurmaParticipante(AddTurmaParticipanteDTO dto) {
        var opt = turmaPartRepository.checkIfTurmaContainsFuncionario(dto.getTurmaId(), dto.getFuncionarioId());
        if (opt.isPresent())
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Funcionario ja cadastrado nesta turma: " + dto.getTurmaId()
            );

        turmaPartRepository.insertFuncionario(dto.getFuncionarioId(), dto.getTurmaId());

        return dto;
    }

    public GetTurmaDTO deleteTurmaById(Integer turmaId) {
        var opt = turmaRepository.getById(turmaId);

        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "turma com este Id não encontrada");

        turmaPartRepository.removeAllByTurmaId(turmaId);
        turmaRepository.deleteOneById(turmaId);

        return mapper.map(opt.get(), GetTurmaDTO.class);
    }

    public GetFuncionarioDTO removeFuncionarioFromTurma(Integer funcionarioId, Integer turmaId) {
        var opt = funcionarioRepository.getById(funcionarioId);
        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "funcionario com este id não existe");

        turmaPartRepository.removeFuncionarioFromTurma(funcionarioId, turmaId);

        return mapper.map(opt.get(), GetFuncionarioDTO.class);
    }
}
