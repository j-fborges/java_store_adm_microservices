package br.com.j_fborges.online.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.j_fborges.online.domain.Sale;

@Repository
public interface ISaleRepository extends MongoRepository<Sale, String>{

	Optional<Sale> findByCode(String code);
}
