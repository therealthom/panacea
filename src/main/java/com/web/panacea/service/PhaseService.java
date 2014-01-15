package com.web.panacea.service;

import com.web.panacea.domain.Phase;
import java.util.List;

public interface PhaseService {

    public abstract long countAllPhases();

    public abstract void deletePhase(Phase phase);

    public abstract Phase findPhase(Long id);

    public abstract List<Phase> findAllPhases();

    public abstract List<Phase> findPhaseEntries(int firstResult, int maxResults);

    public abstract void savePhase(Phase phase);

    public abstract Phase updatePhase(Phase phase);

}
