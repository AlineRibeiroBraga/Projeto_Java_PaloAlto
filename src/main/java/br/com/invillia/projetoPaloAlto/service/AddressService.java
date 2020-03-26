package br.com.invillia.projetoPaloAlto.service;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.mapper.AddressMapper;
import br.com.invillia.projetoPaloAlto.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public List<AddressDTO> findByNumber(String number) {
        return addressMapper.listAddressToListAddressDTO(addressRepository.findByNumber(number));
    }
}
