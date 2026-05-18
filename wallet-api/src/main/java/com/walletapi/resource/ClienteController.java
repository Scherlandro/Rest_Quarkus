package com.walletapi.resource;

import java.util.List;

import com.walletapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletapi.entity.Cliente;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/{id_cliente}")
    public ResponseEntity consultar(@PathVariable("id_cliente") Integer id_cliente){
        return repository.findById(id_cliente).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping(path = "/salvar")
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping(path = "/editar")
    public Cliente editar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @DeleteMapping(path = "/delete/{id_cliente}")
    public void excluir(@PathVariable("id_cliente") Integer id_cliente){
        repository.deleteById(id_cliente);
    }
}

