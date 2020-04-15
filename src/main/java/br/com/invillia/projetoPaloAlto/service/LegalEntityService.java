package br.com.invillia.projetoPaloAlto.service;

import java.util.List;
import java.util.Optional;

import br.com.invillia.projetoPaloAlto.controller.IndividualController;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.dtoUpdate.LegalEntityDTOUpdate;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.exception.AddressException;
import br.com.invillia.projetoPaloAlto.exception.IndividualException;
import br.com.invillia.projetoPaloAlto.mapper.IndividualMapper;
import br.com.invillia.projetoPaloAlto.repository.IndividualRepository;
import org.apache.catalina.startup.CopyParentClassLoaderRule;
import org.springframework.stereotype.Service;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.invillia.projetoPaloAlto.mapper.LegalEntityMapper;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import br.com.invillia.projetoPaloAlto.exception.LegalEntityException;
import br.com.invillia.projetoPaloAlto.repository.LegalEntityRepository;

import javax.swing.text.Document;

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

                individualsDTOValidator(legalEntity.getIndividuals());

                return legalEntityRepository.save(legalEntity).getId();
            }

            throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
        }

        throw new LegalEntityException(Messages.DOCUMENT_ALREADY_EXISTS);
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
        else{
            return true;
        }

        return main == 1;
    }

    private void individualsDTOValidator(List<Individual> individuals) {

        if(individuals != null){
            int lenght = individuals.size();
            Optional<Individual> individual;

            for(int i=0; i < lenght; i++){

                individual = individualRepository.findByDocument(individuals.get(i).getDocument());

                if(!individual.isEmpty()){
                    individuals.remove(i);
                    individuals.add(i,individual.get());
                }
            }
        }
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

        if(mainAddressValidator(legalEntityDTO.getAddressesDTO())){

            legalEntityMapper.update(legalEntity,legalEntityDTO);
            partners(legalEntity);

            legalEntityRepository.save(legalEntity);

            return legalEntity.getDocument();
        }

        throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
    }

    private void partners(LegalEntity legalEntity) {

        int tam = legalEntity.getIndividuals().size();

        for(int i=0; i < tam; i++){
            Individual individual = legalEntity.getIndividuals().get(i);

            if(individual.getId() == null){

                Individual individualR = individualRepository.findByDocument(individual.getDocument()).get();

                if(individualR != null){
                    individualMapper.updateIndividual(individual,individualR);
                    legalEntity.getIndividuals().remove(i);
                    legalEntity.getIndividuals().add(i,individualR);
                }
            }
        }
    }

    public Long updateById(Long id, LegalEntityDTO legalEntityDTO) {

        LegalEntity legalEntity = legalEntityRepository.findById(id)
                .orElseThrow(() -> new LegalEntityException(Messages.LEGAL_ENTITY_WAS_NOT_FOUND));

        if(!legalEntityDTO.getDocument().equals(legalEntity.getDocument()) || !legalEntity.getActive()){
            throw new LegalEntityException(Messages.INVALIDATED_LEGAL_ENTITY);
        }

        if(mainAddressValidator(legalEntityDTO.getAddressesDTO())){

            legalEntityMapper.update(legalEntity,legalEntityDTO);

            legalEntityRepository.save(legalEntity);

            return legalEntity.getId();
        }

        throw new AddressException(Messages.MUCH_MAIN_ADDRESS);
    }
}