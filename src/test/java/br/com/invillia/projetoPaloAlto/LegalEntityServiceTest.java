package br.com.invillia.projetoPaloAlto;


import br.com.invillia.projetoPaloAlto.domain.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_CLASS)
public class LegalEntityServiceTest {

    private Faker faker = new Faker();

    @Mock
    private LegalEntityRepository legalEntityRepository;

    @Spy
    @InjectMocks
    private LegalEntityService legalEntityService;

    private LegalEntityDTO legalEntityDTO;
    private LegalEntity legalEntity;

    @Mock
    private LegalEntityMapper legalEntityMapper;

    @BeforeAll
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.legalEntityDTO = new LegalEntityDTO();
        this.legalEntity = new LegalEntity();
    }

    private LegalEntityDTO createLegalEntityDTO(){

        legalEntityDTO.setDocument(faker.number().digits(14));
        legalEntityDTO.setTradeName(faker.name().name());
        legalEntityDTO.setName(faker.name().name());

        return legalEntityDTO;
    }

    private LegalEntity createLegalEntity(){

        legalEntity.setDocument(faker.number().digits(14));
        legalEntity.setTradeName(faker.name().name());
        legalEntity.setName(faker.name().name());
        legalEntity.setCreatedAt(LocalDateTime.now());
        legalEntity.setId(1L);

        return legalEntity;
    }

    @Test
    public void cadastroOk(){

        legalEntityDTO = createLegalEntityDTO();
        when(legalEntityRepository.findByDocument(legalEntityDTO.getDocument())).thenReturn(Optional.empty());
        when(legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO)).thenReturn(createLegalEntity());
        when(legalEntityRepository.save(Mockito.any(LegalEntity.class))).thenReturn(createLegalEntity());

        Long id = legalEntityService.insert(legalEntityDTO);

        Assert.assertNotNull(id);
        Mockito.verify(legalEntityRepository,times(1)).save(Mockito.any(LegalEntity.class));
    }

    @Test
    public void cadastroExistente(){

        legalEntityDTO = createLegalEntityDTO();

        when(legalEntityRepository.findByDocument(legalEntityDTO.getDocument())).
                thenReturn(Optional.ofNullable(createLegalEntity()));

        Assertions.assertThrows(LegalEntityException.class, () -> legalEntityService.insert(legalEntityDTO));
    }
}
