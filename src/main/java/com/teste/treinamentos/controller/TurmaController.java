package com.teste.treinamentos.controller;

import com.teste.treinamentos.dto.funcionario.GetFuncionarioDTO;
import com.teste.treinamentos.dto.turma.CreateTurmaDTO;
import com.teste.treinamentos.dto.turma.GetTurmaDTO;
import com.teste.treinamentos.dto.turma_part.AddTurmaParticipanteDTO;
import com.teste.treinamentos.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turma")
public class TurmaController {
    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<GetTurmaDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public CreateTurmaDTO createOne(@Valid @RequestBody CreateTurmaDTO dto) {
        return service.createOne(dto);
    }

    @PostMapping
    public AddTurmaParticipanteDTO addTurmaParticipante(
            @Valid @RequestBody AddTurmaParticipanteDTO dto
    ) {
        return service.addTurmaParticipante(dto);
    }

    @PutMapping("remove-student")
    public GetFuncionarioDTO removeFuncionarioById(
            @RequestParam(name = "funcionario_id") Integer funcionarioId,
            @RequestParam(name = "turma_id") Integer turmaId
    ) {
        return service.removeFuncionarioFromTurma(funcionarioId, turmaId);
    }

    @DeleteMapping
    public GetTurmaDTO deleteTurmaById(@RequestParam(name = "turma_id") Integer id) {
        return service.deleteTurmaById(id);
    }
}
