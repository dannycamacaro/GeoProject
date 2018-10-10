package com.srb.project.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "audits", schema = "srb")
public class AuditsEntity {
    private int idaudits;
    private int idusers;
    private String typeoperation;
    private String ip;
    private Date auditsdate;
    private Time timeaudit;
    private Integer statusoperation;
    private String content;

    @Id
    @Column(name = "IDAUDITS", nullable = false)
    public int getIdaudits() {
        return idaudits;
    }

    public void setIdaudits(int idaudits) {
        this.idaudits = idaudits;
    }

    @Basic
    @Column(name = "IDUSERS", nullable = false)
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Basic
    @Column(name = "TYPEOPERATION", nullable = true, length = 45)
    public String getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(String typeoperation) {
        this.typeoperation = typeoperation;
    }

    @Basic
    @Column(name = "IP", nullable = true, length = 45)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "AUDITSDATE", nullable = true)
    public Date getAuditsdate() {
        return auditsdate;
    }

    public void setAuditsdate(Date auditsdate) {
        this.auditsdate = auditsdate;
    }

    @Basic
    @Column(name = "TIMEAUDIT", nullable = true)
    public Time getTimeaudit() {
        return timeaudit;
    }

    public void setTimeaudit(Time timeaudit) {
        this.timeaudit = timeaudit;
    }

    @Basic
    @Column(name = "STATUSOPERATION", nullable = true)
    public Integer getStatusoperation() {
        return statusoperation;
    }

    public void setStatusoperation(Integer statusoperation) {
        this.statusoperation = statusoperation;
    }

    @Basic
    @Column(name = "CONTENT", nullable = true, length = 3000)
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
        if (auditsdate != null ? !auditsdate.equals(that.auditsdate) : that.auditsdate != null) return false;
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
        result = 31 * result + (auditsdate != null ? auditsdate.hashCode() : 0);
        result = 31 * result + (timeaudit != null ? timeaudit.hashCode() : 0);
        result = 31 * result + (statusoperation != null ? statusoperation.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
