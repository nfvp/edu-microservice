package pt.nos.ms.something.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pt.nos.ms.something.dao.SomethingDAO;
import pt.nos.ms.something.dto.SomethingDTO;
import pt.nos.ms.something.exception.SomethingNotFoundException;
import pt.nos.ms.something.repository.SomethingRepository;
import pt.nos.ms.something.services.ProducerMQ;
import pt.nos.ms.something.utils.MappingUtils;

@Component
public class SomethingDAL {
    
    private @Autowired SomethingRepository somethingRepository;
    
    private @Autowired ProducerMQ producerMQ;
    
    private @Autowired RestTemplate restTemplate;
    
    
    public List<SomethingDTO> getSomethings(Optional<String> name) {
        if (name.isPresent()) {
            return somethingRepository.findByOneNameOrAnotherName(name.get(), name.get()).stream().map(MappingUtils::mapToSomethingDTO).collect(Collectors.toList());
        } else {
            return somethingRepository.findAll().stream().map(MappingUtils::mapToSomethingDTO).collect(Collectors.toList());
        }
    }
    
    public SomethingDTO getSomething(Long id) throws SomethingNotFoundException {
        Optional<SomethingDAO> something = somethingRepository.findById(id);
        if (something.isPresent()) {
            return MappingUtils.mapToSomethingDTO(something.get());
        } else {
            throw new SomethingNotFoundException();
        }
    }
    
    public SomethingDTO createSomething(SomethingDTO something) {
        SomethingDAO somethingToSave = MappingUtils.mapToSomethingDAO(something);
        somethingToSave.setCreatedAt(LocalDateTime.now());
        somethingToSave.setCreatedBy("user");
        somethingToSave.setUpdatedAt(LocalDateTime.now());
        somethingToSave.setUpdatedBy("user");
        return MappingUtils.mapToSomethingDTO(somethingRepository.save(somethingToSave));
    }
    
    public SomethingDTO updateSomething(Long id, SomethingDTO something) throws SomethingNotFoundException {
        Optional<SomethingDAO> somethingDB = somethingRepository.findById(id);
        if (somethingDB.isPresent()) {
            SomethingDAO somethingToSave = somethingDB.get();
            somethingToSave.setOneName(something.getFullName().split(" ")[0]);
            somethingToSave.setAnotherName(something.getFullName().split(" ")[1]);
            somethingToSave.setTimestamp(something.getTimestamp());
            somethingToSave.setUpdatedAt(LocalDateTime.now());
            somethingToSave.setUpdatedBy("user");
            return MappingUtils.mapToSomethingDTO(somethingRepository.save(somethingToSave));
        } else {
            throw new SomethingNotFoundException();
        }
    }
    
    public SomethingDTO patchTimestamp(Long id, SomethingDTO something) throws SomethingNotFoundException {
        Optional<SomethingDAO> somethingDB = somethingRepository.findById(id);
        if (somethingDB.isPresent()) {
            SomethingDAO somethingToSave = somethingDB.get();
            somethingToSave.setTimestamp(something.getTimestamp());
            somethingToSave.setUpdatedAt(LocalDateTime.now());
            somethingToSave.setUpdatedBy("user");
            return MappingUtils.mapToSomethingDTO(somethingRepository.save(somethingToSave));
        } else {
            throw new SomethingNotFoundException();
        }
    }
    
    public void deleteSomething(Long id) {
        somethingRepository.deleteById(id);
    }
    
    public List<SomethingDTO> uploadFile(Path path) throws IOException {
        List<SomethingDTO> somethingsToAdd = Files.lines(path).map(MappingUtils::mapToSomethingDTO).collect(Collectors.toList());
        List<SomethingDTO> somethingsAdded = somethingsToAdd.stream().map(this::createSomething).collect(Collectors.toList());
        return somethingsAdded;
    }
    
    public void sendMessage(String message) {
        producerMQ.sendMessage(message);
    }
    
    public List<SomethingDTO> getListWS() {
        ResponseEntity<SomethingDTO[]> response = restTemplate.getForEntity("/", SomethingDTO[].class);
        return Arrays.asList(response.getBody());
    }
    
    public List<SomethingDTO> getListWithFiltersWS(String name) {
        ResponseEntity<SomethingDTO[]> response = restTemplate.getForEntity("/?name=" + name, SomethingDTO[].class);
        return Arrays.asList(response.getBody());
    }
    
    public SomethingDTO getObjectWS(int id) {
        ResponseEntity<SomethingDTO> response = restTemplate.getForEntity("/{id}", SomethingDTO.class, id);
        return response.getBody();
    }
    
    public SomethingDTO createObjectWS(SomethingDTO something) {
        ResponseEntity<SomethingDTO> response = restTemplate.postForEntity("/", something, SomethingDTO.class);
        return response.getBody();
    }
    
    public SomethingDTO updateObjectWS(SomethingDTO something) {
        ResponseEntity<SomethingDTO> response = restTemplate.exchange("/{id}", HttpMethod.PUT, new HttpEntity<Object>(something), SomethingDTO.class, something.getId());
        return response.getBody();
    }
    
    public SomethingDTO patchObjectWS(SomethingDTO something) {
        ResponseEntity<SomethingDTO> response = restTemplate.exchange("/{id}", HttpMethod.PATCH, new HttpEntity<Object>(something), SomethingDTO.class, something.getId());
        return response.getBody();
    }
    
    public void deleteObjectWS(int id) {
        restTemplate.delete("/{id}", id);
    }
    
}
