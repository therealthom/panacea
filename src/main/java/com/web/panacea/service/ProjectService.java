package com.web.panacea.service;

import com.web.panacea.domain.Project;
import java.util.List;

public interface ProjectService {

    public abstract long countAllProjects();

    public abstract void deleteProject(Project project);

    public abstract Project findProject(Long id);

    public abstract List<Project> findAllProjects();

    public abstract List<Project> findProjectEntries(int firstResult, int maxResults);

    public abstract void saveProject(Project project);

    public abstract Project updateProject(Project project);

}
