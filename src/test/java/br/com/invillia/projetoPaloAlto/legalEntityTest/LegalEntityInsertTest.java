package br.com.invillia.projetoPaloAlto.legalEntityTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.mapper.AddressMapper;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_CLASS)
public class LegalEntityInsertTest {

    private Faker faker;

    @Mock
    private LegalEntityRepository legalEntityRepository;

    @InjectMocks
    private LegalEntityService legalEntityService;

    @Spy
    @InjectMocks
    private LegalEntityMapper legalEntityMapper;

    @Mock
    private IndividualRepository individualRepository;

    @BeforeAll
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.faker = new Faker();
        this.legalEntityMapper = new LegalEntityMapper();
    }

    private LegalEntityDTO createLegalEntityDTO(){

        LegalEntityDTO legalEntityDTO = new LegalEntityDTO();

        legalEntityDTO.setName(faker.name().name());
        legalEntityDTO.setDocument(faker.number().digits(14));
        legalEntityDTO.setTradeName(faker.name().name());
        legalEntityDTO.setAddressesDTO(createListAddressDTO());

        for(AddressDTO addressDTO : legalEntityDTO.getAddressesDTO()){
            addressDTO.setLegalEntityDTO(legalEntityDTO);
        }

        return legalEntityDTO;
    }

    private List<AddressDTO> createListAddressDTO() {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        addressesDTO.add(createAddressDTO(true));
        addressesDTO.add(createAddressDTO(false));

        return addressesDTO;
    }

    private AddressDTO createAddressDTO(Boolean main) {

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setDistrict(faker.name().name());
        addressDTO.setNumber(faker.number().digit());
        addressDTO.setCity(faker.name().name());
        addressDTO.setState(faker.name().name());
        addressDTO.setZipCode(faker.code().gtin8());
        addressDTO.setMain(main);

        return addressDTO;
    }

    private List<IndividualDTO> createListIndividualsDTO() {

        List<IndividualDTO> individualsDTO = new ArrayList<>();

        individualsDTO.add(createIndividualDTO());
        individualsDTO.add(createIndividualDTO());

        return individualsDTO;
    }

    private IndividualDTO createIndividualDTO() {

        IndividualDTO individualDTO = new IndividualDTO();

        individualDTO.setName(faker.name().fullName());
        individualDTO.setDocument(faker.number().digits(11));
        individualDTO.setMotherName(faker.name().fullName());
        individualDTO.setRg(faker.number().digits(9));
        individualDTO.setBirthDate(LocalDate.now());
        individualDTO.setAddressesDTO(createListAddressDTO());

        for(AddressDTO addressDTO : individualDTO.getAddressesDTO()){
            addressDTO.setIndividualDTO(individualDTO);
        }

        return individualDTO;
    }

    private LegalEntity createLegalEntity(){
        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setName(faker.name().name());
        legalEntity.setCreatedAt(LocalDateTime.now());
        legalEntity.setId(1L);
        legalEntity.setDocument(faker.number().digits(14));
        legalEntity.setTradeName(faker.name().name());

        legalEntity.setAddresses(createListAddress());

        for(Address address : legalEntity.getAddresses()){
            address.setLegalEntity(legalEntity);
        }

        return legalEntity;
    }

    private List<Address> createListAddress() {
        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress(true, 1L));
        addresses.add(createAddress(false, 2L));

        return addresses;
    }

    private Address createAddress(Boolean main, Long id) {

        Address address = new Address();

        address.setId(id);
        address.setDistrict(faker.name().name());
        address.setMain(main);
        address.setNumber(faker.number().digit());
        address.setCity(faker.name().name());
        address.setState(faker.name().name());
        address.setZipCode(faker.code().gtin8());

        return address;
    }

    private Individual createIndividual() {

        Individual individual = new Individual();

        individual.setId(1L);
        individual.setBirthDate(LocalDate.now());
        individual.setDocument(faker.number().digits(11));
        individual.setMotherName(faker.name().name());
        individual.setRg(faker.number().digits(9));
        individual.setCreatedAt(LocalDateTime.now());
        individual.setName(faker.name().fullName());
        individual.setAddresses(createListAddress());

        for(Address address : individual.getAddresses()){
            address.setIndividual(individual);
        }

        return individual;
    }

    private List<Individual> createListIndividual() {

        List<Individual> individuals = new ArrayList<>();

        individuals.add(createIndividual());
        individuals.add(createIndividual());

        return individuals;
    }

    private LegalEntityDTO createLegalEntityDTOAddress() {

        LegalEntityDTO legalEntityDTO = new LegalEntityDTO();

        legalEntityDTO.setDocument(faker.number().digits(14));
        legalEntityDTO.setTradeName(faker.name().name());
        legalEntityDTO.setName(faker.name().name());
        legalEntityDTO.setAddressesDTO(createListAddressDTOAddress());

        for(AddressDTO addressDTO : legalEntityDTO.getAddressesDTO()){
            addressDTO.setLegalEntityDTO(legalEntityDTO);
        }

        return legalEntityDTO;
    }

    private List<AddressDTO> createListAddressDTOAddress() {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        addressesDTO.add(createAddressDTOAddress(true));
        addressesDTO.add(createAddressDTOAddress(true));

        return addressesDTO;
    }

    private AddressDTO createAddressDTOAddress(Boolean main) {

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setDistrict(faker.name().name());
        addressDTO.setMain(main);
        addressDTO.setNumber(faker.number().digit());
        addressDTO.setCity(faker.name().name());
        addressDTO.setState(faker.name().name());
        addressDTO.setZipCode(faker.code().gtin8());

        return addressDTO;
    }

    @Test
    public void InsertOkayWithIndividualsSaved(){

        LegalEntityDTO legalEntityDTO = createLegalEntityDTO();
        legalEntityDTO.setIndividualsDTO(createListIndividualsDTO());

        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(false);
        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(false);
        when(individualRepository.findByDocument(legalEntityDTO.getIndividualsDTO().get(1).getDocument()))
        .thenReturn(Optional.of(createIndividual()));
        when(legalEntityRepository.save(Mockito.any(LegalEntity.class))).thenReturn(createLegalEntity());

        Long id = legalEntityService.insert(legalEntityDTO);

        Assert.assertNotNull(id);
        Mockito.verify(legalEntityRepository,times(1)).save(Mockito.any(LegalEntity.class));
    }

    @Test
    public void InsertOkayWithIndividualsNotSave(){

        LegalEntityDTO legalEntityDTO = createLegalEntityDTO();
        legalEntityDTO.setIndividualsDTO(createListIndividualsDTO());

        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(false);
        when(legalEntityRepository.save(Mockito.any(LegalEntity.class))).thenReturn(createLegalEntity());
        when(individualRepository.findByDocument(legalEntityDTO.getIndividualsDTO().get(1).getDocument()))
                .thenReturn(null);
        Long id = legalEntityService.insert(legalEntityDTO);

        Assert.assertNotNull(id);
        Mockito.verify(legalEntityRepository,times(1)).save(Mockito.any(LegalEntity.class));
    }

    @Test
    public void insertDocumentAreadyExists(){

        LegalEntityDTO legalEntityDTO = createLegalEntityDTO();

        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(true);

        Assertions.assertThrows(LegalEntityException.class, ()-> legalEntityService.insert(legalEntityDTO));
    }

    @Test
    public void inserMainAddressValidator(){

        LegalEntityDTO legalEntityDTO = createLegalEntityDTOAddress();

        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(false);

        Assertions.assertThrows(AddressException.class, () -> legalEntityService.insert(legalEntityDTO));
    }

    @Test
    public void InsertOkayWithoutIndividuals(){

        LegalEntityDTO legalEntityDTO = createLegalEntityDTO();

        when(legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())).thenReturn(false);
        when(legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO)).thenReturn(createLegalEntity());
        when(legalEntityRepository.save(Mockito.any(LegalEntity.class))).thenReturn(createLegalEntity());

        Long id = legalEntityService.insert(legalEntityDTO);

        Assert.assertNotNull(id);
        Mockito.verify(legalEntityRepository,times(1)).save(Mockito.any(LegalEntity.class));
    }
}
