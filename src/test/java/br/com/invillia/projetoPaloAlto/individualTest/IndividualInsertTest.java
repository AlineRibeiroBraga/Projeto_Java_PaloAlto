package br.com.invillia.projetoPaloAlto.individualTest;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.mapper.AddressMapper;
import org.mockito.*;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.service.IndividualService;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_CLASS)
public class IndividualInsertTest {

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
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.faker = new Faker();
    }

    private IndividualDTO createIndividualDTO() {

        IndividualDTO individualDTO = new IndividualDTO();

        individualDTO.setRg(faker.number().digits(9));
        individualDTO.setMotherName(faker.name().fullName());
        individualDTO.setBirthDate(LocalDate.now());
        individualDTO.setDocument(faker.number().digits(11));
        individualDTO.setName(faker.name().fullName());
        individualDTO.setActive(true);
        individualDTO.setAddressesDTO(createListAddressDTO());

        for(AddressDTO addressDTO : individualDTO.getAddressesDTO()){
            addressDTO.setIndividualDTO(individualDTO);
        }

        return individualDTO;
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
        addressDTO.setMain(main);
        addressDTO.setNumber(faker.number().digit());
        addressDTO.setCity(faker.name().name());
        addressDTO.setState(faker.name().name());
        addressDTO.setZipCode(faker.code().gtin8());

        return addressDTO;
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
        individual.setActive(true);
        individual.setAddresses(createListAddress());

        for(Address address : individual.getAddresses()){
            address.setIndividual(individual);
        }

        return individual;
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

    @Test
    public void insertOkay(){

        IndividualDTO individualDTO = createIndividualDTO();

        when(individualRepository.existsByDocument(individualDTO.getDocument())).thenReturn(false);
        when(individualRepository.existsByRg(individualDTO.getRg())).thenReturn(false);
        when(individualRepository.save(Mockito.any(Individual.class))).thenReturn(createIndividual());

        Long id = individualService.insert(individualDTO);

        Assertions.assertNotNull(id);
        verify(individualRepository,times(1)).save(Mockito.any(Individual.class));
    }

    @Test
    public void insertDocumentAlreadyExists(){

        IndividualDTO individualDTO = createIndividualDTO();

        when(individualRepository.existsByDocument(individualDTO.getDocument())).thenReturn(true);

        Assertions.assertThrows(IndividualException.class, () -> individualService.insert(individualDTO));
    }

    @Test
    public void insertRgAlreadyExists(){

        IndividualDTO individualDTO = createIndividualDTO();

        when(individualRepository.existsByDocument(individualDTO.getDocument())).thenReturn(false);
        when(individualRepository.existsByRg(individualDTO.getRg())).thenReturn(true);

        Assertions.assertThrows(IndividualException.class, () -> individualService.insert(individualDTO));
    }

    @Test
    public void insertMainAddressValidator(){

        IndividualDTO individualDTO = createIndividualDTOAddress();

        when(individualRepository.existsByDocument(individualDTO.getDocument())).thenReturn(false);
        when(individualRepository.existsByRg(individualDTO.getRg())).thenReturn(false);

        Assertions.assertThrows(AddressException.class, () -> individualService.insert(individualDTO));
    }

    private IndividualDTO createIndividualDTOAddress() {

        IndividualDTO individualDTO = new IndividualDTO();

        individualDTO.setRg(faker.number().digits(9));
        individualDTO.setMotherName(faker.name().fullName());
        individualDTO.setBirthDate(LocalDate.now());
        individualDTO.setDocument(faker.number().digits(11));
        individualDTO.setName(faker.name().fullName());
        individualDTO.setAddressesDTO(createAddressesDTOAddress());

        for(AddressDTO addressDTO : individualDTO.getAddressesDTO()){
            addressDTO.setIndividualDTO(individualDTO);
        }

        return individualDTO;
    }

    private List<AddressDTO> createAddressesDTOAddress() {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        addressesDTO.add(createAddressDTO(true));
        addressesDTO.add(createAddressDTO(true));

        return addressesDTO;
    }
}