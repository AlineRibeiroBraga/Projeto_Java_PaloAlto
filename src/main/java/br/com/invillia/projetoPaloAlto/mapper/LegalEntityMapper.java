package br.com.invillia.projetoPaloAlto.mapper;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;

@Component
public class LegalEntityMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IndividualMapper individualMapper;

    public LegalEntity legalEntityDTOTolegalEntity(LegalEntityDTO legalEntityDTO) {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setName(legalEntityDTO.getName());
        legalEntity.setDocument(legalEntityDTO.getDocument());
        legalEntity.setTradeName(legalEntityDTO.getTradeName());
        legalEntity.setCreatedAt(LocalDateTime.now());
        legalEntity.setAddresses(addressMapper.listAddressDTOToListAddress(legalEntityDTO.getAddressesDTO()));

        legalEntity = addressMapper.setAddressLegalEntity(legalEntity.getAddresses(),legalEntity);
        legalEntity = setIndividualsLegalEntity(legalEntity,legalEntityDTO.getIndividualsDTO());

        return legalEntity;
    }

    private LegalEntity setIndividualsLegalEntity(LegalEntity legalEntity, List<IndividualDTO> individualsDTO) {

        if(individualsDTO != null){
            legalEntity.setIndividuals(individualMapper.listIndividualDTOToListIndividual(individualsDTO));
        }

        return legalEntity;
    }

    public List<LegalEntity> listLegalEntityDTOToListLegalEntity(List<LegalEntityDTO> legalEntitiesDTO) {

        List<LegalEntity> legalEntities = new ArrayList<>();

        for(LegalEntityDTO legalEntityDTO : legalEntitiesDTO){
            legalEntities.add(legalEntityDTOTolegalEntity(legalEntityDTO));
        }

        return legalEntities;
    }

    public LegalEntityDTO legalEntityToLegalEntityDTO(LegalEntity legalEntity){

        LegalEntityDTO legalEntityDTO = new LegalEntityDTO();

        legalEntityDTO.setName(legalEntity.getName());
        legalEntityDTO.setDocument(legalEntity.getDocument());
        legalEntityDTO.setTradeName(legalEntity.getTradeName());

        return legalEntityDTO;
    }

    public List<LegalEntityDTO> listLegalEntityToListLegalEntityDTO(List<LegalEntity> legalEntities) {

        List<LegalEntityDTO> legalEntitiesDTO = new ArrayList<>();

        for(LegalEntity legalEntity : legalEntities){
            legalEntitiesDTO.add(legalEntityToLegalEntityDTO(legalEntity));
        }

        return legalEntitiesDTO;
    }
}
