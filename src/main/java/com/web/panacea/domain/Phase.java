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
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Configurable
public class Phase implements Serializable {

    /**
     */
    @NotNull
    @ManyToOne
    private PromotionRequest promotionRequest;

    /**
     */
    private Boolean promotionResult;

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

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public PromotionRequest getPromotionRequest() {
        return this.promotionRequest;
    }

    public void setPromotionRequest(PromotionRequest promotionRequest) {
        this.promotionRequest = promotionRequest;
    }

    public Boolean getPromotionResult() {
        return this.promotionResult;
    }

    public void setPromotionResult(Boolean promotionResult) {
        this.promotionResult = promotionResult;
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Phase().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countPhases() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Phase o", Long.class).getSingleResult();
    }

    public static List<Phase> findAllPhases() {
        return entityManager().createQuery("SELECT o FROM Phase o", Phase.class).getResultList();
    }

    public static Phase findPhase(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Phase.class, id);
    }

    public static List<Phase> findPhaseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Phase o", Phase.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Phase attached = Phase.findPhase(this.id);
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
    public Phase merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Phase merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
