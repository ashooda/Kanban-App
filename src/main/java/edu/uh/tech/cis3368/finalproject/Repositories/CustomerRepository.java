package edu.uh.tech.cis3368.finalproject.Repositories;

import edu.uh.tech.cis3368.finalproject.Entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface CustomerRepository extends CrudRepository<Customer, Integer>, Repository<Customer, Integer> {

    @Query("select c from Customer c where c.customerPhoneNumber = ?1")
    Customer findByPhone(String phoneNumber);

    @Query(value="select * from customer", nativeQuery = true)
    Set<Customer> findAllCustomers();

    @Query("select c from Customer c where c.customerId = ?1")
    Customer findCustById(int num);
}
