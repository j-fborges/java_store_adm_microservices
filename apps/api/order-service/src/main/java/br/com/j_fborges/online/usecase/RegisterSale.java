package br.com.j_fborges.online.usecase;

import java.math.BigDecimal;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.j_fborges.online.domain.Product;
import br.com.j_fborges.online.domain.Sale;
import br.com.j_fborges.online.domain.Sale.Status;
import br.com.j_fborges.online.dto.SaleDTO;
import br.com.j_fborges.online.exceptions.EntityNotFoundException;
import br.com.j_fborges.online.repository.ISaleRepository;
import br.com.j_fborges.online.service.ConsumerService;
import br.com.j_fborges.online.service.IProductService;
import jakarta.validation.Valid;

@Service
public class RegisterSale {
	
private ISaleRepository saleRepository;
	
	private IProductService productService;
	
	private ConsumerService consumerService;
	
	@Autowired
	public RegisterSale(ISaleRepository productRepository,
			IProductService productService,
			ConsumerService consumerService) {
		this.saleRepository = productRepository;
		this.productService = productService;
		this.consumerService = consumerService;
	}
	
	public Sale register(@Valid SaleDTO saleDTO) {
		Sale sale = convertToDomain(saleDTO, Status.STARTED);
		validateConsumer(sale.getConsumerId());
		sale.recalculateSaleTotalValue();
		return this.saleRepository.insert(sale);
	}

	private void validateConsumer(String consumerId) {
		Boolean isRegistered = 
				this.consumerService.isConsumerRegistered(consumerId);
		if (!isRegistered) {
			new EntityNotFoundException(Sale.class, "consumerId", consumerId);
		}
	}

	private Sale convertToDomain(@Valid SaleDTO saleDTO, Status status) {
		return Sale.builder()
				.consumerId(saleDTO.getConsumerId())
				.code(saleDTO.getCode())
				.date(saleDTO.getDate())
				.status(status)
				.totalValue(BigDecimal.ZERO)
				.saleProducts(new HashSet<>())
				.build();
	}

	public Sale update(@Valid Sale sale) {
		return this.saleRepository.save(sale);
	}

	public Sale complete(String id) {
		Sale sale = find(id);
		sale.validateStatus();
		sale.setStatus(Status.COMPLETED);
		return this.saleRepository.save(sale);
	}
	
	public Sale cancel(String id) {
		Sale sale = find(id);
		sale.validateStatus();
		sale.setStatus(Status.CANCELLED);
		return this.saleRepository.save(sale);
	}

	public Sale addProductQuantity(String id, String productCode, Integer quantity) {
		Sale sale = find(id);
		Product product = findProduct(productCode);
		sale.validateStatus();
		sale.addProductQuantity(product, quantity);
		return this.saleRepository.save(sale);
	}
	
	public Sale removeProductQuantity(String id, String productCode, Integer quantity) {
		Sale sale = find(id);
		Product product = findProduct(productCode);
		sale.validateStatus();
		sale.removeProductQuantity(product, quantity);
		return this.saleRepository.save(sale);
	}
	
	private Sale find(String id) {
		return saleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Sale.class, "id", id));
		
	}
	
	private Product findProduct(String productCode) {
		Product prod = productService.findProduct(productCode);
		if (prod == null) {
			throw new EntityNotFoundException(Product.class, "code", productCode);
		}
		return prod;
	}
}
