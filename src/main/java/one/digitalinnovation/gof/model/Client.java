package one.digitalinnovation.gof.model;

import jakarta.persistence.*;
import lombok.*;
import one.digitalinnovation.gof.dto.ClientDTO;
import org.springframework.beans.BeanUtils;
import tools.jackson.databind.util.BeanUtil;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Address address;

    public Client(ClientDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
