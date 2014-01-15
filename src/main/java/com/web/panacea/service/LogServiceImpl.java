package com.web.panacea.service;

import com.web.panacea.domain.Log;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    public long countAllLogs() {
        return Log.countLogs();
    }

    public void deleteLog(Log log) {
        log.remove();
    }

    public Log findLog(Long id) {
        return Log.findLog(id);
    }

    public List<Log> findAllLogs() {
        return Log.findAllLogs();
    }

    public List<Log> findLogEntries(int firstResult, int maxResults) {
        return Log.findLogEntries(firstResult, maxResults);
    }

    public void saveLog(Log log) {
        log.persist();
    }

    public Log updateLog(Log log) {
        return log.merge();
    }
}
