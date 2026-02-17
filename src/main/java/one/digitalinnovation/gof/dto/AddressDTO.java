package one.digitalinnovation.gof.dto;

public record AddressDTO(
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
