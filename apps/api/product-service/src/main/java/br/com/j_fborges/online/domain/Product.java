package br.com.j_fborges.online.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product{
	
	public enum Status {
		ACTIVE, INACTIVE;
	}

    @Id
    private String id;

    @NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
    private String idCode;

    @NotNull
	@Size(min = 1, max = 50)
    private String title;

    private BigDecimal price;

    @NotNull
	@Size(min = 1, max = 50)
    private String category;

    @NotNull
	@Size(min = 1, max = 150)
    private String description;
    
    private Status status;

    public Product(String id, String title, String idCode, String price, String category, String description){
        this.id = id;
        this.title = title;
        this.idCode = idCode;
        this.price = new BigDecimal(price);
        this.category = category;
        this.description = description;
    }

    public Product(String title, String idCode, String price, String category, String description){
        this.title = title;
        this.idCode = idCode;
        this.price = new BigDecimal(price);
        this.category = category;
        this.description = description;
    }
}
