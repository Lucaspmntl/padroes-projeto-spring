package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "viacep.com.br/ws/")
public interface ViaCepService {

    @GetMapping("/{cep}/json")
    Address consultCep(@PathVariable String cep);
}