package br.com.invillia.projetoPaloAlto.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import br.com.invillia.projetoPaloAlto.anotation.IsCPF;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.service.IndividualService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController()
@RequestMapping("/individual")
public class IndividualController {

    @Autowired
    private IndividualService individualService;

    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid IndividualDTO individualDTO){

        Long id = individualService.insert(individualDTO);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/individual/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/document/{document}")
    public IndividualDTO findByDocument(@PathVariable @IsCPF String document){
        return individualService.findByDocument(document);
    }

    @GetMapping("/{id}")
    public IndividualDTO findByDocument(@PathVariable Long id){
        return individualService.findById(id);
    }
}
