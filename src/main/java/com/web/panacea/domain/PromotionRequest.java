package com.web.panacea.domain;

import static com.web.panacea.domain.Project.entityManager;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

@Entity
@Configurable
public class PromotionRequest implements Serializable {

    /**
     */
    @NotNull
    @ManyToOne
    private Project project;

    /**
     */
    @Size(min = 1, max = 100)
    private String username;
        
    /**
     */
    @NotNull
    @Size(min = 1, max = 255)
    private String comments;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dateCreated;

    /**
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "promotionRequest")
    private Set<Phase> phases = new HashSet<Phase>();

    /**
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "promotionRequest")
    private Set<Document> documents = new HashSet<Document>();

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<Phase> getPhases() {
        return this.phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    public Set<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
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

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new PromotionRequest().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countPromotionRequests() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PromotionRequest o", Long.class).getSingleResult();
    }

    public static List<PromotionRequest> findAllPromotionRequests() {
        return entityManager().createQuery("SELECT o FROM PromotionRequest o", PromotionRequest.class).getResultList();
    }

    public static PromotionRequest findPromotionRequest(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(PromotionRequest.class, id);
    }
    
    public static PromotionRequest findByProject(Project project) {
        TypedQuery<PromotionRequest> query = entityManager().createQuery("SELECT o FROM PromotionRequest o WHERE o.project = :thisProject ORDER BY o.id DESC", PromotionRequest.class);
        List<PromotionRequest> resultados = query.setParameter("thisProject", project).getResultList();
        return resultados.get(0);
    }

    public static List<PromotionRequest> findPromotionRequestEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PromotionRequest o", PromotionRequest.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            PromotionRequest attached = PromotionRequest.findPromotionRequest(this.id);
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
    public PromotionRequest merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        PromotionRequest merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
