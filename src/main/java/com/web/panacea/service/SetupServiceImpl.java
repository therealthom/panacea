package com.web.panacea.service;

import com.web.panacea.domain.Setup;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetupServiceImpl implements SetupService {

    public long countAllSetups() {
        return Setup.countSetups();
    }

    public void deleteSetup(Setup setup) {
        setup.remove();
    }

    public Setup findSetup(Long id) {
        return Setup.findSetup(id);
    }

    public List<Setup> findAllSetups() {
        return Setup.findAllSetups();
    }

    public List<Setup> findSetupEntries(int firstResult, int maxResults) {
        return Setup.findSetupEntries(firstResult, maxResults);
    }

    public void saveSetup(Setup setup) {
        setup.persist();
    }

    public Setup updateSetup(Setup setup) {
        return setup.merge();
    }
}
