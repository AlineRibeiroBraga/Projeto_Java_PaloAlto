package br.com.invillia.projetoPaloAlto.API.rest;

import br.com.invillia.projetoPaloAlto.anotation.IsCPF;
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
public class IndividualController{

    @Autowired
    private IndividualService individualService;

    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid IndividualDTO individualDTO) {

        Long id = individualService.insert(individualDTO);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/individual/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/document/{document}")
    public IndividualDTO findByDocument(@PathVariable @IsCPF String document) {
        return individualService.findByDocument(document);
    }

    @GetMapping("/{id}")
    public IndividualDTO findByDocument(@PathVariable Long id) {
        return individualService.findById(id);
    }

    @DeleteMapping("/document/{document}")
    public String deleteIndividualByDocument(@PathVariable String document) {
        return individualService.deleteByDocument(document);
    }

    @DeleteMapping("/{id}")
    public Long deleteIndividualById(@PathVariable Long id) {
        return individualService.deleteById(id);
    }

    @PutMapping("/document")
    public String updateByDocument(@RequestBody IndividualDTO individualDTO) {
        return individualService.updateByDocument(individualDTO);
    }

    @PutMapping("/{id}")
    public Long updateById(@RequestBody IndividualDTO individualDTO, @PathVariable Long id){
        return individualService.updateById(id,individualDTO);
    }
}

