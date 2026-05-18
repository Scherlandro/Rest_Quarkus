package com.walletapi.resource;

import java.util.Arrays;
import java.util.List;

import com.walletapi.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walletapi.entity.Pais;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisRepository repository;
    private String nome_pais;


    @GetMapping(path = "/all")
    public ResponseEntity<List<Pais>>listarPaises(){
        return ResponseEntity.ok(repository.findAll());
    }


        @GetMapping(path = "/{id_paises}")
        public ResponseEntity consultarPaisPorId(@PathVariable("id_paises") Integer id_paises){
            return repository.findById(id_paises).map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        }


    @GetMapping(value = "/buscarPorNomeDoPais")
    public ResponseEntity consultarPaisPorNome(@RequestParam(name ="nome_pais") String nome_pais){
        return Arrays.stream(repository.busarPorNome_pais(nome_pais).toArray()).findFirst().map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

}


