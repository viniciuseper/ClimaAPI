package com.example.RestAPI.controller;
import com.example.RestAPI.ClimaResponeVO;
import com.example.RestAPI.model.ClimaEntity;
import com.example.RestAPI.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clima")
public class ClimaController {

    @Autowired
    private Service service;

    @PostMapping("/salvar")
    public void salvarClima(){
        service.salvarClima();
    }

    @PutMapping("/atualizarClima")
    public void autalizarClima(@RequestBody ClimaEntity clima) {
        service.salvarClima();
    }

    @DeleteMapping
    public void deletarClima(@RequestBody ClimaEntity clima){
        service.deletarClima(clima);
    }

    @GetMapping("/obterTodos")
    public List<ClimaEntity> obterTodos(){
        return service.obterTodos();
    }

    @GetMapping("/preverTempo")
    public ClimaResponeVO preverTempo(){
        return service.preverTempo();
    }

}
