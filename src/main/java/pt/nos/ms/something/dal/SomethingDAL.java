package pt.nos.ms.something.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.nos.ms.something.dao.SomethingDAO;
import pt.nos.ms.something.dto.SomethingDTO;
import pt.nos.ms.something.repository.SomethingRepository;
import pt.nos.ms.something.services.ProducerMQ;
import pt.nos.ms.something.utils.MappingUtils;

@Component
public class SomethingDAL {
    
    private @Autowired SomethingRepository somethingRepository;
    
    private @Autowired ProducerMQ producerMQ;
    
    
    public List<SomethingDTO> getSomethings(String name) {
        if (name == null) {
            return somethingRepository.findAll().stream().map(MappingUtils::mapToSomethingDTO).collect(Collectors.toList());
        } else {
            return somethingRepository.findByOneNameOrAnotherName(name, name).stream().map(MappingUtils::mapToSomethingDTO).collect(Collectors.toList());
        }
    }
    
    public SomethingDTO getSomething(Long id) throws Exception {
        Optional<SomethingDAO> something = somethingRepository.findById(id);
        if (something.isPresent()) {
            return MappingUtils.mapToSomethingDTO(something.get());
        } else {
            throw new Exception();
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
    
    public SomethingDTO updateSomething(Long id, SomethingDTO something) throws Exception {
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
            throw new Exception();
        }
    }
    
    public SomethingDTO patchTimestamp(Long id, SomethingDTO something) throws Exception {
        Optional<SomethingDAO> somethingDB = somethingRepository.findById(id);
        if (somethingDB.isPresent()) {
            SomethingDAO somethingToSave = somethingDB.get();
            somethingToSave.setTimestamp(something.getTimestamp());
            somethingToSave.setUpdatedAt(LocalDateTime.now());
            somethingToSave.setUpdatedBy("user");
            return MappingUtils.mapToSomethingDTO(somethingRepository.save(somethingToSave));
        } else {
            throw new Exception();
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
    
}
