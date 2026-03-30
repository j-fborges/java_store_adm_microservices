package br.com.j_fborges.online.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	private String id;

	@NotNull
	@Size(min = 2, max = 10)
	private String idCode;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String title;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String description;
	
	@NotNull
	private BigDecimal price;

}
