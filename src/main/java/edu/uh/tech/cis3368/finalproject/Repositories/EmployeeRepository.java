package edu.uh.tech.cis3368.finalproject.Repositories;

import edu.uh.tech.cis3368.finalproject.Entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>, Repository<Employee, Integer> {
    @Query(value="select * from employee", nativeQuery = true)
    Set<Employee> findAllEmployees();

    @Query("select e from Employee e where e.employeeId = ?1")
    Employee findEmpById(int num);

}
