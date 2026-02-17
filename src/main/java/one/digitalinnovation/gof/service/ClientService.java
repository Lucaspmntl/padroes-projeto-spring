package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.dto.ClientDTO;
import one.digitalinnovation.gof.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {

    public Iterable<ClientDTO> findAll();


    public ClientDTO findById(Long id);


    public ClientDTO insert(ClientDTO dto);


    public ClientDTO update(Long id, ClientDTO dto);


    public void delete(Long id);
}
