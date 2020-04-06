package br.com.invillia.projetoPaloAlto.individualTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
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
public class IndividualFindByTest {

    private Faker faker;

    @Mock
    private IndividualRepository individualRepository;

    @Spy
    @InjectMocks
    private IndividualMapper individualMapper;

    @Spy
    @InjectMocks
    private IndividualService individualService;

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
        individual.setName(faker.name().name());
        individual.setCreatedAt(LocalDateTime.now());
        individual.setRg(faker.number().digits(9));
        individual.setDocument(faker.number().digits(11));
        individual.setMotherName(faker.name().name());
        individual.setBirthDate(LocalDate.now());
        individual.setAddresses(createListAddress());

        for(Address address : individual.getAddresses()){
            address.setIndividual(individual);
        }

        return individual;
    }

    private List<Address> createListAddress() {

        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress(1L,true));
        addresses.add(createAddress(2L,false));

        return addresses;
    }

    private Address createAddress(Long id, Boolean main) {

        Address address = new Address();

        address.setId(id);
        address.setMain(main);
        address.setDistrict(faker.address().fullAddress());
        address.setNumber(faker.address().buildingNumber());
        address.setCity(faker.address().cityName());
        address.setState(faker.address().state());
        address.setZipCode(faker.address().zipCode());

        return address;
    }

    private IndividualDTO createIndividualDTO() {

        IndividualDTO individualDTO = new IndividualDTO();

        individualDTO.setName(faker.name().name());
        individualDTO.setRg(faker.number().digits(9));
        individualDTO.setDocument(faker.number().digits(11));
        individualDTO.setMotherName(faker.name().name());
        individualDTO.setBirthDate(LocalDate.now());
        individualDTO.setAddressesDTO(createListAddressDTO());

        for(AddressDTO addressDTO : individualDTO.getAddressesDTO()){
            addressDTO.setIndividualDTO(individualDTO);
        }

        return individualDTO;
    }

    private List<AddressDTO> createListAddressDTO() {

        List<AddressDTO> addresses = new ArrayList<>();

        addresses.add(createAddressDTO(1L,true));
        addresses.add(createAddressDTO(2L,false));

        return addresses;
    }

    private AddressDTO createAddressDTO(Long id, Boolean main) {

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setMain(main);
        addressDTO.setDistrict(faker.address().fullAddress());
        addressDTO.setNumber(faker.address().buildingNumber());
        addressDTO.setCity(faker.address().cityName());
        addressDTO.setState(faker.address().state());
        addressDTO.setZipCode(faker.address().zipCode());

        return addressDTO;
    }

    @Test
    public void findByIdExists(){

        Individual individual = createIndividual();

        when(individualRepository.findById(1L)).thenReturn(Optional.of(individual));

        IndividualDTO individualDTO = individualService.findById(1L);

        Assertions.assertNotNull(individualDTO);
        Assertions.assertEquals(individualDTO.getName(),individual.getName());
        Assertions.assertEquals(individualDTO.getDocument(),individual.getDocument());
        Assertions.assertEquals(individualDTO.getBirthDate(),individual.getBirthDate());
        Assertions.assertEquals(individualDTO.getMotherName(),individual.getMotherName());

        AddressDTO addressDTO1 = individualDTO.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = individualDTO.getAddressesDTO().get(1);
        Address address1 = individual.getAddresses().get(0);
        Address address2 = individual.getAddresses().get(1);
        IndividualDTO individualDTO1 = addressDTO1.getIndividualDTO();
        IndividualDTO individualDTO2 = addressDTO2.getIndividualDTO();
        Individual individual1 = address1.getIndividual();
        Individual individual2 = address2.getIndividual();

        Assertions.assertEquals(addressDTO1.getMain(), address1.getMain());
        Assertions.assertEquals(addressDTO1.getDistrict(), address1.getDistrict());
        Assertions.assertEquals(addressDTO1.getNumber(),address1.getNumber());
        Assertions.assertEquals(addressDTO1.getCity(),address1.getCity());
        Assertions.assertEquals(addressDTO1.getState(),address1.getState());
        Assertions.assertEquals(addressDTO1.getZipCode(),address1.getZipCode());

        Assertions.assertEquals(individualDTO1.getName(),individual1.getName());
        Assertions.assertEquals(individualDTO1.getDocument(),individual1.getDocument());
        Assertions.assertEquals(individualDTO1.getBirthDate(),individual1.getBirthDate());
        Assertions.assertEquals(individualDTO1.getMotherName(),individual1.getMotherName());

        Assertions.assertEquals(addressDTO2.getMain(), address2.getMain());
        Assertions.assertEquals(addressDTO2.getDistrict(), address2.getDistrict());
        Assertions.assertEquals(addressDTO2.getNumber(),address2.getNumber());
        Assertions.assertEquals(addressDTO2.getCity(),address2.getCity());
        Assertions.assertEquals(addressDTO2.getState(),address2.getState());
        Assertions.assertEquals(addressDTO2.getZipCode(),address2.getZipCode());

        Assertions.assertEquals(individualDTO2.getName(),individual2.getName());
        Assertions.assertEquals(individualDTO2.getDocument(),individual2.getDocument());
        Assertions.assertEquals(individualDTO2.getBirthDate(),individual2.getBirthDate());
        Assertions.assertEquals(individualDTO2.getMotherName(),individual2.getMotherName());

        verify(individualRepository,times(1)).findById(1L);
    }

    @Test
    public void findByDocumentExists(){

        Individual individual  = createIndividual();

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        IndividualDTO individualDTO = individualService.findByDocument(individual.getDocument());

        AddressDTO addressDTO1 = individualDTO.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = individualDTO.getAddressesDTO().get(1);
        Address address1 = individual.getAddresses().get(0);
        Address address2 = individual.getAddresses().get(1);

        IndividualDTO individualDTO1 = addressDTO1.getIndividualDTO();
        IndividualDTO individualDTO2 = addressDTO2.getIndividualDTO();
        Individual individual1 = address1.getIndividual();
        Individual individual2 = address2.getIndividual();

        Assertions.assertEquals(addressDTO1.getZipCode(),address1.getZipCode());
        Assertions.assertEquals(addressDTO1.getState(),address1.getState());
        Assertions.assertEquals(addressDTO1.getCity(),address1.getCity());
        Assertions.assertEquals(addressDTO1.getNumber(),address1.getNumber());
        Assertions.assertEquals(addressDTO1.getDistrict(),address1.getDistrict());

        Assertions.assertEquals(addressDTO2.getZipCode(),address2.getZipCode());
        Assertions.assertEquals(addressDTO2.getState(),address2.getState());
        Assertions.assertEquals(addressDTO2.getCity(),address2.getCity());
        Assertions.assertEquals(addressDTO2.getNumber(),address2.getNumber());
        Assertions.assertEquals(addressDTO2.getDistrict(),address2.getDistrict());

        Assertions.assertEquals(individualDTO1.getName(),individual1.getName());
        Assertions.assertEquals(individualDTO1.getDocument(),individual1.getDocument());
        Assertions.assertEquals(individualDTO1.getBirthDate(),individual1.getBirthDate());
        Assertions.assertEquals(individualDTO1.getMotherName(),individual1.getMotherName());

        Assertions.assertEquals(individualDTO2.getName(),individual2.getName());
        Assertions.assertEquals(individualDTO2.getDocument(),individual2.getDocument());
        Assertions.assertEquals(individualDTO2.getBirthDate(),individual2.getBirthDate());
        Assertions.assertEquals(individualDTO2.getMotherName(),individual2.getMotherName());

        verify(individualRepository,times(1)).findByDocument(individualDTO.getDocument());
    }

//    @Test
//    public void findByIdNotExists(){
//
//        when(individualRepository.findById(1L)).thenReturn(Optional.empty());
//
//        IndividualDTO individualDTO = individualService.findById(1L);
//
//        Assertions.assertThrows(IndividualException.class, ()-> individualService.findById(1L));
//    }
}
