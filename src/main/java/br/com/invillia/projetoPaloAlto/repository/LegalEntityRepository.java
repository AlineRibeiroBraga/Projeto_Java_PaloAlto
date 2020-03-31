package br.com.invillia.projetoPaloAlto.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;

@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {

    Boolean existsByDocument(String document);
}
