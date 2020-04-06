package br.com.invillia.projetoPaloAlto.legalEntityTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.mapper.AddressMapper;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_CLASS)
public class LegalEntityFindByTest {

    private Faker faker;

    @Mock
    private LegalEntityRepository legalEntityRepository;

    @Spy
    @InjectMocks
    private LegalEntityService legalEntityService;

    @Spy
    @InjectMocks
    private LegalEntityMapper legalEntityMapper;

    @Spy
    private AddressMapper addressMapper;

    @Spy
    private IndividualMapper individualMapper;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.faker = new Faker();
    }

    private LegalEntity createLegalEntity() {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setId(1L);
        legalEntity.setDocument(faker.number().digits(14));
        legalEntity.setTradeName(faker.name().name());
        legalEntity.setName(faker.name().fullName());
        legalEntity.setAddresses(createListAddress());
        legalEntity.setIndividuals(new ArrayList<>());

        for(Address address : legalEntity.getAddresses()){
            address.setLegalEntity(legalEntity);
        }

        return legalEntity;
    }

    private List<Address> createListAddress() {

        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress(true));
        addresses.add(createAddress(false));

        return addresses;
    }

    private Address createAddress(Boolean main) {

        Address address = new Address();

        address.setZipCode(faker.address().zipCode());
        address.setCity(faker.address().city());
        address.setNumber(faker.number().digit());
        address.setMain(main);
        address.setDistrict(faker.address().fullAddress());
        address.setState(faker.address().state());

        return address;
    }

    private LegalEntityDTO createLegalEntityDTO() {

        LegalEntityDTO legalEntityDTO = new LegalEntityDTO();

        legalEntityDTO.setDocument(faker.number().digits(14));
        legalEntityDTO.setTradeName(faker.name().name());
        legalEntityDTO.setName(faker.name().fullName());
        legalEntityDTO.setAddressesDTO(createListAddressDTO());

        for(AddressDTO addressDTO : legalEntityDTO.getAddressesDTO()){
            addressDTO.setLegalEntityDTO(legalEntityDTO);
        }

        return legalEntityDTO;
    }

    private List<AddressDTO> createListAddressDTO() {

        List<AddressDTO> addresses = new ArrayList<>();

        addresses.add(createAddressDTO(true));
        addresses.add(createAddressDTO(false));

        return addresses;
    }

    private AddressDTO createAddressDTO(Boolean main) {

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setZipCode(faker.address().zipCode());
        addressDTO.setCity(faker.address().city());
        addressDTO.setNumber(faker.number().digit());
        addressDTO.setMain(main);
        addressDTO.setDistrict(faker.address().fullAddress());
        addressDTO.setState(faker.address().state());

        return addressDTO;
    }

    private List<Individual> createListIndividual() {
        List<Individual> individuals = new ArrayList<>();

        individuals.add(createIndividual(1L));
        individuals.add(createIndividual(2L));

        return individuals;
    }

    private Individual createIndividual(Long id) {

        Individual individual = new Individual();

        individual.setId(id);
        individual.setBirthDate(LocalDate.now());
        individual.setMotherName(faker.name().name());
        individual.setDocument(faker.number().digits(11));
        individual.setRg(faker.number().digits(9));
        individual.setCreatedAt(LocalDateTime.now());
        individual.setName(faker.name().name());
        individual.setAddresses(createAddress(true));

        for(Address address : individual.getAddresses()){
            address.setIndividual(individual);
        }

        return individual;
    }

    @Test
    public void findByIdExistsWithoutIndividuals(){

        LegalEntity legalEntity = createLegalEntity();

        when(legalEntityRepository.findById(1L)).thenReturn(Optional.of(legalEntity));

        LegalEntityDTO legalEntityDTO = legalEntityService.findById(1L);

        legalEntityValidator(legalEntityDTO,legalEntity);

        Address address1 = legalEntity.getAddresses().get(0);
        Address address2 = legalEntity.getAddresses().get(1);
        AddressDTO addressDTO1 = legalEntityDTO.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = legalEntityDTO.getAddressesDTO().get(1);

        addressesValidator(addressDTO1,address1);
        addressesValidator(addressDTO2,address2);

        LegalEntity legalEntity1 = address1.getLegalEntity();
        LegalEntity legalEntity2 = address2.getLegalEntity();
        LegalEntityDTO legalEntityDTO1 = addressDTO1.getLegalEntityDTO();
        LegalEntityDTO legalEntityDTO2 = addressDTO2.getLegalEntityDTO();

        legalEntityValidator(legalEntityDTO1,legalEntity1);
        legalEntityValidator(legalEntityDTO2,legalEntity2);

        verify(legalEntityRepository,times(1)).findById(1L);
    }

    @Test
    public void findByIdExistsWithIndividuals(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());

        when(legalEntityRepository.findById(1L)).thenReturn(Optional.of(legalEntity));

        LegalEntityDTO legalEntityDTO = legalEntityService.findById(1L);

        legalEntityValidator(legalEntityDTO,legalEntity);

        Address address1 = legalEntity.getAddresses().get(0);
        Address address2 = legalEntity.getAddresses().get(1);
        AddressDTO addressDTO1 = legalEntityDTO.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = legalEntityDTO.getAddressesDTO().get(1);

        addressesValidator(addressDTO1,address1);
        addressesValidator(addressDTO2,address2);

        LegalEntity legalEntity1 = address1.getLegalEntity();
        LegalEntity legalEntity2 = address2.getLegalEntity();
        LegalEntityDTO legalEntityDTO1 = addressDTO1.getLegalEntityDTO();
        LegalEntityDTO legalEntityDTO2 = addressDTO2.getLegalEntityDTO();

        legalEntityValidator(legalEntityDTO1,legalEntity1);
        legalEntityValidator(legalEntityDTO2,legalEntity2);

        Individual individual1 = legalEntity.getIndividuals().get(0);
        Individual individual2 = legalEntity.getIndividuals().get(1);
        IndividualDTO individualDTO1 = legalEntityDTO.getIndividualsDTO().get(0);
        IndividualDTO individualDTO2 = legalEntityDTO.getIndividualsDTO().get(1);

        individualsValidator(individualDTO1,individual1);
        individualsValidator(individualDTO2,individual2);

        address1 = individual1.getAddresses().get(0);
        address2 = individual1.getAddresses().get(1);
        addressDTO1 = legalEntityDTO.getAddressesDTO().get(0);
        addressDTO2 = legalEntityDTO.getAddressesDTO().get(1);
    }


    private void legalEntityValidator(LegalEntityDTO legalEntityDTO, LegalEntity legalEntity) {

        Assertions.assertEquals(legalEntityDTO.getTradeName(),legalEntity.getTradeName());
        Assertions.assertEquals(legalEntityDTO.getName(),legalEntity.getName());
        Assertions.assertEquals(legalEntityDTO.getDocument(),legalEntity.getDocument());
    }

    private void addressesValidator(AddressDTO addressDTO, Address address) {

        Assertions.assertEquals(addressDTO.getMain(), address.getMain());
        Assertions.assertEquals(addressDTO.getDistrict(), address.getDistrict());
        Assertions.assertEquals(addressDTO.getNumber(),address.getNumber());
        Assertions.assertEquals(addressDTO.getCity(),address.getCity());
        Assertions.assertEquals(addressDTO.getState(),address.getState());
        Assertions.assertEquals(addressDTO.getZipCode(),address.getZipCode());
    }

    private void individualsValidator(IndividualDTO individualDTO, Individual individual) {

        Assertions.assertEquals(individualDTO.getName(),individual.getName());
        Assertions.assertEquals(individualDTO.getDocument(),individual.getDocument());
        Assertions.assertEquals(individualDTO.getBirthDate(),individual.getBirthDate());
        Assertions.assertEquals(individualDTO.getMotherName(),individual.getMotherName());
    }
}