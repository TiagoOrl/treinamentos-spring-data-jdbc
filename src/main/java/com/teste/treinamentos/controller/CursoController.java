package com.teste.treinamentos.controller;

import com.teste.treinamentos.dto.curso.CreateCursoDTO;
import com.teste.treinamentos.dto.curso.GetCursoDTO;
import com.teste.treinamentos.dto.curso.UpdateCursoDTO;
import com.teste.treinamentos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/curso")
public class CursoController {
    private final CursoService service;

    @Autowired
    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<GetCursoDTO> getAll(
            @RequestParam Optional<Boolean> ativo
    ) {
        return service.getAll(ativo);
    }

    @GetMapping("one")
    public GetCursoDTO getById(
            @RequestParam Integer id,
            @RequestParam Optional<Boolean> ativo
    ) {
        return service.getById(id, ativo);
    }

    @GetMapping("get-by-name")
    public List<GetCursoDTO> getByName(
            @RequestParam String name,
            @RequestParam Optional<Boolean> ativo
    ) {
        return service.getByName(name, ativo);
    }

    @PostMapping
    public CreateCursoDTO createOne(@Valid @RequestBody CreateCursoDTO dto) {
        return service.createOne(dto);
    }

    @PutMapping
    public UpdateCursoDTO updateOne(@Valid @RequestBody UpdateCursoDTO dto) {
        return service.updateOne(dto);
    }

    @DeleteMapping("remove")
    public GetCursoDTO deleteCourseById(
            @RequestParam Integer id,
            @RequestParam Optional<Boolean> force
    ) {
        return service.deleteById(id, force);
    }

}
