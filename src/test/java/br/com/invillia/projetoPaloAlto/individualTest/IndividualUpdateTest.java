package br.com.invillia.projetoPaloAlto.individualTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.dtoUpdate.IndividualDTOUpdate;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
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
public class IndividualUpdateTest {

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
        individual.setMotherName(faker.name().name());
        individual.setName(faker.name().name());
        individual.setCreatedAt(LocalDateTime.now());
        individual.setDocument(faker.number().digits(11));
        individual.setRg(faker.number().digits(9));
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
        address.setCity(faker.address().city());
        address.setState(faker.address().state());
        address.setZipCode(faker.address().zipCode());

        return address;
    }

    private IndividualDTOUpdate createIndividualDTOUpdate() {

        IndividualDTOUpdate individualDTOUpdate = new IndividualDTOUpdate();

        individualDTOUpdate.setName(faker.name().name());
        individualDTOUpdate.setMotherName(faker.name().fullName());
        individualDTOUpdate.setAddressesDTO(createListAddressDTO());

        return individualDTOUpdate;
    }

    private List<AddressDTO> createListAddressDTO() {

        List<AddressDTO> addressDTOS = new ArrayList<>();

        addressDTOS.add(createAddressDTO(true));
        addressDTOS.add(createAddressDTO(false));

        return addressDTOS;
    }

    private AddressDTO createAddressDTO(Boolean main) {

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setMain(main);
        addressDTO.setDistrict(faker.address().fullAddress());
        addressDTO.setNumber(faker.address().buildingNumber());
        addressDTO.setCity(faker.address().city());
        addressDTO.setState(faker.address().state());
        addressDTO.setZipCode(faker.address().zipCode());

        return addressDTO;
    }

    @Test
    public void updateByDocument(){

        Individual individual = createIndividual();
        IndividualDTOUpdate individualDTOUpdate = createIndividualDTOUpdate();

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        String documento = individualService.updateByDocument(individual.getDocument(),individualDTOUpdate);
        IndividualDTO individualDTO = individualService.findByDocument(documento);

        individualValidator(individualDTOUpdate,individualDTO);

        AddressDTO addressDTO1 = individualDTOUpdate.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = individualDTOUpdate.getAddressesDTO().get(1);
        AddressDTO addressDTO3 = individualDTO.getAddressesDTO().get(2);
        AddressDTO addressDTO4 = individualDTO.getAddressesDTO().get(3);

        addressValidator(addressDTO1,addressDTO3);
        addressValidator(addressDTO2,addressDTO4);

        verify(individualService,times(1)).updateByDocument(individual.getDocument(),individualDTOUpdate);
    }

    @Test
    public void updateByDocumentNotExists(){

        when(individualRepository.findByDocument(Mockito.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateByDocument(faker.number().digits(11),createIndividualDTOUpdate()));
    }

    @Test
    public void updateByDocumentNotActive(){
        Individual individual = createIndividual();
        individual.setActive(false);

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateByDocument(individual.getDocument(),createIndividualDTOUpdate()));
    }

    @Test
    public void updateByDocumentMoreThanOneMainAddress(){

        Individual individual = createIndividual();
        IndividualDTOUpdate individualDTOUpdate = createIndividualDTOUpdate();
        individualDTOUpdate.getAddressesDTO().get(1).setMain(true);

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateByDocument(individual.getDocument(),individualDTOUpdate));
    }

    @Test
    public void updateByDocumentNoMainAddress(){

        Individual individual = createIndividual();
        IndividualDTOUpdate individualDTOUpdate = createIndividualDTOUpdate();

        individualDTOUpdate.getAddressesDTO().get(0).setMain(false);

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateByDocument(individual.getDocument(),individualDTOUpdate));
    }

    @Test
    public void updateById(){

        Individual individual = createIndividual();
        IndividualDTOUpdate individualDTOUpdate = createIndividualDTOUpdate();

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Long id = individualService.updateById(individual.getId(),individualDTOUpdate);
        IndividualDTO individualDTO = individualService.findById(id);

        individualValidator(individualDTOUpdate,individualDTO);

        AddressDTO addressDTO1 = individualDTOUpdate.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = individualDTOUpdate.getAddressesDTO().get(1);
        AddressDTO addressDTO3 = individualDTO.getAddressesDTO().get(2);
        AddressDTO addressDTO4 = individualDTO.getAddressesDTO().get(3);

        addressValidator(addressDTO1,addressDTO3);
        addressValidator(addressDTO2,addressDTO4);

        verify(individualService,times(1)).updateById(individual.getId(),individualDTOUpdate);
    }

    @Test
    public void updateByIdNotExists(){

        when(individualRepository.findByDocument(Mockito.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateByDocument(faker.number().digits(11),createIndividualDTOUpdate()));
    }

    @Test
    public void updateByIdNotActive(){
        Individual individual = createIndividual();
        individual.setActive(false);

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateById(individual.getId(),createIndividualDTOUpdate()));
    }

    @Test
    public void updateByIdMoreThanOneMainAddress(){

        Individual individual = createIndividual();
        IndividualDTOUpdate individualDTOUpdate = createIndividualDTOUpdate();
        individualDTOUpdate.getAddressesDTO().get(1).setMain(true);

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateById(individual.getId(),individualDTOUpdate));
    }

    @Test
    public void updateByIdNoMainAddress(){

        Individual individual = createIndividual();
        IndividualDTOUpdate individualDTOUpdate = createIndividualDTOUpdate();

        individualDTOUpdate.getAddressesDTO().get(0).setMain(false);

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateById(individual.getId(),individualDTOUpdate));
    }

    private void addressValidator(AddressDTO addressDTO1, AddressDTO addressDTO2) {

        Assertions.assertEquals(addressDTO1.getDistrict(),addressDTO2.getDistrict());
        Assertions.assertEquals(addressDTO1.getNumber(),addressDTO2.getNumber());
        Assertions.assertEquals(addressDTO1.getCity(),addressDTO2.getCity());
        Assertions.assertEquals(addressDTO1.getState(),addressDTO2.getState());
        Assertions.assertEquals(addressDTO1.getZipCode(),addressDTO2.getZipCode());
        Assertions.assertEquals(addressDTO1.getMain(),addressDTO2.getMain());
    }

    private void individualValidator(IndividualDTOUpdate individualDTOUpdate, IndividualDTO individualDTO) {

        Assertions.assertEquals(individualDTOUpdate.getMotherName(),individualDTO.getMotherName());
        Assertions.assertEquals(individualDTOUpdate.getName(),individualDTO.getName());
    }
}
