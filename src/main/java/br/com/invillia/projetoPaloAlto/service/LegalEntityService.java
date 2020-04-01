package br.com.invillia.projetoPaloAlto.service;

import java.util.List;

import br.com.invillia.projetoPaloAlto.controller.IndividualController;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import org.springframework.stereotype.Service;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;

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

            LegalEntity legalEntity = legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO);
            individualsDTOValidator(legalEntity.getIndividuals());

            System.out.println(legalEntity.toString());
            return legalEntityRepository.save(legalEntity).getId();

        }

        throw new LegalEntityException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

    private void individualsDTOValidator(List<Individual> individuals) {

        int lenght = individuals.size();
        Individual individual;

        for(int i=0; i < lenght; i++){

            individual = individualRepository.findByDocument(individuals.get(i).getDocument());

            if(individual != null){
                individuals.remove(i);
                individuals.add(i,individual);
            }
        }
    }

}
