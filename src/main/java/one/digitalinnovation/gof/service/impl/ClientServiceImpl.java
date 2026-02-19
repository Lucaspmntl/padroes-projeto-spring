package one.digitalinnovation.gof.service.impl;

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
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id "+id));

        return ClientDTO.fromEntity(client);
    }
    
    @Override
    public ClientDTO insert(ClientDTO dto) {
        // FIXME Verificar se o endereço do cliente ja existe (Pelo CEP). -------------
        // FIXME Caso nao exista, integrar com o ViaCEP e persistir o retorno. ------------
        // FIXME Inserir cliente, vinculando o endereço (novo ou existente). ----------------

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
    public ClientDTO update(Long id, ClientDTO dto) {
        // FIXME Buscar cliente por id, caso exista: -------------------
        // FIXME Verificar se o endereço do cliente já existe (pelo CEP) --------------------
        // FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno --------------------
        // FIXME Alterar Cliente, vinculando o Endereço (novo ou existente) --------------------

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
