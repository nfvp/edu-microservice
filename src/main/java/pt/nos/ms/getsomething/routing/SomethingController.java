package pt.nos.ms.getsomething.routing;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import pt.nos.ms.getsomething.dal.SomethingDAL;
import pt.nos.ms.getsomething.dto.SomethingDTO;

@RestController
@RequestMapping("/somethings")
public class SomethingController {
    
    private @Autowired SomethingDAL somethingDAL;
    
    
    @GetMapping
    public ResponseEntity<List<SomethingDTO>> getSomethings() {
        return ResponseEntity.ok(somethingDAL.getSomethings());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SomethingDTO> getSomething(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(somethingDAL.getSomething(id));
    }
    
    @PostMapping
    public ResponseEntity<SomethingDTO> createSomething(@RequestBody SomethingDTO Something) {
        return ResponseEntity.ok(somethingDAL.createSomething(Something));
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
    public ResponseEntity<?> deleteSomething(@PathVariable Long id) {
        somethingDAL.deleteSomething(id);
        return ResponseEntity.ok(null);
    }
    
}
