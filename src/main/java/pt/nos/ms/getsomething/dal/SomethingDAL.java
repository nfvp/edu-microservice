package pt.nos.ms.getsomething.dal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.nos.ms.getsomething.dao.SomethingDAO;
import pt.nos.ms.getsomething.dto.SomethingDTO;
import pt.nos.ms.getsomething.repository.SomethingRepository;
import pt.nos.ms.getsomething.utils.MappingUtils;

@Component
public class SomethingDAL {
    
    private @Autowired SomethingRepository somethingRepository;
    
    
    public List<SomethingDTO> getSomethings() {
        return somethingRepository.findAll().stream().map(MappingUtils::mapToSomethingDTO).collect(Collectors.toList());
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
    
}
