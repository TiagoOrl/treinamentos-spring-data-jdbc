package com.teste.treinamentos.service;

import com.teste.treinamentos.dto.funcionario.CreateFuncionarioDTO;
import com.teste.treinamentos.dto.funcionario.GetFuncionarioDTO;
import com.teste.treinamentos.dto.funcionario.UpdateFuncionarioDTO;
import com.teste.treinamentos.entity.Funcionario;
import com.teste.treinamentos.repository.funcionario.FuncionarioRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepo repository;
    private final ModelMapper mapper;

    public FuncionarioService(FuncionarioRepo repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    public List<GetFuncionarioDTO> getAll(Optional<Boolean> active) {
        if (active.isEmpty())
            return repository.getAll().stream().map(
                    i ->  mapper.map(i, GetFuncionarioDTO.class)
            ).toList();
        else
            return repository.getAllByActive(active.get()).stream().map(
                    i -> mapper.map(i, GetFuncionarioDTO.class)
            ).toList();
    }

    public GetFuncionarioDTO getById(Integer id) {
        var opt = repository.getById(id);
        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "funcionario nao encontrado");

        return mapper.map(opt.get(), GetFuncionarioDTO.class);
    }

    public List<GetFuncionarioDTO> getByName(String name) {
        return repository.getByName(name).stream().map(
                i -> mapper.map(i, GetFuncionarioDTO.class)
        ).toList();
    }

    public CreateFuncionarioDTO insertOne(CreateFuncionarioDTO dto) {
        repository.insertOne(mapper.map(dto, Funcionario.class));
        return dto;
    }

    public UpdateFuncionarioDTO updateById(UpdateFuncionarioDTO dto) {
        var opt = repository.getById(dto.getCodigo());
        if (opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "funcionario com este codigo nao encontrado");

        if (dto.getNome() != null)
            repository.updateName(dto.getNome(), dto.getCodigo());
        if (dto.getCpf() != null)
            repository.updateCpf(dto.getCpf(), dto.getCodigo());
        if (dto.getCargo() != null)
            repository.updateCargo(dto.getCargo(), dto.getCodigo());
        if (dto.getNascimento() != null)
            repository.updateNascimento(dto.getNascimento(), dto.getCodigo());
        if (dto.getAdmissao() != null)
            repository.updateAdmissao(dto.getAdmissao(), dto.getCodigo());

        return dto;
    }

}
