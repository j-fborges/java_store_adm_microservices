package br.com.j_fborges.online.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.j_fborges.online.domain.Product;
import br.com.j_fborges.online.domain.Product.Status;
import br.com.j_fborges.online.exceptions.EntityNotFoundException;
import br.com.j_fborges.online.repository.IProductRepository;

@Service
public class FindProduct {
	
private IProductRepository productRepository;
	
	@Autowired
	public FindProduct(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Page<Product> find(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	public Page<Product> find(Pageable pageable, Status status) {
		return productRepository.findAllByStatus(pageable, status);
	}

	public Product findByIdCode(String idCode) {
		return productRepository.findByIdCode(idCode)
				.orElseThrow(() -> new EntityNotFoundException(Product.class, "idCode", idCode));
	}
}
