package com.web.panacea.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Configurable
public class Project implements Serializable {

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String packaging;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String groupId;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", unique=true)
    private String name;

    @NotNull
    private Boolean active = true;

    /**
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Environment> environments = new HashSet<Environment>();

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Project().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countProjects() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Project o", Long.class).getSingleResult();
    }

    public static List<Project> findAllProjects() {
        return entityManager().createQuery("SELECT o FROM Project o ORDER BY o.name", Project.class).getResultList();
    }

    public static Project findProject(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Project.class, id);
    }

    public static Project findProjectByName(String name) {
        TypedQuery<Project> query = entityManager().createQuery("SELECT o FROM Project o WHERE o.name = :thisName", Project.class);
        return query.setParameter("thisName", name).getSingleResult();
    }

    public static List<Project> findProjectEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Project o", Project.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Transactional
    public void persist() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Project attached = Project.findProject(this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.clear();
    }

    @Transactional
    public Project merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Project merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Environment> getEnvironments() {
        return this.environments;
    }

    public void setEnvironments(Set<Environment> environments) {
        this.environments = environments;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the packaging
     */
    public String getPackaging() {
        return packaging;
    }

    /**
     * @param packaging the packaging to set
     */
    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
