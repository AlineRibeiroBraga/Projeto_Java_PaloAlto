package br.com.invillia.projetoPaloAlto.service;

import org.springframework.stereotype.Service;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import br.com.invillia.projetoPaloAlto.domain.LegalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;

@Service
public class LegalEntityService {

    @Autowired
    LegalEntityRepository legalEntityRepository;

    @Autowired
    LegalEntityMapper legalEntityMapper;

    public Long insert(LegalEntityDTO legalEntityDTO) {

        if(legalEntityRepository.findByDocument(legalEntityDTO.getDocument()).isEmpty()){
            LegalEntity legalEntity = legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO);

            return legalEntityRepository.save(legalEntity).getId();
        }

        throw new LegalEntityException(Messages.DOCUMENT_ALREADY_EXISTS);
    }
}
