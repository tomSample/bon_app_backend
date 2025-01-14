package bon_appetit.api.controllers;

import bon_appetit.api.models.Log;
import bon_appetit.api.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/logs")
@CrossOrigin(origins = "http://localhost:5173")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping
    public ResponseEntity<Log> createLog(@RequestBody Log log) {
        Log createdLog = logService.create(log);
        return ResponseEntity.ok(createdLog);
    }

    @GetMapping
    public ResponseEntity<Iterable<Log>> getAllLogs() {
        Iterable<Log> logs = logService.findAll();
        return ResponseEntity.ok(logs);
    }
}
