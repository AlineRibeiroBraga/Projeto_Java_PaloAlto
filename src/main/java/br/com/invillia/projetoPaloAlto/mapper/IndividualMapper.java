package br.com.invillia.projetoPaloAlto.mapper;

import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class IndividualMapper {

    @Autowired
    private AddressMapper addressMapper;

    public Individual individualDTOToIndividual(IndividualDTO individualDTO) {

        Individual individual = new Individual();

        individual.setDocument(individualDTO.getDocument());
        individual.setMotherName(individualDTO.getMotherName());
        individual.setRg(individualDTO.getRg());
        individual.setBirthDate(individualDTO.getBirthDate());
        individual.setName(individualDTO.getName());
        individual.setCreatedAt(LocalDateTime.now());
        individual.setAddresses(addressMapper.listAddressDTOToListAddress(individualDTO.getAddressesDTO()));

//        addressMapper.setAddressIndividual(individual.getAddresses(),individual);

        for(Address address : individual.getAddresses()){
            address.setIndividual(individual);
        }
        return individual;
    }

}
