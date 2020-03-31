package br.com.invillia.projetoPaloAlto.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public IndividualDTO findByDocument(String document){
        return individualMapper.individualToIndividualDTO(individualRepository.findByDocument(document));
    }

    public IndividualDTO findById(Long id) {
        return individualMapper.individualToIndividualDTO(individualRepository.findById(id).get());
    }
}

