package br.com.j_fborges.online.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.j_fborges.online.domain.Sale;
import br.com.j_fborges.online.exceptions.EntityNotFoundException;
import br.com.j_fborges.online.repository.ISaleRepository;

@Service
public class FindSale {
	
private ISaleRepository saleRepository;
	
	@Autowired
	public FindSale(ISaleRepository productRepository) {
		this.saleRepository = productRepository;
	}
	
	public Page<Sale> find(Pageable pageable) {
		return saleRepository.findAll(pageable);
	}

	public Sale buscarByCode(String code) {
		return saleRepository.findByCode(code)
				.orElseThrow(() -> new EntityNotFoundException(Sale.class, "code", code));
	}
}
