package br.com.j_fborges.online.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.j_fborges.online.domain.Product;
import br.com.j_fborges.online.domain.Product.Status;
import br.com.j_fborges.online.exceptions.EntityNotFoundException;
import br.com.j_fborges.online.repository.IProductRepository;
import jakarta.validation.Valid;

@Service
public class RegisterProduct {
	
private IProductRepository productRepository;
	
	@Autowired
	public RegisterProduct(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product register(@Valid Product product) {
		product.setStatus(Status.ACTIVE);
		return this.productRepository.insert(product);
	}

	public Product update(@Valid Product product) {
		return this.productRepository.save(product);
	}

	public void remove(String id) {
		Product prod = productRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id));
		prod.setStatus(Status.INACTIVE);
		this.productRepository.save(prod);
		//this.productRepository.deleteById(id);
	}
}
