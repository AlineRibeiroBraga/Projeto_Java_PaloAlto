package br.com.invillia.projetoPaloAlto.controller;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.service.AddressService;
import com.github.javafaker.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{number}")
    public List<AddressDTO> findByNumber(@PathVariable String number ){
        return addressService.findByNumber(number);
    }
}
