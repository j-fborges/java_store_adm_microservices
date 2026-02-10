package br.com.j_fborges.online.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.j_fborges.online.domain.Product;
import br.com.j_fborges.online.domain.Product.Status;
import br.com.j_fborges.online.usecase.FindProduct;
import br.com.j_fborges.online.usecase.RegisterProduct;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

private FindProduct findProduct;
	
	private RegisterProduct registerProduct;
	
	@Autowired
	public ProductResource(FindProduct findProduct,
			RegisterProduct registerProduct) {
		this.findProduct = findProduct;
		this.registerProduct = registerProduct;
	}
	
	@GetMapping
	@Operation(summary = "Finds a paginated list of products")
	public ResponseEntity<Page<Product>> find(Pageable pageable) {
		return ResponseEntity.ok(findProduct.find(pageable));
	}
	
	@GetMapping(value = "/status/{status}")
	@Operation(summary = "Finds a paginated list of products by status")
	public ResponseEntity<Page<Product>> findByStatus(Pageable pageable, 
			@PathVariable(value = "status", required = true) Status status) {
		return ResponseEntity.ok(findProduct.find(pageable, status));
	}
	
	@GetMapping(value = "/{idCode}")
	@Operation(summary = "Finds a product by idCode")
	public ResponseEntity<Product> findByCodigo(String idCode) {
		return ResponseEntity.ok(findProduct.findByIdCode(idCode));
	}
	
	@PostMapping
	@Operation(summary = "Registers a product")
	public ResponseEntity<Product> register(@RequestBody @Valid Product product) {
		return ResponseEntity.ok(registerProduct.register(product));
	}
	
	@PutMapping
	@Operation(summary = "Updates a product")
	public ResponseEntity<Product> atualizar(@RequestBody @Valid Product product) {
		return ResponseEntity.ok(registerProduct.update(product));
	}	
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Removes a product by its unique identifyer")
	public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
		registerProduct.remove(id);
		return ResponseEntity.ok("Successfully removed product");
	}
}
