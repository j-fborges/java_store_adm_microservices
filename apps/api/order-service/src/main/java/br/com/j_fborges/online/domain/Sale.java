package br.com.j_fborges.online.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "venda")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Sale {
	
	public enum Status {
        STARTED, COMPLETED, CANCELLED;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if (status.name().equals(value)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    private String id;

    @NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
    private String code;

    @NotNull
    private String consumerId;

    private Set<ProductQuantity> saleProducts;

    private BigDecimal totalValue;

    @NotNull
    private Instant date;

    @NotNull
    private Status status;

    public Sale() {
        saleProducts = new HashSet<>();
    }

    public void addProductQuantity(Product product, Integer quantity) {
        validateStatus();
        Optional<ProductQuantity> op =
                saleProducts.stream().filter(filter -> filter.getProduct().getIdCode().equals(product.getIdCode())).findAny();
        if (op.isPresent()) {
            ProductQuantity produtpQtd = op.get();
            produtpQtd.addQuantity(quantity);
        } else {

            ProductQuantity prod = ProductQuantity.builder()
					.product(product)
					.totalValue(BigDecimal.ZERO)
					.quantity(0)
					.build();
            prod.addQuantity(quantity);
            saleProducts.add(prod);
        }
        recalculateSaleTotalValue();
    }

    public void validateStatus() {
        if (this.status == Status.COMPLETED) {
            throw new UnsupportedOperationException("IMPOSSIBLE TO EDIT COMPLETED SALE");
        }
    }

    public void removeProductQuantity(Product product, Integer quantity) {
        validateStatus();
        Optional<ProductQuantity> op =
                saleProducts.stream().filter(filter -> filter.getProduct().getIdCode().equals(product.getIdCode())).findAny();

        if (op.isPresent()) {
            ProductQuantity produtpQtd = op.get();
            if (produtpQtd.getQuantity()>quantity) {
                produtpQtd.removeQuantity(quantity);
                recalculateSaleTotalValue();
            } else {
                saleProducts.remove(op.get());
                recalculateSaleTotalValue();
            }

        }
    }

    public void removeAllQuantity() {
        validateStatus();
        saleProducts.clear();
        totalValue = BigDecimal.ZERO;
    }

    public Integer getQuantityTotalProducts() {
        int result = saleProducts.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
        return result;
    }

    public void recalculateSaleTotalValue() {
        validateStatus();
        BigDecimal totalValue = BigDecimal.ZERO;
        for (ProductQuantity prod : this.saleProducts) {
            totalValue = totalValue.add(prod.getTotalValue());
        }
        this.totalValue = totalValue;
    }
}
