package br.com.invillia.projetoPaloAlto.controller;

import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
}
