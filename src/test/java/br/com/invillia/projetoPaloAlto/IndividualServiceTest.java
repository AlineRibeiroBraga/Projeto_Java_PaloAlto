package br.com.invillia.projetoPaloAlto;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.service.IndividualService;
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

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_CLASS)
public class IndividualServiceTest {

    private Faker faker;

    @Mock
    private IndividualRepository individualRepository;

    @Spy
    @InjectMocks
    private IndividualService individualService;

    @Mock
    private IndividualMapper individualMapper;

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
        individualDTO.setAddressesDTO(createAddressesDTO());

        for(AddressDTO addressDTO : individualDTO.getAddressesDTO()){
            addressDTO.setIndividualDTO(individualDTO);
        }

        return individualDTO;
    }

    private List<AddressDTO> createAddressesDTO() {
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

    @Test
    public void insertOkay(){

        IndividualDTO individualDTO = createIndividualDTO();

        when(individualRepository.existsByDocument(individualDTO.getDocument())).thenReturn(false);
        when(individualRepository.existsByRg(individualDTO.getRg())).thenReturn(false);
//        when(mainAddressValidator(createIndividualDTO().getAddress())).thenReturn(true);
        when(individualMapper.individualDTOToIndividual(individualDTO)).thenReturn(Mockito.any(Individual.class));
        when(individualRepository.save(Mockito.any(Individual.class))).thenReturn(createIndividual());

        Long id = individualService.insert(individualDTO);

        Assertions.assertNotNull(id);
        verify(individualRepository,times(1)).save(Mockito.any(Individual.class));
    }

    private Individual createIndividual() {

        Individual individual = new Individual();

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

    private List<Address> createListAddress() {

        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress(true));
        addresses.add(createAddress(false));

        return addresses;
    }

    private Address createAddress(Boolean main) {

        Address address = new Address();

        address.setDistrict(faker.name().name());
        address.setMain(main);
        address.setNumber(faker.number().digit());
        address.setCity(faker.name().name());
        address.setState(faker.name().name());
        address.setZipCode(faker.code().gtin8());

        return address;
    }
}
