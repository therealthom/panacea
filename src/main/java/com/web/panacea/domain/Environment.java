package com.web.panacea.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

@Entity
@Configurable
public class Environment implements Serializable {

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String host;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String port;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String username;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String password;

    /**
     */
    @NotNull
    @ManyToOne
    private Project project;
    
    /**
     */
    private boolean active;

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Environment().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countEnvironments() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Environment o", Long.class).getSingleResult();
    }

    public static List<Environment> findAllEnvironments() {
        return entityManager().createQuery("SELECT o FROM Environment o", Environment.class).getResultList();
    }

    public static Environment findEnvironment(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Environment.class, id);
    }

    public static List<Environment> findEnvironmentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Environment o", Environment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Environment attached = Environment.findEnvironment(this.id);
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
    public Environment merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Environment merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive () {
        return this.active;
    }
    
    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
