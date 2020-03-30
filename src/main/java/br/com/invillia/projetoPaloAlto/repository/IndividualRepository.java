package br.com.invillia.projetoPaloAlto.repository;

import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {

    Boolean existsByDocument(String document);

    Boolean existsByRg(String rg);
}
