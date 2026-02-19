package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.dto.ClientDTO;
import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Iterable <ClientDTO>> findAll(){
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    // TODO: Adicionar @Valid ao @RequestBody para ativar as validações do DTO
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.insert(dto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto, @PathVariable long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
