package br.com.invillia.projetoPaloAlto.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;

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

        addressMapper.setAddressIndividual(individual.getAddresses(),individual);

        return individual;
    }

    public List<Individual> listIndividualDTOToListIndividual(List<IndividualDTO> individualsDTO) {

        List<Individual> individuals = new ArrayList<>();

        for(IndividualDTO individualDTO : individualsDTO){
            individuals.add(individualDTOToIndividual(individualDTO));
        }

        return individuals;
    }


    public IndividualDTO individualToIndividualDTO(Individual individual) {

        IndividualDTO individualDTO = new IndividualDTO();

        individualDTO.setName(individual.getName());
        individualDTO.setRg(individual.getRg());
        individualDTO.setMotherName(individual.getMotherName());
        individualDTO.setDocument(individual.getDocument());
        individualDTO.setBirthDate(individual.getBirthDate());
        individualDTO.setAddressesDTO(addressMapper.listAddressToListAddressDTO(individual.getAddresses()));

        addressMapper.setAddressDTOIndividualDTO(individualDTO.getAddressesDTO(),individualDTO);

        return individualDTO;
    }

    public List<IndividualDTO> listIndividualToListIndividualDTO(List<Individual> individuals) {

        List<IndividualDTO> individualsDTO = new ArrayList<>();

        for(Individual individual : individuals){
            individualsDTO.add(individualToIndividualDTO(individual));
        }

        return individualsDTO;
    }
}
