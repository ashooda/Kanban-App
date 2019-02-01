package edu.uh.tech.cis3368.finalproject.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Job implements Serializable {
    private int jobId;
    private int customerId;
    private int jobStatusId;
    private String productName;
    private Double totalPrice;
    private Double profit;
    private Customer customerByCustomerId;
    private Jobstatus jobstatusByJobStatusId;
    private Collection<Jobcomponent> jobcomponentsByJobId;

    public Job() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "job_status_id")
    public int getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(int jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "total_price")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "profit")
    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobId == job.jobId &&
                customerId == job.customerId &&
                jobStatusId == job.jobStatusId &&
                Objects.equals(productName, job.productName) &&
                Objects.equals(totalPrice, job.totalPrice) &&
                Objects.equals(profit, job.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, customerId, jobStatusId, productName, totalPrice, profit);
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false,nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "job_status_id", referencedColumnName = "job_status_id", insertable = false, updatable = false, nullable = false)
    public Jobstatus getJobstatusByJobStatusId() {
        return jobstatusByJobStatusId;
    }

    public void setJobstatusByJobStatusId(Jobstatus jobstatusByJobStatusId) {
        this.jobstatusByJobStatusId = jobstatusByJobStatusId;
    }

    @OneToMany(mappedBy = "jobByJobId")
    public Collection<Jobcomponent> getJobcomponentsByJobId() {
        return jobcomponentsByJobId;
    }

    public void setJobcomponentsByJobId(Collection<Jobcomponent> jobcomponentsByJobId) {
        this.jobcomponentsByJobId = jobcomponentsByJobId;
    }
}
