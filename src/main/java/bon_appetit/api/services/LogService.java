package bon_appetit.api.services;

import bon_appetit.api.models.Log;
import bon_appetit.api.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Log create(Log log) {
        return logRepository.save(log);
    }

    public Iterable<Log> findAll() {
        return logRepository.findAll();
    }
}
