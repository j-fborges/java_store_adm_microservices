package br.com.j_fborges.online.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.j_fborges.online.domain.Consumer;

@Repository
public interface IConsumerRepository extends MongoRepository<Consumer, String> {
	
	Optional<Consumer> findByIdNumber(Long idNumber);
}
