package com.web.panacea.service;

import com.web.panacea.domain.Phase;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhaseServiceImpl implements PhaseService {

    public long countAllPhases() {
        return Phase.countPhases();
    }

    public void deletePhase(Phase phase) {
        phase.remove();
    }

    public Phase findPhase(Long id) {
        return Phase.findPhase(id);
    }

    public List<Phase> findAllPhases() {
        return Phase.findAllPhases();
    }

    public List<Phase> findPhaseEntries(int firstResult, int maxResults) {
        return Phase.findPhaseEntries(firstResult, maxResults);
    }

    public void savePhase(Phase phase) {
        phase.persist();
    }

    public Phase updatePhase(Phase phase) {
        return phase.merge();
    }
}
