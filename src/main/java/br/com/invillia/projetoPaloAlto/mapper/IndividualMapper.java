package br.com.invillia.projetoPaloAlto.mapper;

import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.dtoUpdate.IndividualDTOUpdate;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        individual.setActive(individualDTO.getActive());
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
        individualDTO.setActive(individual.getActive());
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

    public void update(Individual individual, IndividualDTOUpdate individualDTOUpdate) {

        individual.setName(individualDTOUpdate.getName());
        individual.setMotherName(individualDTOUpdate.getMotherName());
        individual.setUpdatedAt(LocalDateTime.now());

        addressMapper.update(individual.getAddresses(),individualDTOUpdate.getAddressesDTO());

        for(Address address : individual.getAddresses()){
            if(address.getIndividual() == null){
                address.setIndividual(individual);
            }
        }
    }
}
