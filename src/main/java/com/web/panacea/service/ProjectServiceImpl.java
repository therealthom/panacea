package com.web.panacea.service;

import com.web.panacea.domain.Project;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    public long countAllProjects() {
        return Project.countProjects();
    }

    public void deleteProject(Project project) {
        project.remove();
    }

    public Project findProject(Long id) {
        return Project.findProject(id);
    }

    public List<Project> findAllProjects() {
        return Project.findAllProjects();
    }

    public List<Project> findProjectEntries(int firstResult, int maxResults) {
        return Project.findProjectEntries(firstResult, maxResults);
    }

    public void saveProject(Project project) {
        project.persist();
    }

    public Project updateProject(Project project) {
        return project.merge();
    }
    
    public Project findProjectByName(String name) {
        return Project.findProjectByName(name);
    };
}
