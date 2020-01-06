package pt.nos.ms.something.routing;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pt.nos.ms.something.dal.SomethingDAL;
import pt.nos.ms.something.dto.SomethingDTO;

@RestController
@RequestMapping("/somethings")
public class SomethingController {
    
    private @Autowired SomethingDAL somethingDAL;
    
    
    @GetMapping
    public ResponseEntity<List<SomethingDTO>> getSomethings(@RequestParam(required = false) Optional<String> name) {
        return ResponseEntity.ok(somethingDAL.getSomethings(name));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SomethingDTO> getSomething(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(somethingDAL.getSomething(id));
    }
    
    @PostMapping
    public ResponseEntity<SomethingDTO> createSomething(@RequestBody SomethingDTO something) {
        return ResponseEntity.ok(somethingDAL.createSomething(something));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SomethingDTO> updateSomething(@PathVariable Long id, @RequestBody SomethingDTO something) throws Exception {
        return ResponseEntity.ok(somethingDAL.updateSomething(id, something));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<SomethingDTO> patchTimestamp(@PathVariable Long id, @RequestBody SomethingDTO something) throws Exception {
        return ResponseEntity.ok(somethingDAL.patchTimestamp(id, something));
    }
    
    @DeleteMapping("/{id}")
    public void deleteSomething(@PathVariable Long id) {
        somethingDAL.deleteSomething(id);
    }
    
    @PostMapping("/upload")
    public ResponseEntity<List<SomethingDTO>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Path path = Paths.get(System.getProperty("java.io.tmpdir"), LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "-" + file.getOriginalFilename());
        OutputStream os = Files.newOutputStream(path);
        os.write(file.getBytes());
        os.close();
        
        return ResponseEntity.ok(somethingDAL.uploadFile(path));
    }
    
    @PostMapping("/publish")
    public void sendMessage(@RequestParam("message") String message) {
        somethingDAL.sendMessage(message);
    }
    
    @GetMapping("/webservice")
    public void callWebService() {
        somethingDAL.getListWS();
        somethingDAL.getListWithFiltersWS("something");
        somethingDAL.getObjectWS(1);

        SomethingDTO something = new SomethingDTO();
        something.setId(1);
        something.setFullName("One One");
        something.setTimestamp(LocalDateTime.now());

        somethingDAL.createObjectWS(something);
        somethingDAL.updateObjectWS(something);
        somethingDAL.patchObjectWS(something);
        somethingDAL.deleteObjectWS(3);
    }
    
}
