package com.web.panacea.service;

import com.web.panacea.domain.Environment;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {

    public long countAllEnvironments() {
        return Environment.countEnvironments();
    }

    public void deleteEnvironment(Environment environment) {
        environment.remove();
    }

    public Environment findEnvironment(Long id) {
        return Environment.findEnvironment(id);
    }

    public List<Environment> findAllEnvironments() {
        return Environment.findAllEnvironments();
    }

    public List<Environment> findEnvironmentEntries(int firstResult, int maxResults) {
        return Environment.findEnvironmentEntries(firstResult, maxResults);
    }

    public void saveEnvironment(Environment environment) {
        environment.persist();
    }

    public Environment updateEnvironment(Environment environment) {
        return environment.merge();
    }
}
