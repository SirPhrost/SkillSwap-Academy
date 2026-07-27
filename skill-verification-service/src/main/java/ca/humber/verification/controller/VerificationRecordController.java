package ca.humber.verification.controller;

import ca.humber.verification.model.VerificationRecord;
import ca.humber.verification.model.VerificationStatus;
import ca.humber.verification.service.RecordNotFoundException;
import ca.humber.verification.service.VerificationRecordService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/verifications")
public class VerificationRecordController {
    private final VerificationRecordService service;

    public VerificationRecordController(VerificationRecordService service) { this.service = service; }

    @GetMapping
    public List<VerificationRecord> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public VerificationRecord getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public ResponseEntity<VerificationRecord> create(@Valid @RequestBody VerificationRecord record) {
        VerificationRecord saved = service.create(record);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public VerificationRecord update(@PathVariable Long id, @Valid @RequestBody VerificationRecord record) {
        return service.update(id, record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<VerificationRecord> search(@RequestParam(required = false) String skillName,
                                           @RequestParam(required = false) VerificationStatus status,
                                           @RequestParam(required = false) Integer minScore) {
        return service.search(skillName, status, minScore);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Map<String, String>> notFound(RecordNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
    }
}
