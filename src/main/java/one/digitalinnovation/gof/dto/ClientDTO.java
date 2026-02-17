package one.digitalinnovation.gof.dto;

import one.digitalinnovation.gof.model.Address;
import one.digitalinnovation.gof.model.Client;

public record ClientDTO(
        long id,
        String name,
        Address address
) {
    public static ClientDTO fromEntity(Client entity){
        return new ClientDTO(entity.getId(), entity.getName(), entity.getAddress());
    }

}
