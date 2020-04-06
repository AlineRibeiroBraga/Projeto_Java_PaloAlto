package br.com.invillia.projetoPaloAlto.legalEntityTest;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;
import br.com.invillia.projetoPaloAlto.service.LegalEntityService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(PER_CLASS)
public class LegalEntityFindByTest {

    private Faker faker;

    @Mock
    private LegalEntityRepository legalEntityRepository;

    @InjectMocks
    private LegalEntityService legalEntityService;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.faker = faker;
    }

    private LegalEntity createLegalEntity() {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setDocument(faker.number().digits(14));
        legalEntity.setTradeName(faker.name().name());
        legalEntity.setName(faker.name().fullName());
        legalEntity.setAddresses(createListAddress());

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

    @Test
    public void findByIdExists(){

        LegalEntity legalEntity = createLegalEntity();

        when(legalEntityRepository.findById(1L)).thenReturn(Optional.of(legalEntity));
        LegalEntityDTO legalEntityDTO = createLegalEntityDTO();
    }
}
