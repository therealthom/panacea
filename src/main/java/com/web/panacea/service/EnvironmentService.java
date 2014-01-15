package com.web.panacea.service;

import com.web.panacea.domain.Environment;
import java.util.List;

public interface EnvironmentService {

    public abstract long countAllEnvironments();

    public abstract void deleteEnvironment(Environment environment);

    public abstract Environment findEnvironment(Long id);

    public abstract List<Environment> findAllEnvironments();

    public abstract List<Environment> findEnvironmentEntries(int firstResult, int maxResults);

    public abstract void saveEnvironment(Environment environment);

    public abstract Environment updateEnvironment(Environment environment);

}
