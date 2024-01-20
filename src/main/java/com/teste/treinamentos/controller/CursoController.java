package com.teste.treinamentos.controller;

import com.teste.treinamentos.dto.curso.CreateCursoDTO;
import com.teste.treinamentos.dto.curso.GetCursoDTO;
import com.teste.treinamentos.dto.curso.UpdateCursoDTO;
import com.teste.treinamentos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/curso")
public class CursoController {
    private final CursoService service;

    @Autowired
    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<GetCursoDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("one")
    public GetCursoDTO getById(@RequestParam Integer id) {
        return service.getById(id);
    }

    @GetMapping("get-by-name")
    public List<GetCursoDTO> getByName(@RequestParam String name) {
        return service.getByName(name);
    }

    @PostMapping
    public CreateCursoDTO createOne(@Valid @RequestBody CreateCursoDTO dto) {
        return service.createOne(dto);
    }

    @PutMapping
    public UpdateCursoDTO updateOne(@Valid @RequestBody UpdateCursoDTO dto) {
        return service.updateOne(dto);
    }
}
