package br.com.invillia.projetoPaloAlto.mapper;

import br.com.invillia.projetoPaloAlto.domain.Address;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
}
