package com.web.panacea.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Configurable
public class Setup implements Serializable {

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String svnHost;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String svnPort;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String svnUsername;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String svnPassword;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String jenkinsHost;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String jenkinsPort;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String jenkinsUsername;

    /**
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String jenkinsPassword;

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

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Setup().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countSetups() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Setup o", Long.class).getSingleResult();
    }

    public static List<Setup> findAllSetups() {
        return entityManager().createQuery("SELECT o FROM Setup o", Setup.class).getResultList();
    }

    public static Setup findSetup(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Setup.class, id);
    }

    public static List<Setup> findSetupEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Setup o", Setup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Setup attached = Setup.findSetup(this.id);
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
    public Setup merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Setup merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String getSvnHost() {
        return this.svnHost;
    }

    public void setSvnHost(String svnHost) {
        this.svnHost = svnHost;
    }

    public String getSvnPort() {
        return this.svnPort;
    }

    public void setSvnPort(String svnPort) {
        this.svnPort = svnPort;
    }

    public String getSvnUsername() {
        return this.svnUsername;
    }

    public void setSvnUsername(String svnUsername) {
        this.svnUsername = svnUsername;
    }

    public String getSvnPassword() {
        return this.svnPassword;
    }

    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }

    public String getJenkinsHost() {
        return this.jenkinsHost;
    }

    public void setJenkinsHost(String jenkinsHost) {
        this.jenkinsHost = jenkinsHost;
    }

    public String getJenkinsPort() {
        return this.jenkinsPort;
    }

    public void setJenkinsPort(String jenkinsPort) {
        this.jenkinsPort = jenkinsPort;
    }

    public String getJenkinsUsername() {
        return this.jenkinsUsername;
    }

    public void setJenkinsUsername(String jenkinsUsername) {
        this.jenkinsUsername = jenkinsUsername;
    }

    public String getJenkinsPassword() {
        return this.jenkinsPassword;
    }

    public void setJenkinsPassword(String jenkinsPassword) {
        this.jenkinsPassword = jenkinsPassword;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
