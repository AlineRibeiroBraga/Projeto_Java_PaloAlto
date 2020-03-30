package br.com.invillia.projetoPaloAlto.service;

import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import org.springframework.stereotype.Service;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;

import java.util.List;

@Service
public class LegalEntityService {

    @Autowired
    LegalEntityRepository legalEntityRepository;

    @Autowired
    LegalEntityMapper legalEntityMapper;

    public Long insert(LegalEntityDTO legalEntityDTO) {

        if(!legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())){
            LegalEntity legalEntity = legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO);

            return legalEntityRepository.save(legalEntity).getId();
        }

        throw new LegalEntityException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

    public List<LegalEntityDTO> findAll() {

        List<LegalEntity> legalEntities = legalEntityRepository.findAll();

        return legalEntityMapper.listLegalEntityToListLegalEntityDTO(legalEntities);
    }
}
