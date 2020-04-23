package br.com.invillia.projetoPaloAlto;

import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LegalEntityMapperTest {

    private Faker faker = new Faker();

    public LegalEntity createLegalEntity(Long id) {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setId(id);
        legalEntity.setActive(true);
        legalEntity.setDocument(faker.number().digits(11));
        legalEntity.setCreatedAt(LocalDateTime.now());
        legalEntity.setTradeName(faker.name().name());
        legalEntity.setName(faker.name().name());
        legalEntity.setIndividuals(new ArrayList<>());
        legalEntity.setAddresses(createListAddress());

        for(Address address : legalEntity.getAddresses()){
            address.setLegalEntity(legalEntity);
        }

        return legalEntity;
    }

    public List<Address> createListAddress() {

        List<Address> addresses = new ArrayList<>();

        addresses.add(createAddress(true, 1L));

        return addresses;
    }

    public Address createAddress(Boolean main, Long id) {

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

}
