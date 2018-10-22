package com.srb.project.model;

import javax.persistence.*;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "audits", schema = "srb")
public class AuditsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAUDITS", nullable = false)
    private int idaudits;

    @Basic
    @Column(name = "IDUSERS", nullable = false)
    private int idusers;

    @Basic
    @Column(name = "TYPEOPERATION", nullable = true, length = 45)
    private String typeoperation;

    @Basic
    @Column(name = "IP", nullable = true, length = 45)
    private String ip;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDITSDATE", nullable = true)
    private Date auditDate;

    @Basic
    @Column(name = "TIMEAUDIT", nullable = true)
    private Time timeaudit;

    @Basic
    @Column(name = "STATUSOPERATION", nullable = true)
    private Integer statusoperation;
    @Basic
    @Column(name = "CONTENT", nullable = true, length = 3000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "IDUSERS", referencedColumnName = "IDUSERS",insertable = false, updatable = false, nullable = false)
    private UsersEntity usersByIdusers;

    @PrePersist
    public void prePersist() {
        auditDate =  new java.util.Date();
    }

    public void setIdaudits(Integer idaudits) {
        this.idaudits = idaudits;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }


    public int getIdaudits() {
        return idaudits;
    }

    public void setIdaudits(int idaudits) {
        this.idaudits = idaudits;
    }


    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }


    public String getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(String typeoperation) {
        this.typeoperation = typeoperation;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditsdate) {
        this.auditDate = auditsdate;
    }


    public Time getTimeaudit() {
        return timeaudit;
    }

    public void setTimeaudit(Time timeaudit) {
        this.timeaudit = timeaudit;
    }


    public Integer getStatusoperation() {
        return statusoperation;
    }

    public void setStatusoperation(Integer statusoperation) {
        this.statusoperation = statusoperation;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditsEntity that = (AuditsEntity) o;

        if (idaudits != that.idaudits) return false;
        if (idusers != that.idusers) return false;
        if (typeoperation != null ? !typeoperation.equals(that.typeoperation) : that.typeoperation != null)
            return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (auditDate != null ? !auditDate.equals(that.auditDate) : that.auditDate != null) return false;
        if (timeaudit != null ? !timeaudit.equals(that.timeaudit) : that.timeaudit != null) return false;
        if (statusoperation != null ? !statusoperation.equals(that.statusoperation) : that.statusoperation != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idaudits;
        result = 31 * result + idusers;
        result = 31 * result + (typeoperation != null ? typeoperation.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (auditDate != null ? auditDate.hashCode() : 0);
        result = 31 * result + (timeaudit != null ? timeaudit.hashCode() : 0);
        result = 31 * result + (statusoperation != null ? statusoperation.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }


    public UsersEntity getUsersByIdusers() {
        return usersByIdusers;
    }

    public void setUsersByIdusers(UsersEntity usersByIdusers) {
        this.usersByIdusers = usersByIdusers;
    }
}
