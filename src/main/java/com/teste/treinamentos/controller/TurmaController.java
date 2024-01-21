package com.teste.treinamentos.controller;

import com.teste.treinamentos.dto.funcionario.GetFuncionarioDTO;
import com.teste.treinamentos.dto.turma.CreateTurmaDTO;
import com.teste.treinamentos.dto.turma.GetTurmaDTO;
import com.teste.treinamentos.dto.turma_part.AddTurmaParticipanteDTO;
import com.teste.treinamentos.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turma")
public class TurmaController {
    private final TurmaService service;

    @Autowired
    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<GetTurmaDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("by-course-id")
    public List<GetTurmaDTO> getByCourseId(@RequestParam Integer id) {
        return service.getAllTurmasByCourseId(id);
    }

    @GetMapping("by-course-and-funcionario-id")
    public List<GetTurmaDTO> getByCourseIdAndStudentId(
            @RequestParam Integer cursoId,
            @RequestParam Integer funcionarioId
    ) {
        return service.getByCourseIdAndStudentId(cursoId, funcionarioId);
    }

    @PostMapping("create-turma")
    public CreateTurmaDTO createOne(@Valid @RequestBody CreateTurmaDTO dto) {
        return service.createOne(dto);
    }

    @PostMapping("add-student")
    public AddTurmaParticipanteDTO addTurmaParticipante(
            @Valid @RequestBody AddTurmaParticipanteDTO dto
    ) {
        return service.addTurmaParticipante(dto);
    }

    @DeleteMapping("remove-student")
    public GetFuncionarioDTO removeFuncionarioById(
            @RequestParam(name = "funcionario_id") Integer funcionarioId,
            @RequestParam(name = "turma_id") Integer turmaId
    ) {
        return service.removeFuncionarioFromTurma(funcionarioId, turmaId);
    }

    @DeleteMapping("remove-turma")
    public GetTurmaDTO deleteTurmaById(@RequestParam(name = "turma_id") Integer id) {
        return service.deleteTurmaById(id);
    }
}
