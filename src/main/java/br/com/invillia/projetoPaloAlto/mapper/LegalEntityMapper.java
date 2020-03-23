package br.com.invillia.projetoPaloAlto.mapper;

import br.com.invillia.projetoPaloAlto.domain.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class LegalEntityMapper {

    public LegalEntity legalEntityDTOTolegalEntity(LegalEntityDTO legalEntityDTO) {

        LegalEntity legalEntity = new LegalEntity();
        if(legalEntity != null){
            legalEntity.setName(legalEntityDTO.getName());
            legalEntity.setDocument(legalEntityDTO.getDocument());
            legalEntity.setTradeName(legalEntityDTO.getTradeName());
            legalEntity.setCreatedAt(LocalDateTime.now());

            return legalEntity;
        }

        return null;
    }
}
