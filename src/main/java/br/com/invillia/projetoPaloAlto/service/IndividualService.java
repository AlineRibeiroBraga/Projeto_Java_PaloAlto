package br.com.invillia.projetoPaloAlto.service;

import br.com.invillia.projetoPaloAlto.controller.AddressController;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional()
public class IndividualService {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private IndividualMapper individualMapper;

    public Long insert(IndividualDTO individualDTO) {

        if (!individualRepository.existsByDocument(individualDTO.getDocument())) {
            if (!individualRepository.existsByRg(individualDTO.getRg())) {
                if(mainAddressValidator(individualDTO.getAddressesDTO())){

                    Individual individual = individualMapper.individualDTOToIndividual(individualDTO);

                    return individualRepository.save(individual).getId();
                }

                throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
            }
        }

        throw new IndividualException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

    private boolean mainAddressValidator(List<AddressDTO> addressesDTO) {

        int main = 0;

        for(AddressDTO addressDTO :  addressesDTO){
            if(addressDTO.getMain()){
                ++main;
            }

            if(main > 1){
                return false;
            }
        }

        return true;
    }
}


