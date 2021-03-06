package com.web.panacea.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable

public class Login implements Serializable {

    /**
     */
    @NotNull
    @Size(min = 2, max = 40)
    private String username;

    /**
     */
    @NotNull
    @Size(min = 2, max = 40)
    private String password;

    @NotNull
    @Size(min = 2, max = 40)
    private String userRol;
    
    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Login().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static List<Login> findAllLogins() {
        return entityManager().createQuery("SELECT o FROM Login o", Login.class).getResultList();
    }

    public static Login findLogin(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Login.class, id);
    }

    public static Login findLoginByUsernameAndPassword(String username, String password) {
        TypedQuery<Login> query = entityManager().createQuery("SELECT o FROM Login o WHERE o.username = :username AND o.password = :password ", Login.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Login login = null;
        try {
            login = query.getSingleResult();
        } catch (Exception e) {
        }
        return login;
    }

    public static List<Login> findLoginEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Login o", Login.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Login attached = Login.findLogin(this.id);
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
    public Login merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Login merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userRol
     */
    public String getUserRol() {
        return userRol;
    }

    /**
     * @param userRol the userRol to set
     */
    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }
}
