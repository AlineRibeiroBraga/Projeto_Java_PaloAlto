package br.com.invillia.projetoPaloAlto;

import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

public class IndividualServiceTest {

    @Autowired
    private Faker faker;

    private IndividualDTO createIndividualDTO() {
        IndividualDTO individualDTO = new IndividualDTO();
    }

    public void insertOkay(){
        IndividualDTO individualDTO = createIndividualDTO();
    }

    public Long insert(IndividualDTO individualDTO) {

        if (!individualRepository.existsByDocument(individualDTO.getDocument())) {
            if (!individualRepository.existsByRg(individualDTO.getRg())) {
                if(mainAddressValidator(individualDTO.getAddress())){

                    Individual individual = individualMapper.individualDTOToIndividual(individualDTO);

                    return individualRepository.save(individual).getId();
                }

                throw new AddressException(Messages.MUCH_MAIN_ADDRESS);

            }
        }

        throw new IndividualException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

    public void insertAlreadyExists(){

    }
}
