package br.com.j_fborges.online.domain;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "consumer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name="Consumer", description="Consumer") 
public class Consumer{

    @Id
    @Schema(description = "Unique identifier")
    private String id;

    @NotNull
    @Size(min=1, max=50)
    @Schema(description = "Name", minLength = 1, maxLength = 50, nullable = false)
    private String name;

    @NotNull
    @Indexed(unique = true, background = true)
    @Schema(description = "IdNumber", nullable = false)
    private Long idNumber;

    @NotNull
    @Size(min = 1, max = 50)
    @Indexed(unique = true, background = true)
    @Schema(description = "Email", minLength = 1, maxLength = 50, nullable = false)
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid Email")
    private String email;

    @NotNull
	@Schema(description="Telephone", nullable = false) 
    private Long tel;

    @NotNull
	@Size(min = 1, max = 50)
	@Schema(description="Address", minLength = 1, maxLength=50, nullable = false)
    private String address;

    @NotNull
	@Schema(description="Address Number", nullable = false) 
    private Integer addressNumber;

    @NotNull
	@Size(min = 1, max = 50)
	@Schema(description="City", minLength = 1, maxLength=50, nullable = false)
    private String city;

    @NotNull
	@Size(min = 1, max = 50)
	@Schema(description="State", minLength = 1, maxLength=50, nullable = false)
    private String state;

    public Consumer(String id, String name, String idNumber, String email, String tel, String address, String addressNumber, String city, String state) {
        this.id = id;
        this.name = name;
        this.idNumber = Long.valueOf(idNumber);
        this.email = email;
        this.tel = Long.valueOf(tel);
        this.address = address;
        this.addressNumber = Integer.valueOf(addressNumber);
        this.city = city;
        this.state = state;
    }

    public Consumer(String name, String idNumber, String email, String tel, String address, String addressNumber, String city, String state) {
        this.name = name;
        this.idNumber = Long.valueOf(idNumber);
        this.email = email;
        this.tel = Long.valueOf(tel);
        this.address = address;
        this.addressNumber = Integer.valueOf(addressNumber);
        this.city = city;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(idNumber, consumer.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idNumber);
    }
}
