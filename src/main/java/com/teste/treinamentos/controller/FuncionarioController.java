package com.teste.treinamentos.controller;

import com.teste.treinamentos.dto.funcionario.CreateFuncionarioDTO;
import com.teste.treinamentos.dto.funcionario.GetFuncionarioDTO;
import com.teste.treinamentos.dto.funcionario.UpdateFuncionarioDTO;
import com.teste.treinamentos.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {
    private final FuncionarioService service;

    @Autowired
    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<GetFuncionarioDTO> getAll(@RequestParam Boolean active) {
        return service.getAll(active);
    }

    @GetMapping("/client")
    public GetFuncionarioDTO getById(@RequestParam Integer id) {
        return service.getById(id);
    }

    @GetMapping("/get-by-name")
    public List<GetFuncionarioDTO> getByName(@RequestParam String name) {
        return service.getByName(name);
    }

    @PostMapping
    public CreateFuncionarioDTO createNew(@Valid @RequestBody CreateFuncionarioDTO dto) {
        return service.insertOne(dto);
    }

    @PutMapping
    public UpdateFuncionarioDTO updateById(@Valid @RequestBody UpdateFuncionarioDTO dto) {
        return service.updateById(dto);
    }
}
