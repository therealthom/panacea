package com.web.panacea.service;

import com.web.panacea.domain.Log;
import java.util.List;

public interface LogService {

    public abstract long countAllLogs();

    public abstract void deleteLog(Log log);

    public abstract Log findLog(Long id);

    public abstract List<Log> findAllLogs();

    public abstract List<Log> findLogEntries(int firstResult, int maxResults);

    public abstract void saveLog(Log log);

    public abstract Log updateLog(Log log);

}
