package br.com.j_fborges.online.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductQuantity {
	
	@NotNull
	private Product product;
	
	@NotNull
	private Integer quantity;
	
	private BigDecimal totalValue;
	
	public ProductQuantity() {
		this.quantity = 0;
		this.totalValue = BigDecimal.ZERO;
	}

	public void addQuantity(Integer quantity) {
		this.quantity += quantity;
		BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
		BigDecimal newTotal = this.totalValue.add(newValue);
		this.totalValue = newTotal;
	}
	
	public void removeQuantity(Integer quantity) {
		this.quantity -= quantity;
		BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
		this.totalValue = this.totalValue.subtract(newValue);
	}
	
	
}
