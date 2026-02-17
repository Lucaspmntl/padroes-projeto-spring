package one.digitalinnovation.gof.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String cep;
    private String street;
    private String complement;
    private String neighborhood;
    private String location;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
