package edu.uh.tech.cis3368.finalproject.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Jobcomponent implements Serializable {
    private int jobComponentId;
    private int jobId;
    private int componentId;
    private Integer quantity;
    private Job jobByJobId;
    private Component componentByComponentId;

    public Jobcomponent() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_component_id")
    public int getJobComponentId() {
        return jobComponentId;
    }

    public void setJobComponentId(int jobComponentId) {
        this.jobComponentId = jobComponentId;
    }

    @Basic
    @Column(name = "job_id")
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "component_id")
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jobcomponent that = (Jobcomponent) o;
        return jobComponentId == that.jobComponentId &&
                jobId == that.jobId &&
                componentId == that.componentId &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobComponentId, jobId, componentId, quantity);
    }

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id", insertable = false, updatable = false, nullable = false)
    public Job getJobByJobId() {
        return jobByJobId;
    }

    public void setJobByJobId(Job jobByJobId) {
        this.jobByJobId = jobByJobId;
    }

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false, nullable = false)
    public Component getComponentByComponentId() {
        return componentByComponentId;
    }

    public void setComponentByComponentId(Component componentByComponentId) {
        this.componentByComponentId = componentByComponentId;
    }
}
