package br.com.invillia.projetoPaloAlto.service;

import java.util.List;

import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
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
    IndividualService individualService;

    public Long insert(LegalEntityDTO legalEntityDTO) {

        if(!legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())){
//            if(IndividualsDTOValidator(legalEntityDTO.getIndividualsDTO())){

                LegalEntity legalEntity = legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO);

                return legalEntityRepository.save(legalEntity).getId();
//            }

//            throw new IndividualException(Messages.INDIVIDUAL_ENTITY_WAS_NOT_SAVED);
        }

        throw new LegalEntityException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

//    private Boolean IndividualsDTOValidator(List<IndividualDTO> individualsDTO) {
//
//
//    }
}
