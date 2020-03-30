package br.com.invillia.projetoPaloAlto.repository;

import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {

    Boolean existsByDocument(String document);
}
