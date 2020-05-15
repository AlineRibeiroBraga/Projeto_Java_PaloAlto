package br.com.invillia.projetoPaloAlto.api.rest;

import br.com.invillia.projetoPaloAlto.anotation.IsCPF;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.response.Response;
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
    public Response insert(@RequestBody @Valid IndividualDTO individualDTO) {
        return individualService.insert(individualDTO);
    }

    @GetMapping("/document/{document}")
    public IndividualDTO findByDocument(@PathVariable @IsCPF String document) {
        return individualService.findByDocument(document);
    }

    @GetMapping("/{id}")
    public IndividualDTO Id(@PathVariable Long id) {
        return individualService.findById(id);
    }

    @DeleteMapping("/document/{document}")
    public Response deleteIndividualByDocument(@PathVariable String document) {
        return individualService.deleteByDocument(document);
    }

    @DeleteMapping("/{id}")
    public Response deleteIndividualById(@PathVariable Long id) {
        return individualService.deleteById(id);
    }

    @PutMapping("/document")
    public Response updateByDocument(@RequestBody IndividualDTO individualDTO) {
        return individualService.updateByDocument(individualDTO);
    }

    @PutMapping("/{id}")
    public Response updateById(@RequestBody IndividualDTO individualDTO, @PathVariable Long id){
        return individualService.updateById(id,individualDTO);
    }
}

