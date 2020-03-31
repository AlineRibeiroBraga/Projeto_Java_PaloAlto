package br.com.invillia.projetoPaloAlto.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {

    Boolean existsByDocument(String document);

    Boolean existsByRg(String rg);

    Individual findByDocument(String document);
}
