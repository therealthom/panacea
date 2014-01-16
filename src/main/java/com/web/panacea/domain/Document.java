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
import javax.validation.constraints.Size;

@Entity
@Configurable
public class Document implements Serializable {

    private byte [] file;
    /**
     */
    @NotNull
    @ManyToOne
    private PromotionRequest promotionRequest;

    /**
     */
    @NotNull
    @Size(min = 2, max = 100)
    private String filename;

    public PromotionRequest getPromotionRequest() {
        return this.promotionRequest;
    }

    public void setPromotionRequest(PromotionRequest promotionRequest) {
        this.promotionRequest = promotionRequest;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
        EntityManager em = new Document().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countDocuments() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Document o", Long.class).getSingleResult();
    }

    public static List<Document> findAllDocuments() {
        return entityManager().createQuery("SELECT o FROM Document o", Document.class).getResultList();
    }

    public static Document findDocument(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Document.class, id);
    }

    public static List<Document> findDocumentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Document o", Document.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Document attached = Document.findDocument(this.id);
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
    public Document merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Document merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * @return the file
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(byte[] file) {
        this.file = file;
    }
}
