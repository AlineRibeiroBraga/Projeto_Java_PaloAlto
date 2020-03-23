package br.com.invillia.projetoPaloAlto.controller;

import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController()
@RequestMapping("/legal-entity")
public class LegalEntityController {

    @Autowired
    private LegalEntityService legalEntityService;

    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid LegalEntityDTO legalEntityDTO){

        long id = legalEntityService.insert(legalEntityDTO);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/legal-entity/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }
}
