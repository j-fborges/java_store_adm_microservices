package br.com.j_fborges.online.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.j_fborges.online.domain.Product;
import br.com.j_fborges.online.domain.Product.Status;

public interface IProductRepository extends MongoRepository<Product, String>{

Optional<Product> findByIdCode(String idCode);
	
	Page<Product> findAllByStatus(Pageable pageable, Status status);
}
