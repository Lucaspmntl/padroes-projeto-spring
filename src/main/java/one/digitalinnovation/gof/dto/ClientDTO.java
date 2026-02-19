package one.digitalinnovation.gof.dto;

import one.digitalinnovation.gof.model.Address;
import one.digitalinnovation.gof.model.Client;

public record ClientDTO(
        // TODO: Beans validation para validação prática de dados
        long id,

        // TODO: Adicionar anotações como @NotBlank e @Size(min/max)
        String name,

        // TODO: Substituir a entidade 'Address' por 'AddressDTO' para evitar expor a entidade de banco diretamente
        // TODO: Adicionar @NotNull e @Valid para validar o objeto aninhado
        Address address
) {
    public static ClientDTO fromEntity(Client entity){
        return new ClientDTO(entity.getId(), entity.getName(), entity.getAddress());
    }
}
