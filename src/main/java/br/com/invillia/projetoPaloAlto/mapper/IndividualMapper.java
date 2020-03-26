package br.com.invillia.projetoPaloAlto.mapper;

import br.com.invillia.projetoPaloAlto.domain.Individual;
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
        individual.setAddress(addressMapper.listAddressDTOToListAddress(individualDTO.getAddress()));

        return individual;
    }

}
