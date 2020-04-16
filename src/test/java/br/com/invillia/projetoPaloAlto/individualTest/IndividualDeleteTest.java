package br.com.invillia.projetoPaloAlto.individualTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.mapper.AddressMapper;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.service.IndividualService;
import com.github.javafaker.Faker;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_CLASS)
public class IndividualDeleteTest {

    private Faker faker;

    @Mock
    private IndividualRepository individualRepository;

    @Spy
    @InjectMocks
    private IndividualService individualService;

    @Spy
    @InjectMocks
    private IndividualMapper individualMapper;

    @Spy
    private AddressMapper addressMapper;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.faker = new Faker();
    }

    private Individual createIndividual() {

        Individual individual = new Individual();

        individual.setId(1L);
        individual.setActive(true);
        individual.setName(faker.name().name());
        individual.setCreatedAt(LocalDateTime.now());
        individual.setRg(faker.number().digits(9));
        individual.setDocument(faker.number().digits(11));
        individual.setMotherName(faker.name().fullName());
        individual.setBirthDate(LocalDate.now());
        individual.setAddresses(createListAddress());

        for(Address address : individual.getAddresses()){
            address.setIndividual(individual);
        }

        return individual;

    }

    private List<Address> createListAddress() {

        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress(1L, true));
        addresses.add(createAddress(1L, false));

        return addresses;
    }

    private Address createAddress(Long id, Boolean main) {

        Address address = new Address();

        address.setId(id);
        address.setMain(main);
        address.setDistrict(faker.address().fullAddress());
        address.setNumber(faker.address().buildingNumber());
        address.setCity(faker.address().city());
        address.setState(faker.address().state());
        address.setZipCode(faker.address().zipCode());

        return address;
    }

    @Test
    public void deleteByDocumentExists(){

        Individual individual = createIndividual();

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        String document = individualService.deleteByDocument(individual.getDocument());
        IndividualDTO individualDTO = individualService.findByDocument(document);

        fieldsValidator(individualDTO,individual);

        verify(individualService,times(1)).deleteByDocument(individual.getDocument());
    }

    private void fieldsValidator(IndividualDTO individualDTO, Individual individual) {

        individualValidator(individualDTO,individual);

        Address address1 = individual.getAddresses().get(0);
        Address address2 = individual.getAddresses().get(1);
        AddressDTO addressDTO1 = individualDTO.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = individualDTO.getAddressesDTO().get(1);

        addressValidator(address1,addressDTO1);
        addressValidator(address2,addressDTO2);

        Individual individual1 = address1.getIndividual();
        Individual individual2 = address2.getIndividual();
        IndividualDTO individualDTO1 = addressDTO1.getIndividualDTO();
        IndividualDTO individualDTO2 = addressDTO2.getIndividualDTO();

        individualValidator(individualDTO1,individual1);
        individualValidator(individualDTO2,individual2);
    }

    @Test
    public void deleteByDocumentAlreadyDeleted(){

        Individual individual = createIndividual();
        individual.setActive(false);

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(IndividualException.class,
                ()-> individualService.deleteByDocument(individual.getDocument()));
    }

    @Test
    public void deleteByDocumentNotExists(){

        when(individualRepository.findByDocument(Mockito.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class,
                ()-> individualService.deleteByDocument(Mockito.anyString()));
    }

    @Test
    public void deleteByIdExists(){

        Individual individual = createIndividual();

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Long id = individualService.deleteById(individual.getId());
        IndividualDTO individualDTO = individualService.findById(id);

        fieldsValidator(individualDTO,individual);

        verify(individualService,times(1)).deleteById(individual.getId());
    }

    @Test
    public void deleteByIdAlreadyDeleted(){

        Individual individual = createIndividual();
        individual.setActive(false);

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(IndividualException.class,
                ()-> individualService.deleteById(individual.getId()));
    }

    @Test
    public void deleteByIdNotExists(){

        when(individualRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class,
                ()-> individualService.deleteById(1L));
    }

    private void individualValidator(IndividualDTO individualDTO, Individual individual) {

        Assertions.assertEquals(individualDTO.getActive(),individual.getActive());
        Assertions.assertEquals(individualDTO.getBirthDate(),individual.getBirthDate());
        Assertions.assertEquals(individualDTO.getDocument(),individual.getDocument());
        Assertions.assertEquals(individualDTO.getMotherName(),individual.getMotherName());
        Assertions.assertEquals(individualDTO.getRg(),individual.getRg());
        Assertions.assertEquals(individualDTO.getName(),individual.getName());
    }

    private void addressValidator(Address address, AddressDTO addressDTO) {

        Assertions.assertEquals(addressDTO.getDistrict(),address.getDistrict());
        Assertions.assertEquals(addressDTO.getNumber(),address.getNumber());
        Assertions.assertEquals(addressDTO.getCity(),address.getCity());
        Assertions.assertEquals(addressDTO.getState(),address.getState());
        Assertions.assertEquals(addressDTO.getZipCode(),address.getZipCode());
        Assertions.assertEquals(addressDTO.getMain(),address.getMain());
    }
}