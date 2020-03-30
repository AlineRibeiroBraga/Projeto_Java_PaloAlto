package br.com.invillia.projetoPaloAlto.repository;

import br.com.invillia.projetoPaloAlto.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByNumber(String number);
}
