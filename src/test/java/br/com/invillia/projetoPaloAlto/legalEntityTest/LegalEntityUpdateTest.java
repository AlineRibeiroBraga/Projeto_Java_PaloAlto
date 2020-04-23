package br.com.invillia.projetoPaloAlto.legalEntityTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.mapper.AddressMapper;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
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
public class LegalEntityUpdateTest {

    private Faker faker;

    @Mock
    private LegalEntityRepository legalEntityRepository;

    @Mock
    private IndividualRepository individualRepository;

    @Spy
    @InjectMocks
    private LegalEntityService legalEntityService;

    @Spy
    @InjectMocks
    private LegalEntityMapper legalEntityMapper;

    @Spy
    private AddressMapper addressMapper;

    @Spy
    @InjectMocks
    private IndividualMapper individualMapper;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.faker = new Faker();
    }

    private LegalEntity createLegalEntity() {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setId(1L);
        legalEntity.setActive(true);
        legalEntity.setTradeName(faker.name().name());
        legalEntity.setName(faker.name().name());
        legalEntity.setDocument(faker.number().digits(14));
        legalEntity.setAddresses(createListAddress());

        for(Address address : legalEntity.getAddresses()){
            address.setLegalEntity(legalEntity);
        }

        return legalEntity;
    }

    private List<Address> createListAddress() {

        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress(1L, true));

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

    private List<Individual> createListIndividual() {

        List<Individual> individuals = new ArrayList<>();

        individuals.add(createIndividual(1L));

        return individuals;
    }

    private Individual createIndividual(Long id) {

        Individual individual = new Individual();

        individual.setId(id);
        individual.setActive(true);
        individual.setName(faker.name().name());
        individual.setBirthDate(LocalDate.now());
        individual.setDocument(faker.number().digits(11));
        individual.setRg(faker.number().digits(9));
        individual.setCreatedAt(LocalDateTime.now());
        individual.setMotherName(faker.name().fullName());
        individual.setAddresses(createListAddress());

        for(Address address :individual.getAddresses()){
            address.setIndividual(individual);
        }

        return individual;
    }

    private Individual newIndividual(Individual individual1) {

        Individual individual = new Individual();

        individual.setId(individual1.getId());
        individual.setName(individual1.getName());
        individual.setMotherName(individual1.getMotherName());
        individual.setCreatedAt(individual1.getCreatedAt());
        individual.setRg(individual1.getRg());
        individual.setDocument(individual1.getDocument());
        individual.setBirthDate(individual1.getBirthDate());
        individual.setUpdatedAt(individual1.getUpdatedAt());
        individual.setActive(individual1.getActive());
        individual.setAddresses(newListAddress(individual1.getAddresses()));

        for(Address address : individual.getAddresses()){
            address.setIndividual(individual);
        }

        return individual;
    }

    private List<Address> newListAddress(List<Address> addresses1) {

        List<Address> addresses = new ArrayList<>();

        for(Address address : addresses1){
            addresses.add(newAddress(address));
        }

        return addresses;
    }

    private Address newAddress(Address address1) {

        Address address = new Address();

        address.setId(address1.getId());
        address.setMain(address1.getMain());
        address.setDistrict(address1.getDistrict());
        address.setNumber(address1.getNumber());
        address.setCity(address1.getCity());
        address.setState(address1.getState());
        address.setZipCode(address1.getZipCode());

        return address;
    }

    private LegalEntityDTO createLegalEntityDTO(){

        LegalEntityDTO legalEntityDTO = new LegalEntityDTO();

        legalEntityDTO.setName(faker.name().name());
        legalEntityDTO.setDocument(faker.number().digits(14));
        legalEntityDTO.setTradeName(faker.name().name());
        legalEntityDTO.setActive(true);
        legalEntityDTO.setAddressesDTO(createListAddressDTO());

        for(AddressDTO addressDTO : legalEntityDTO.getAddressesDTO()){
            addressDTO.setLegalEntityDTO(legalEntityDTO);
        }

        return legalEntityDTO;
    }

    private List<AddressDTO> createListAddressDTO() {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        addressesDTO.add(createAddressDTO(true));

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

    @Test
    public void updateByDocumentExistsWithExistsAddressWithoutIndividuals(){

        LegalEntity legalEntity = createLegalEntity();
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));

        String document = legalEntityService.updateByDocument(legalEntityDTO);
        LegalEntityDTO legalEntityDTOUpdate = legalEntityService.findByDocument(document);

        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,0);

//        legalEntityDTOValidator(legalEntityDTOUpdate,legalEntityDTO);
//
//        AddressDTO addressDTOUpdate1 = legalEntityDTOUpdate.getAddressesDTO().get(0);
//        AddressDTO addressDTO1 = legalEntityDTO.getAddressesDTO().get(0);
//
//        addressDTOValidator(addressDTOUpdate1,addressDTO1);
//
//        LegalEntityDTO legalEntityDTOUpdate1 = addressDTOUpdate1.getLegalEntityDTO();
//        LegalEntityDTO legalEntityDTO1 = addressDTO1.getLegalEntityDTO();
//
//        legalEntityDTOValidator(legalEntityDTOUpdate1,legalEntityDTO1);

        verify(legalEntityService, times(1)).updateByDocument(legalEntityDTO);

    }

    @Test
    public void updateByDocumentExistsWithoutExistsAddressWithoutIndividuals(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.getAddresses().add(createAddress(2L,false));
        legalEntity.getAddresses().get(1).setLegalEntity(legalEntity);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));

        String document = legalEntityService.updateByDocument(legalEntityDTO);
        LegalEntityDTO legalEntityDTOUpdate = legalEntityService.findByDocument(document);

        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,0);
        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,1);

        verify(legalEntityService, times(1)).updateByDocument(legalEntityDTO);
    }

    @Test
    public void updateByDocumentExistsWithExistsAddressWithIndividuals(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().add(createIndividual(2L));
        legalEntity.getIndividuals().get(1).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(1));

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.of(individualR));
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.of(individualR));

        String document = legalEntityService.updateByDocument(legalEntityDTO);
        LegalEntityDTO legalEntityDTOUpdate = legalEntityService.findByDocument(document);

        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,0);

        fieldsPartnersValidator(legalEntityDTOUpdate,legalEntityDTO,0);
        fieldsPartnersValidator(legalEntityDTOUpdate,legalEntityDTO,1);

        verify(legalEntityService, times(1)).updateByDocument(legalEntityDTO);
    }

    @Test
    public void updateByDocumentNotFound(){

        LegalEntityDTO legalEntityDTO = createLegalEntityDTO();

        when(legalEntityRepository.findByDocument(legalEntityDTO.getDocument())).thenReturn(Optional.empty());

        Assertions.assertThrows(LegalEntityException.class, () -> legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentInvalidedDocument(){

        when(legalEntityRepository.findByDocument(Mockito.anyString())).thenReturn(Optional.of(createLegalEntity()));

        Assertions.assertThrows(LegalEntityException.class, () -> legalEntityService.updateByDocument(createLegalEntityDTO()));
    }

    @Test
    public void updateByDocumentNotAticve(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setActive(false);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(LegalEntityException.class, () -> legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentMoreThanOneMainAddressLegalEntity(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.getAddresses().add(createAddress(2L,true));
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentNoOneMainAddressLegalEntity(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.getAddresses().get(0).setMain(false);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentMoreThanOneMainAddressIndividual(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).getAddresses().add(createAddress(2L,true));
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentNoOneMainAddressIndividual(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).getAddresses().get(0).setMain(false);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentInvalidedPartnersEmpty1(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(0));

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.empty());
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.of(individualR));

        Assertions.assertThrows(IndividualException.class, ()-> legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentInvalidedPartnersEmpty2(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(0));

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.of(individualR));
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class, ()-> legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByDocumentInvalidedPartnersDifferent(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(0));

        when(legalEntityRepository.findByDocument(legalEntity.getDocument())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.of(individualR));
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.of(createIndividual(2L)));

        Assertions.assertThrows(IndividualException.class, ()-> legalEntityService.updateByDocument(legalEntityDTO));
    }

    @Test
    public void updateByIdExistsWithExistsAddressWithoutIndividuals(){

        LegalEntity legalEntity = createLegalEntity();
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));

        Long id = legalEntityService.updateById(1L,legalEntityDTO);
        LegalEntityDTO legalEntityDTOUpdate = legalEntityService.findById(id);

        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,0);

        verify(legalEntityService, times(1)).updateById(1L,legalEntityDTO);
    }


    @Test
    public void updateByIdExistsWithoutExistsAddressWithoutIndividuals(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.getAddresses().add(createAddress(2L,false));
        legalEntity.getAddresses().get(1).setLegalEntity(legalEntity);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));

        Long id = legalEntityService.updateById(1L,legalEntityDTO);
        LegalEntityDTO legalEntityDTOUpdate = legalEntityService.findById(id);

        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,0);
        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,1);

        verify(legalEntityService, times(1)).updateById(1L,legalEntityDTO);
    }

    @Test
    public void updateByIdExistsWithExistsAddressWithIndividuals(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().add(createIndividual(2L));
        legalEntity.getIndividuals().get(1).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(1));

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.of(individualR));
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.of(individualR));

        Long id = legalEntityService.updateById(1L,legalEntityDTO);
        LegalEntityDTO legalEntityDTOUpdate = legalEntityService.findById(id);

        fieldsValidator(legalEntityDTOUpdate,legalEntityDTO,0);

        fieldsPartnersValidator(legalEntityDTOUpdate,legalEntityDTO,0);
        fieldsPartnersValidator(legalEntityDTOUpdate,legalEntityDTO,1);

        verify(legalEntityService, times(1)).updateById(1L,legalEntityDTO);
    }

    @Test
    public void updateByIdNotFound(){

        LegalEntityDTO legalEntityDTO = createLegalEntityDTO();

        when(legalEntityRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(LegalEntityException.class, () -> legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdInvalidedId(){

        when(legalEntityRepository.findById(1L)).thenReturn(Optional.of(createLegalEntity()));

        Assertions.assertThrows(LegalEntityException.class,
                () -> legalEntityService.updateById(1L,createLegalEntityDTO()));
    }

    @Test
    public void updateByIdNotAticve(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setActive(false);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(LegalEntityException.class, () -> legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdMoreThanOneMainAddressLegalEntity(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.getAddresses().add(createAddress(2L,true));
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdNoOneMainAddressLegalEntity(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.getAddresses().get(0).setMain(false);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdMoreThanOneMainAddressIndividual(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).getAddresses().add(createAddress(2L,true));
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdNoOneMainAddressIndividual(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).getAddresses().get(0).setMain(false);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));

        Assertions.assertThrows(AddressException.class, ()->legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdInvalidedPartnersEmpty1(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(0));

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.empty());
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.of(individualR));

        Assertions.assertThrows(IndividualException.class, ()-> legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdInvalidedPartnersEmpty2(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(0));

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.of(individualR));
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.empty());

        Assertions.assertThrows(IndividualException.class, ()-> legalEntityService.updateById(1L,legalEntityDTO));
    }

    @Test
    public void updateByIdInvalidedPartnersDifferent(){

        LegalEntity legalEntity = createLegalEntity();
        legalEntity.setIndividuals(createListIndividual());
        legalEntity.getIndividuals().get(0).setId(null);
        LegalEntityDTO legalEntityDTO = legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
        Individual individualR = newIndividual(legalEntity.getIndividuals().get(0));

        when(legalEntityRepository.findById(legalEntity.getId())).thenReturn(Optional.of(legalEntity));
        when(individualRepository.findByDocument(individualR.getDocument())).thenReturn(Optional.of(individualR));
        when(individualRepository.findByRg(individualR.getRg())).thenReturn(Optional.of(createIndividual(2L)));

        Assertions.assertThrows(IndividualException.class, ()-> legalEntityService.updateById(1L,legalEntityDTO));
    }


    private void fieldsValidator(LegalEntityDTO legalEntityDTOUpdate, LegalEntityDTO legalEntityDTO, int index) {

        legalEntityDTOValidator(legalEntityDTOUpdate,legalEntityDTO);

        AddressDTO addressDTOUpdate1 = legalEntityDTOUpdate.getAddressesDTO().get(index);
        AddressDTO addressDTO1 = legalEntityDTO.getAddressesDTO().get(index);

        addressDTOValidator(addressDTOUpdate1,addressDTO1);

        LegalEntityDTO legalEntityDTOUpdate1 = addressDTOUpdate1.getLegalEntityDTO();
        LegalEntityDTO legalEntityDTO1 = addressDTO1.getLegalEntityDTO();

        legalEntityDTOValidator(legalEntityDTOUpdate1,legalEntityDTO1);
    }

    private void fieldsPartnersValidator(LegalEntityDTO legalEntityDTOUpdate, LegalEntityDTO legalEntityDTO, int index) {

        IndividualDTO individualDTOUpdate1 = legalEntityDTOUpdate.getIndividualsDTO().get(index);
        IndividualDTO individualDTO1 = legalEntityDTO.getIndividualsDTO().get(index);

        individualValidator(individualDTOUpdate1,individualDTO1);

        AddressDTO addressDTOUpdate11 = individualDTOUpdate1.getAddressesDTO().get(0);
        AddressDTO addressDTO11 = individualDTO1.getAddressesDTO().get(0);

        addressDTOValidator(addressDTOUpdate11,addressDTO11);

        IndividualDTO individualDTOUpdate11 = addressDTOUpdate11.getIndividualDTO();
        IndividualDTO individualDTO11 = addressDTO11.getIndividualDTO();

        individualValidator(individualDTOUpdate11,individualDTO11);
    }

    private void individualValidator(IndividualDTO individualDTOUpdate, IndividualDTO individualDTO) {

        Assertions.assertEquals(individualDTOUpdate.getName(),individualDTO.getName());
        Assertions.assertEquals(individualDTOUpdate.getMotherName(),individualDTO.getMotherName());
    }

    private void addressDTOValidator(AddressDTO addressDTOUpdate, AddressDTO addressDTO) {

        Assertions.assertEquals(addressDTOUpdate.getMain(), addressDTO.getMain());
        Assertions.assertEquals(addressDTOUpdate.getDistrict(), addressDTO.getDistrict());
        Assertions.assertEquals(addressDTOUpdate.getNumber(), addressDTO.getNumber());
        Assertions.assertEquals(addressDTOUpdate.getCity(), addressDTO.getCity());
        Assertions.assertEquals(addressDTOUpdate.getState(), addressDTO.getState());
        Assertions.assertEquals(addressDTOUpdate.getZipCode(), addressDTO.getZipCode());
    }

    private void legalEntityDTOValidator(LegalEntityDTO legalEntityDTOUpdate, LegalEntityDTO legalEntityDTO) {

        Assertions.assertEquals(legalEntityDTOUpdate.getActive(),legalEntityDTO.getActive());
        Assertions.assertEquals(legalEntityDTOUpdate.getName(),legalEntityDTO.getName());
        Assertions.assertEquals(legalEntityDTOUpdate.getTradeName(),legalEntityDTO.getTradeName());
    }
}