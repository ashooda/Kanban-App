package edu.uh.tech.cis3368.finalproject.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Jobstatus implements Serializable {
    private int jobStatusId;
    private String jobStatus;
    private Collection<Job> jobsByJobStatusId;

    public Jobstatus() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_status_id")
    public int getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(int jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    @Basic
    @Column(name = "job_status")
    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jobstatus jobstatus = (Jobstatus) o;
        return jobStatusId == jobstatus.jobStatusId &&
                Objects.equals(jobStatus, jobstatus.jobStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobStatusId, jobStatus);
    }

    @OneToMany(mappedBy = "jobstatusByJobStatusId")
    public Collection<Job> getJobsByJobStatusId() {
        return jobsByJobStatusId;
    }

    public void setJobsByJobStatusId(Collection<Job> jobsByJobStatusId) {
        this.jobsByJobStatusId = jobsByJobStatusId;
    }
}
