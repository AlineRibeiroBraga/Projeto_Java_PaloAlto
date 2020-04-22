package br.com.invillia.projetoPaloAlto.service;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LegalEntityService {

    @Autowired
    LegalEntityRepository legalEntityRepository;

    @Autowired
    LegalEntityMapper legalEntityMapper;

    @Autowired
    IndividualRepository individualRepository;

    @Autowired
    IndividualMapper individualMapper;

    public Long insert(LegalEntityDTO legalEntityDTO) {

        if(!legalEntityRepository.existsByDocument(legalEntityDTO.getDocument())){
            if(mainAddressValidator(legalEntityDTO.getAddressesDTO())){
                LegalEntity legalEntity = legalEntityMapper.legalEntityDTOTolegalEntity(legalEntityDTO);

                if(partners(legalEntity.getIndividuals())){

                    return legalEntityRepository.save(legalEntity).getId();
                }

                throw new IndividualException(Messages.INVALIDATED_INDIVIDUAL);
            }

            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        throw new LegalEntityException(Messages.DOCUMENT_ALREADY_EXISTS);
    }

    public LegalEntityDTO findById(Long id) {
        LegalEntity legalEntity = legalEntityRepository.findById(id)
                .orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND));

        return legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
    }

    public LegalEntityDTO findByDocument(String document) {
        LegalEntity legalEntity = legalEntityRepository.findByDocument(document)
                .orElseThrow(()->new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND));

        return legalEntityMapper.legalEntityToLegalEntityDTO(legalEntity);
    }

    public String deleteByDocument(String document) {

        LegalEntity legalEntity = legalEntityRepository.findByDocument(document).
                orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND));

        deleteIndividuals(legalEntity);

        if(legalEntity.getActive()){
            legalEntity.setActive(false);

            return legalEntity.getDocument();
        }

        throw new LegalEntityException(Messages.LEGAL_ENTITY_WAS_ALREADY_DELETED);
    }

    public Long deleteById(Long id) {

        LegalEntity legalEntity = legalEntityRepository.findById(id).
                orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND));

        deleteIndividuals(legalEntity);

        if(legalEntity.getActive()){
            legalEntity.setActive(false);

            return legalEntity.getId();
        }

        throw new LegalEntityException(Messages.LEGAL_ENTITY_WAS_ALREADY_DELETED);
    }

    private void deleteIndividuals(LegalEntity legalEntity) {

        if(legalEntity.getIndividuals() != null){

            for (Individual individual : legalEntity.getIndividuals()){

                if(!individualRepository.findLegalEntityById(individual.getId())){
                    if(individual.getActive()){
                        individual.setActive(false);
                    }
                }
            }
        }
    }

    public String updateByDocument(LegalEntityDTO legalEntityDTO) {

        LegalEntity legalEntity = legalEntityRepository.findByDocument(legalEntityDTO.getDocument())
                .orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND));

        if(!legalEntityDTO.getDocument().equals(legalEntity.getDocument()) || !legalEntity.getActive()){
            throw new LegalEntityException(Messages.INVALIDATED_LEGAL_ENTITY);
        }

        if(!mainAddressValidator(legalEntityDTO.getAddressesDTO())) {
            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        if(!addressesIndividualsValidator(legalEntityDTO.getIndividualsDTO())){
            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        legalEntityMapper.update(legalEntity,legalEntityDTO);

        if(!partners(legalEntity.getIndividuals())){
            throw new IndividualException(Messages.INVALIDATED_INDIVIDUAL);
        }

        legalEntityRepository.save(legalEntity);

        return legalEntity.getDocument();
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

    private Boolean addressesIndividualsValidator(List<IndividualDTO> individualsDTO) {

        if(individualsDTO == null){
           return true;
        }

        for(IndividualDTO individual : individualsDTO){
            if(!mainAddressValidator(individual.getAddressesDTO())){
                return false;
            }
        }

        return true;
    }

    private Boolean partners(List<Individual> individuals) {

        if(individuals == null){
            return true;
        }

        int tam = individuals.size();
        int nullId = 0;
        int validatedId = 0;

        Optional<Individual> individual1 = Optional.empty();
        Optional<Individual> individual2 = Optional.empty();

        for(int i=0; i < tam; i++){
            Individual individual = individuals.get(i);

            if(individual.getId() == null){
                nullId++;
                individual1 = individualRepository.findByDocument(individual.getDocument());
                individual2 = individualRepository.findByRg(individual.getRg());

                if(!individual1.isEmpty()) {
                    if(!individual2.isEmpty()) {

                        Individual individualR1 = individual1.get();
                        Individual individualR2 = individual2.get();

                        if (individualR1.getRg().equals(individualR2.getRg()) &&
                                individualR1.getDocument().equals(individualR2.getDocument())) {

                            individualMapper.updateIndividual(individual, individualR1);
                            individuals.remove(i);
                            individuals.add(i, individualR1);
                            ++validatedId;
                        }
                    }
                }
            }
        }

        if(nullId == validatedId){
            return true;
        }

        return individual1.isEmpty() && individual2.isEmpty();
    }

    public Long updateById(Long id, LegalEntityDTO legalEntityDTO) {

        LegalEntity legalEntity = legalEntityRepository.findById(id)
                .orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND));

        if(!legalEntityDTO.getDocument().equals(legalEntity.getDocument()) || !legalEntity.getActive()){
            throw new LegalEntityException(Messages.INVALIDATED_LEGAL_ENTITY);
        }

        if(!mainAddressValidator(legalEntityDTO.getAddressesDTO()) ||
                !addressesIndividualsValidator(legalEntityDTO.getIndividualsDTO())) {
            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        legalEntityMapper.update(legalEntity,legalEntityDTO);

        if(!partners(legalEntity.getIndividuals())){
            throw new IndividualException(Messages.INVALIDATED_INDIVIDUAL);
        }

        legalEntityRepository.save(legalEntity);

        return legalEntity.getId();
    }
}