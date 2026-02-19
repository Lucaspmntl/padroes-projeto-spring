package one.digitalinnovation.gof.dto;

public record AddressDTO(

    // TODO: Beans validation para validação prática de dados
    String cep,
    String street,
    String complement,
    String neighborhood,
    String location,
    String uf,
    String ibge,
    String gia,
    String ddd,
    String siafi
) {
}
