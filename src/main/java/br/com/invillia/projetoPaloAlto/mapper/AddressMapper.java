package br.com.invillia.projetoPaloAlto.mapper;

import java.util.List;
import java.util.ArrayList;

import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import org.springframework.stereotype.Component;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;

@Component
public class AddressMapper {

    public Address addressDTOToAdress(AddressDTO addressDTO){

        Address address = new Address();

        address.setMain(addressDTO.getMain());
        address.setDistrict(addressDTO.getDistrict());
        address.setNumber(addressDTO.getNumber());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());

        return address;
    }

    public List<Address> listAddressDTOToListAddress(List<AddressDTO> addressesDTO) {

        List<Address> address = new ArrayList<>();

        for(AddressDTO addressDTO :  addressesDTO){
            address.add(addressDTOToAdress(addressDTO));
        }

        return address;
    }

    public AddressDTO addressToAdressDTO(Address address){

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setMain(address.getMain());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setZipCode(address.getZipCode());

        return addressDTO;
    }

    public List<AddressDTO> listAddressToListAddressDTO(List<Address> addresses) {

        List<AddressDTO> addressesDTOS = new ArrayList<>();

        for(Address address : addresses){
            addressesDTOS.add(addressToAdressDTO(address));
        }

        return addressesDTOS;
    }

    public void setAddressIndividual(List<Address> addresses, Individual individual) {

        for(Address address : addresses){
            address.setIndividual(individual);
        }
    }

    public void setAddressLegalEntity(List<Address> addresses, LegalEntity legalEntity) {

        for(Address address : addresses) {
            address.setLegalEntity(legalEntity);
        }
    }

    public void setAddressDTOIndividualDTO(List<AddressDTO> addressesDTO, IndividualDTO individualDTO) {

        for(AddressDTO addressDTO : addressesDTO){
            addressDTO.setIndividualDTO(individualDTO);
        }
    }


    public void update(List<Address> addresses, List<AddressDTO> addressesDTO) {

        if(addressesDTO != null){

            deactivateAddress(addresses);

            List<Address> addressesList = listAddressDTOToListAddress(addressesDTO);

            for(Address address : addressesList){
                if(addresses.contains(address)){
                    setAddress(addresses,address);
                }
                else {
                    addresses.add(address);
                }
            }
        }
    }

    private void setAddress(List<Address> addresses, Address address1) {

        for(Address address : addresses){
            if(address.getZipCode().equals(address1.getZipCode())){
                address.setMain(address1.getMain());
                address.setDistrict(address1.getDistrict());
                address.setNumber(address1.getNumber());
                address.setCity(address1.getCity());
                address.setState(address1.getState());
                address.setIndividual(address1.getIndividual());
            }
        }
    }

    private void deactivateAddress(List<Address> addresses) {

        for(Address address : addresses){
            if(address.getMain()){
                address.setMain(false);
            }
        }
    }

    public void updateAddress(List<Address> addresses, List<Address> addressesR) {

        if(addresses != null){

            deactivateAddress(addressesR);

            for(Address address : addresses){
                if(addressesR.contains(address)){
                    setAddress(addresses,address);
                }
                else {
                    addresses.add(address);
                }
            }
        }

    }
}
