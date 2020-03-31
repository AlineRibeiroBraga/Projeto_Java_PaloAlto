package br.com.invillia.projetoPaloAlto;

import org.mockito.*;
import org.junit.Assert;
import java.time.LocalDateTime;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;

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
        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(false);
        when(legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO)).thenReturn(createLegalEntity());
        when(legalEntityRepository.save(Mockito.any(LegalEntity.class))).thenReturn(createLegalEntity());

        Long id = legalEntityService.insert(legalEntityDTO);

        Assert.assertNotNull(id);
        Mockito.verify(legalEntityRepository,times(1)).save(Mockito.any(LegalEntity.class));
    }

    @Test
    public void cadastroExistente(){

        legalEntityDTO = createLegalEntityDTO();

        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(true);

        Assertions.assertThrows(LegalEntityException.class, () -> legalEntityService.insert(legalEntityDTO));
    }
}
