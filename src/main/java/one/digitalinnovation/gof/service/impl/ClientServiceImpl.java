package one.digitalinnovation.gof.service.impl;

import jakarta.transaction.Transactional;
import one.digitalinnovation.gof.dto.ClientDTO;
import one.digitalinnovation.gof.model.Address;
import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.repository.AddressRepository;
import one.digitalinnovation.gof.repository.ClientRepository;
import one.digitalinnovation.gof.service.ClientService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    // TODO: Singleton: Injetar os componentes spring com AutoWired
    // TODO: Strategy: Implementar os metodos definidos na interface
    // TODO: Facade: Abstrair integrações com subsistemas, provendo uma interface simples

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Iterable<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientDTO::fromEntity)
                .toList();
    }

    @Override
    public ClientDTO findById(Long id) {
        // TODO: Criar uma exceção personalizada (ex: ResourceNotFoundException) e @ControllerAdvice para tratar o erro globalmente

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id "+id));

        return ClientDTO.fromEntity(client);
    }
    
    @Override
    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        // TODO: Tratamento de exceções personalizado

        // TODO: Adicionar validação defensiva: verificar possiveis dados nulos
        String cep = dto.address().getCep();

        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.consultCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });

        Client clientToSave = new Client(dto);
        clientToSave.setAddress(address);

        Client insertedClient = clientRepository.save(clientToSave);
        return ClientDTO.fromEntity(insertedClient);
    }

    @Override
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {

        Client oldClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));

        String clientCep = dto.address().getCep();

        Address address = addressRepository.findById(clientCep)
                .orElseGet(() -> {
                    Address newAddress = viaCepService.consultCep(clientCep);
                    addressRepository.save(newAddress);
                    return newAddress;
                });

        oldClient.setName(dto.name());
        oldClient.setAddress(address);

        Client updatedClient = clientRepository.save(oldClient);
        return ClientDTO.fromEntity(updatedClient);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
