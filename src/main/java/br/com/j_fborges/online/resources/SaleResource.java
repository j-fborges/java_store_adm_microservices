package br.com.j_fborges.online.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.j_fborges.online.domain.Sale;
import br.com.j_fborges.online.dto.SaleDTO;
import br.com.j_fborges.online.usecase.FindSale;
import br.com.j_fborges.online.usecase.RegisterSale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/sale")
public class SaleResource {
	
private FindSale findSale;
	
	private RegisterSale registerSale;
	
	@Autowired
	public SaleResource(FindSale findSale,
			RegisterSale registerSale) {
		this.findSale = findSale;
		this.registerSale = registerSale;
	}
	
	@GetMapping
	@Operation(summary = "Lists the registered sales")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Returns a list of consumers"),
		    @ApiResponse(responseCode = "400", description = "Malformed requisition or sintax error", 
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
		    @ApiResponse(responseCode = "500", description = "An exception was generated",
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
		})
	public ResponseEntity<Page<Sale>> findr(Pageable pageable) {
		return ResponseEntity.ok(findSale.find(pageable));
	}
	
	@PostMapping
	@Operation(summary = "Start a sale")
	public ResponseEntity<Sale> register(@RequestBody @Valid SaleDTO venda) {
		return ResponseEntity.ok(registerSale.register(venda));
	}
	
	@PutMapping("/{id}/{codigoProduto}/{quantidade}/addProduto")
	public ResponseEntity<Sale> addProductQuantity(
			@PathVariable(name = "id", required = true) String id,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(registerSale.addProductQuantity(id, productCode, quantity));
	}
}
