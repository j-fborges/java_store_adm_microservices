package br.com.j_fborges.online.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.j_fborges.online.domain.Product;

@FeignClient(name = "product", url = "${application.productService.endpointFetchProduct}")
public interface IProductService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/{idCode}", produces = "application/json", headers = "application/json")
	Product findProduct(@RequestParam("idCode") String productCode);

}
