package com.algaworks.crm.controller;

import com.algaworks.crm.model.Cliente;
import com.algaworks.crm.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> busca(@PathVariable(value = "id") Long id){

        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (!optionalCliente.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found!");
        }

            return ResponseEntity.status(HttpStatus.OK).body(optionalCliente.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
