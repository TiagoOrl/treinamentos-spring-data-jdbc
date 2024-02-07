package com.teste.treinamentos.service;

import com.teste.treinamentos.dto.funcionario.GetFuncionarioDTO;
import com.teste.treinamentos.dto.turma.CreateTurmaDTO;
import com.teste.treinamentos.dto.turma.GetTurmaDTO;
import com.teste.treinamentos.dto.turma.PeriodoDTO;
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
            CursoRepo cursoRepository
            ) {
        this.turmaRepository = turmaRepository;
        this.turmaPartRepository = turmaPartRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.cursoRepository = cursoRepository;
        this.mapper = new ModelMapper();
    }

    public List<GetTurmaDTO> getAll() {
        return turmaRepository.getAll().stream().map(
                turma -> buildTurmaDTO(turma)
        ).toList();
    }

    public List<GetTurmaDTO> getAllByPeriod(PeriodoDTO dto) {
        return turmaRepository.getAllByPeriod(dto.getInicio(), dto.getFim())
                .stream().map(
                        turma -> buildTurmaDTO(turma)
                ).toList();
    }

    public List<GetTurmaDTO> getAllTurmasByCourseId(Integer courseId) {
        var opt = cursoRepository.getById(courseId, true);

        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Codigo de curso não existente");

        return turmaRepository.getAllByCourseId(courseId).stream().map(
                turma -> buildTurmaDTO(turma)
        ).toList();
    }

    public List<GetTurmaDTO> getByCourseIdAndStudentId(Integer cursoId, Integer funcionarioId) {

        var turmas = turmaRepository.getAllByCourseIdAndFuncionarioId(cursoId, funcionarioId).stream().map(
                turma -> mapper.map(turma, GetTurmaDTO.class)
        ).toList();

        if (turmas.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi encontrado turmas para o funcionario: " + funcionarioId
                    + "com o curso: " + cursoId
            );

        return turmas;
    }


    public CreateTurmaDTO createOne(CreateTurmaDTO dto) {
        var opt = cursoRepository.getById(dto.getCursoCodigo(), true);

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

        turmaPartRepository.removeAllStudentsByTurmaId(turmaId);
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


    private GetTurmaDTO buildTurmaDTO(Turma turma) {
        var alunosDaTurma = turmaPartRepository.getAllByTurmaId(turma.getCodigo())
                .stream().map(turmaPart -> {
                    var funcionarioOpt = funcionarioRepository.getById(turmaPart.getCodigoFuncionario());
                    if (funcionarioOpt.isPresent())
                        return funcionarioOpt.get();
                    else
                        return null;
                }).toList();
        turma.setParticipantes(alunosDaTurma);
        turma.setQuantidadeParticipantes(alunosDaTurma.size());
        return mapper.map(turma, GetTurmaDTO.class);
    }

    public GetFuncionarioDTO removeStudentFromAllTurmas(Integer id) {
        var opt = funcionarioRepository.getById(id);

        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                    , "Funcionario com este id: " + id + " não encontrado"
            );
        turmaPartRepository.removeStudentFromAllTurmas(id);

        return mapper.map(opt.get(), GetFuncionarioDTO.class);
    }
}
