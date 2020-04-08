package br.com.invillia.projetoPaloAlto.service;

import java.util.List;
import java.util.Optional;

import br.com.invillia.projetoPaloAlto.controller.IndividualController;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import org.apache.catalina.startup.CopyParentClassLoaderRule;
import org.springframework.stereotype.Service;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;

import javax.swing.text.Document;

@Service
public class LegalEntityService {

    @Autowired
    LegalEntityRepository legalEntityRepository;

    @Autowired
    LegalEntityMapper legalEntityMapper;

    @Autowired
    IndividualRepository individualRepository;

    public Long insert(LegalEntityDTO legalEntityDTO) {

        if(!legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())){
            if(mainAddressValidator(legalEntityDTO.getAddressesDTO())){
                LegalEntity legalEntity = legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO);

                individualsDTOValidator(legalEntity.getIndividuals());

                return legalEntityRepository.save(legalEntity).getId();
            }

            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        throw new LegalEntityException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

    private boolean mainAddressValidator(List<AddressDTO> addressesDTO) {

        int main = 0;

        for(AddressDTO addressDTO :  addressesDTO){
            if(addressDTO.getMain()){
                ++main;
            }
        }

        return main == 1;
    }

    private void individualsDTOValidator(List<Individual> individuals) {

        if(individuals != null){
            int lenght = individuals.size();
            Optional<Individual> individual;

            for(int i=0; i < lenght; i++){

                individual = individualRepository.findByDocument(individuals.get(i).getDocument());

                if(!individual.isEmpty()){
                    individuals.remove(i);
                    individuals.add(i,individual.get());
                }
            }
        }
    }

    public LegalEntityDTO findById(Long id) {
        Optional<LegalEntity> optionalLegalEntity = Optional.of(legalEntityRepository.findById(id)
                .orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND)));

        return legalEntityMapper.legalEntityToLegalEntityDTO(optionalLegalEntity.get());
    }

    public LegalEntityDTO findByDocument(String document) {
        Optional<LegalEntity> optionalLegalEntity = Optional.of(legalEntityRepository.findByDocument(document)
                .orElseThrow(()->new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND)));

        return legalEntityMapper.legalEntityToLegalEntityDTO(optionalLegalEntity.get());
    }

    public String deleteByDocument(String document) {

        Optional<LegalEntity> optionalLegalEntity = Optional.of(legalEntityRepository.findByDocument(document).
                orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND)));

        deleteIndividuals(optionalLegalEntity.get());

        if(optionalLegalEntity.get().getActive()){
//            optionalLegalEntity.get().setActive(false);

            return optionalLegalEntity.get().getDocument();
        }

        throw new LegalEntityException(Messages.LEGAL_ENTITY_WAS_ALREADY_DELETED);
    }

    private void deleteIndividuals(LegalEntity legalEntity) {

        if(legalEntity.getIndividuals() != null){

            for (Individual individual : legalEntity.getIndividuals()){

                if(individualRepository.findLegalEntityById(individual.getId())){
                    System.out.println("entrou");
//                    individual.setActive(false);
                }
            }
        }
    }
}
