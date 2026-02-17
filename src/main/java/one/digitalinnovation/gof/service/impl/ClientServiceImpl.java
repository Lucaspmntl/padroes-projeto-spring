package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.dto.ClientDTO;
import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.repository.ClientRepository;
import one.digitalinnovation.gof.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    // TODO: Singleton: Injetar os componentes spring com AutoWired
    // TODO: Strategy: Implementar os metodos definidos na interface
    // TODO: Facade: Abstrair integrações com subsistemas, provendo uma interface simples

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Iterable<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientDTO::fromEntity)
                .toList();
    }

    @Override
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id "+id));

        return ClientDTO.fromEntity(client);
    }
    
    @Override
    public ClientDTO insert(ClientDTO dto) {
        Client insertedClient = clientRepository.save(new Client(dto));
        return ClientDTO.fromEntity(insertedClient);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        Client oldClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));

        oldClient.setName(dto.name());
        oldClient.setAddress(dto.address());

        Client updatedClient = clientRepository.save(oldClient);
        return ClientDTO.fromEntity(updatedClient);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
