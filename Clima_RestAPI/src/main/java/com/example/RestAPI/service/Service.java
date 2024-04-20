package com.example.RestAPI.service;
import com.example.RestAPI.ClimaResponeVO;
import com.example.RestAPI.model.ClimaEntity;
import com.example.RestAPI.repository.ClimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ClimaRepository climaRepository;

    public ClimaResponeVO preverTempo() {
        String apiUrl = "https://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=9fe25332679ebce952fdd9f7f9a83c3e";

        RestTemplate restTemplate = new RestTemplate();

        ClimaResponeVO[] response = restTemplate.getForObject(apiUrl, ClimaResponeVO[].class);

        return Objects.nonNull(response) ? particionarDados(response) : null;
    }

    public ClimaResponeVO particionarDados(ClimaResponeVO[] array){
        ClimaResponeVO clima = new ClimaResponeVO();
        clima.setCountry(array[0].getCountry());
        clima.setText(array[0].getText());
        clima.setDate(array[0].getDate());
        return clima;
    }

    public void deletarClima(ClimaEntity clima){
        climaRepository.delete(clima);
    }

    public void salvarClima(){
        ClimaResponeVO climaVO = this.preverTempo();
        ClimaEntity climaEntity = this.preparaObjClima(climaVO);
        this.climaRepository.save(climaEntity);
    }

    private ClimaEntity preparaObjClima(ClimaResponeVO climaVo){
        ClimaEntity clima = new ClimaEntity();
        clima.setNome(climaVo.getCountry());
        clima.setText(climaVo.getText());
        clima.setData(clima.getData());
        return  clima;
    }

    public List<ClimaEntity> obterTodos(){
        return this.climaRepository.findAll();
    }

}
