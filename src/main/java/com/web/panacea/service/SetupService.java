package com.web.panacea.service;

import com.web.panacea.domain.Setup;
import java.util.List;

public interface SetupService {

    public abstract long countAllSetups();

    public abstract void deleteSetup(Setup setup);

    public abstract Setup findSetup(Long id);

    public abstract List<Setup> findAllSetups();

    public abstract List<Setup> findSetupEntries(int firstResult, int maxResults);

    public abstract void saveSetup(Setup setup);

    public abstract Setup updateSetup(Setup setup);

}
