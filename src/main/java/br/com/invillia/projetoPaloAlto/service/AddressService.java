package br.com.invillia.projetoPaloAlto.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.com.invillia.projetoPaloAlto.mapper.AddressMapper;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.repository.AddressRepository;

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
