package edu.uh.tech.cis3368.finalproject.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Customer implements Serializable {
    private int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhoneNumber;
    private Collection<Job> jobsByCustomerId;

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customer_first_name")
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    @Basic
    @Column(name = "customer_last_name")
    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    @Basic
    @Column(name = "customer_email")
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Basic
    @Column(name = "customer_phone_number")
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Objects.equals(customerFirstName, customer.customerFirstName) &&
                Objects.equals(customerLastName, customer.customerLastName) &&
                Objects.equals(customerEmail, customer.customerEmail) &&
                Objects.equals(customerPhoneNumber, customer.customerPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerFirstName, customerLastName, customerEmail, customerPhoneNumber);
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<Job> getJobsByCustomerId() {
        return jobsByCustomerId;
    }

    public void setJobsByCustomerId(Collection<Job> jobsByCustomerId) {
        this.jobsByCustomerId = jobsByCustomerId;
    }
}
