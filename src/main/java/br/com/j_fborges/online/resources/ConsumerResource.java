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

import br.com.j_fborges.online.domain.Consumer;
import br.com.j_fborges.online.usecase.FindConsumer;
import br.com.j_fborges.online.usecase.RegisterConsumer;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerResource {
	
	private FindConsumer findConsumer;
	private RegisterConsumer registerConsumer;
	
	@Autowired
	public ConsumerResource(FindConsumer findConsumer, 
			RegisterConsumer registerConsumer) {
		this.findConsumer = findConsumer;
		this.registerConsumer = registerConsumer;
	}
	
	@GetMapping
	public ResponseEntity<Page<Consumer>> findr(Pageable pageable) {
		return ResponseEntity.ok(findConsumer.find(pageable));
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find um consumer by id")
	public ResponseEntity<Consumer> findById(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(findConsumer.findById(id));
	}
	
	@GetMapping(value = "isRegistered/{id}")
	public ResponseEntity<Boolean> isRegistered(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(findConsumer.isRegistered(id));
	}
	
	@PostMapping
	public ResponseEntity<Consumer> register(@RequestBody @Valid Consumer consumer) {
		return ResponseEntity.ok(registerConsumer.register(consumer));
	}
	
	@GetMapping(value = "/idNumber/{idNumber}")
	@Operation(summary = "Finds consumer by idNumber")
	public ResponseEntity<Consumer> findByIdNumber(@PathVariable(value = "idNumber", required = true) Long idNumber) {
		return ResponseEntity.ok(findConsumer.findByIdNumber(idNumber));
	}
	
	@PutMapping
	@Operation(summary = "Updates consumer")
	public ResponseEntity<Consumer> update(@RequestBody @Valid Consumer consumer) {
		return ResponseEntity.ok(registerConsumer.update(consumer));
	}	
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Removes consumer by it's unique identifier")
	public ResponseEntity<String> remove(@PathVariable(value = "id") String id) {
		registerConsumer.remove(id);
		return ResponseEntity.ok("Removed with success");
	}

}