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
import br.com.invillia.projetoPaloAlto.utils.Messages;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void findByIdOkay(){
        when(individualRepository.findById(1L)).thenReturn(Optional.of(createIndividual()));
//        when(individualMapper.individualToIndividualDTO(createIndividual())).thenReturn(createIndividualDTO());

        IndividualDTO individualDTO = individualService.findById(1L);

        Assertions.assertNotNull(1L);
        verify(individualRepository,times(1)).findById(1L);
    }

    public IndividualDTO findById(Long id) {
        Optional<Individual> optionalIndividual = Optional.ofNullable(individualRepository.findById(id)
                .orElseThrow(() -> new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND)));

        return individualMapper.individualToIndividualDTO(individualRepository.findById(id).get());
    }
}
