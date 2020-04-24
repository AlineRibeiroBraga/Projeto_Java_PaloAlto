package br.com.invillia.projetoPaloAlto.service;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class IndividualService {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private IndividualMapper individualMapper;

    public Long insert(IndividualDTO individualDTO) {

        if (!individualRepository.existsByDocument(individualDTO.getDocument()) &&
            !individualRepository.existsByRg(individualDTO.getRg())) {
            if(mainAddressValidator(individualDTO.getAddressesDTO())){

                Individual individual = individualMapper.individualDTOToIndividual(individualDTO);

                return individualRepository.save(individual).getId();
            }

            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        throw new IndividualException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

    private boolean mainAddressValidator(List<AddressDTO> addressesDTO) {

        int main = 0;

        if(addressesDTO != null){

            for(AddressDTO addressDTO :  addressesDTO){
                if(addressDTO.getMain()){
                    ++main;
                }
            }
        }

        return main == 1;
    }

    public IndividualDTO findByDocument(String document){

        Individual Individual = individualRepository.findByDocument(document)
                .orElseThrow(()-> new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND));

        return individualMapper.individualToIndividualDTO(Individual);
    }

    public IndividualDTO findById(Long id) {
        Optional<Individual> optionalIndividual = Optional.of(individualRepository.findById(id)
                .orElseThrow(() -> new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND)));

        return individualMapper.individualToIndividualDTO(optionalIndividual.get());
    }

    public String deleteByDocument(String document) {

        Individual individual = individualRepository.findByDocument(document)
                .orElseThrow(()-> new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND));

        if(individual.getActive()){
            individual.setActive(false);

            return individual.getDocument();
        }

        throw new IndividualException(Messages.INDIVIDUAL_WAS_ALREADY_DELETED);
    }

    public Long deleteById(Long id) {

        Individual individual = individualRepository.findById(id)
                .orElseThrow(() -> new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND));

        if (individual.getActive()) {
            individual.setActive(false);

            return individual.getId();
        }

        throw new IndividualException(Messages.INDIVIDUAL_WAS_ALREADY_DELETED);
    }

    public String updateByDocument(IndividualDTO individualDTO) {

        Individual individual = individualRepository.findByDocument(individualDTO.getDocument()).
                orElseThrow( () -> new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND));

        if(!individualDTO.getDocument().equals(individual.getDocument()) &&
           !individualDTO.getRg().equals(individual.getRg()) || !individual.getActive()){
            throw new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND);
        }

        if(!mainAddressValidator(individualDTO.getAddressesDTO())) {
            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        individualMapper.update(individual, individualDTO);

        individualRepository.save(individual);

        return individual.getDocument();
    }

    public Long updateById( Long id, IndividualDTO individualDTO) {

        Individual individual = individualRepository.findById(id).
                orElseThrow(()-> new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND));

        if(!individualDTO.getDocument().equals(individual.getDocument()) &&
                !individualDTO.getRg().equals(individual.getRg()) || !individual.getActive()){
            throw new IndividualException(Messages.INDIVIDUAL_WAS_NOT_FOUND);
        }

        if(!mainAddressValidator(individualDTO.getAddressesDTO())){
            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        individualMapper.update(individual,individualDTO);

        individualRepository.save(individual);

        return individual.getId();
    }

    public IndividualDTO findIndividualDTOById(Long id) {
        return findById(id);
    }
}