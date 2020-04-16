package br.com.invillia.projetoPaloAlto.individualTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
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

    private IndividualDTO createIndividualDTO() {

        IndividualDTO individualDTO = new IndividualDTO();

        individualDTO.setName(faker.name().name());
        individualDTO.setMotherName(faker.name().fullName());
        individualDTO.setActive(true);
        individualDTO.setDocument(faker.number().digits(11));
        individualDTO.setBirthDate(LocalDate.now());
        individualDTO.setRg(faker.number().digits(9));
        individualDTO.setAddressesDTO(createListAddressDTO());

        for (AddressDTO addressDTO : individualDTO.getAddressesDTO()){
            addressDTO.setIndividualDTO(individualDTO);
        }

        return individualDTO;
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
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);

        when(individualRepository.findByDocument(individualDTO.getDocument())).thenReturn(Optional.of(individual));

        String documento = individualService.updateByDocument(individualDTO);
        individualDTO = individualService.findByDocument(documento);

        fieldsValidator(individualDTO,individual);

        verify(individualService,times(1)).updateByDocument(individualDTO);
    }

    @Test
    public void updateByDocumentNotExists(){

        when(individualRepository.findByDocument(Mockito.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateByDocument(createIndividualDTO()));
    }

    @Test
    public void updateByDocumentInvalided(){

        when(individualRepository.findByDocument(Mockito.anyString())).thenReturn(Optional.of(createIndividual()));

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateByDocument(createIndividualDTO()));
    }

    @Test
    public void updateByDocumentNotActive(){
        Individual individual = createIndividual();
        individual.setActive(false);
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateByDocument(individualDTO));
    }

    @Test
    public void updateByDocumentMoreThanOneMainAddress(){

        Individual individual = createIndividual();
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);
        individualDTO.getAddressesDTO().get(1).setMain(true);

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateByDocument(individualDTO));
    }

    @Test
    public void updateByDocumentNoMainAddress(){

        Individual individual = createIndividual();
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);

        individualDTO.getAddressesDTO().get(0).setMain(false);

        when(individualRepository.findByDocument(individual.getDocument())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateByDocument(individualDTO));
    }

    @Test
    public void updateById(){

        Individual individual = createIndividual();
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Long id = individualService.updateById(individual.getId(),individualDTO);
        individualDTO = individualService.findById(id);

        fieldsValidator(individualDTO,individual);

        verify(individualService,times(1)).updateById(individual.getId(),individualDTO);
    }

    @Test
    public void updateByIdNotExists(){

        when(individualRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateById(1L,createIndividualDTO()));
    }

    @Test
    public void updateByIdDocumentInvalided(){

        when(individualRepository.findById(1L)).thenReturn(Optional.of(createIndividual()));

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateById(1L,createIndividualDTO()));
    }

    @Test
    public void updateByIdNotActive(){
        Individual individual = createIndividual();
        individual.setActive(false);
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);

        when(individualRepository.findById(1L)).thenReturn(Optional.of(individual));

        Assertions.assertThrows(IndividualException.class,
                () -> individualService.updateById(1L,individualDTO));
    }

    @Test
    public void updateByIdMoreThanOneMainAddress(){

        Individual individual = createIndividual();
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);
        individualDTO.getAddressesDTO().get(1).setMain(true);

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateById(individual.getId(),individualDTO));
    }

    @Test
    public void updateByIdNoMainAddress(){

        Individual individual = createIndividual();
        IndividualDTO individualDTO = individualMapper.individualToIndividualDTO(individual);

        individualDTO.getAddressesDTO().get(0).setMain(false);

        when(individualRepository.findById(individual.getId())).thenReturn(Optional.of(individual));

        Assertions.assertThrows(AddressException.class,
                () -> individualService.updateById(individual.getId(),individualDTO));
    }

    private void fieldsValidator(IndividualDTO individualDTO, Individual individual) {

        individualsValidator(individualDTO,individual);

        AddressDTO addressDTO1 = individualDTO.getAddressesDTO().get(0);
        AddressDTO addressDTO2 = individualDTO.getAddressesDTO().get(1);
        Address address1 = individual.getAddresses().get(0);
        Address address2 = individual.getAddresses().get(1);

        addressesValidator(addressDTO1,address1);
        addressesValidator(addressDTO2,address2);

        IndividualDTO individualDTO1 = addressDTO1.getIndividualDTO();
        IndividualDTO individualDTO2 = addressDTO2.getIndividualDTO();
        Individual individual1 = address1.getIndividual();
        Individual individual2 = address2.getIndividual();

        individualsValidator(individualDTO1,individual1);
        individualsValidator(individualDTO2,individual2);
    }

    private void individualsValidator(IndividualDTO individualDTO, Individual individual) {

        Assertions.assertEquals(individualDTO.getActive(),individual.getActive());
        Assertions.assertEquals(individualDTO.getName(),individual.getName());
        Assertions.assertEquals(individualDTO.getDocument(),individual.getDocument());
        Assertions.assertEquals(individualDTO.getBirthDate(),individual.getBirthDate());
        Assertions.assertEquals(individualDTO.getMotherName(),individual.getMotherName());
    }

    private void addressesValidator(AddressDTO addressDTO, Address address) {

        Assertions.assertEquals(addressDTO.getMain(), address.getMain());
        Assertions.assertEquals(addressDTO.getDistrict(), address.getDistrict());
        Assertions.assertEquals(addressDTO.getNumber(),address.getNumber());
        Assertions.assertEquals(addressDTO.getCity(),address.getCity());
        Assertions.assertEquals(addressDTO.getState(),address.getState());
        Assertions.assertEquals(addressDTO.getZipCode(),address.getZipCode());
    }
}