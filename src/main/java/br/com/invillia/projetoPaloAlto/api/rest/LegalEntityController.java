package br.com.invillia.projetoPaloAlto.api.rest;

import br.com.invillia.projetoPaloAlto.anotation.IsCNPJ;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
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

    @GetMapping("/document/{document}")
    public LegalEntityDTO findByDocument(@PathVariable  @IsCNPJ String document){
        return legalEntityService.findByDocument(document);
    }

    @GetMapping("/{id}")
    public LegalEntityDTO findByDocument(@PathVariable Long id){
        return legalEntityService.findById(id);
    }

    @DeleteMapping("/document/{document}")
    public String deleteByDocument(@PathVariable String document){
        return legalEntityService.deleteByDocument(document);
    }

    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id){
        return legalEntityService.deleteById(id);
    }

    @PutMapping("/document")
    public String updateByDocument(@RequestBody LegalEntityDTO legalEntityDTO){
        return legalEntityService.updateByDocument(legalEntityDTO);
    }

    @PutMapping("/{id}")
    public Long updateById(@RequestBody LegalEntityDTO legalEntityDTO, @PathVariable Long id){
        return legalEntityService.updateById(id,legalEntityDTO);
    }
}
