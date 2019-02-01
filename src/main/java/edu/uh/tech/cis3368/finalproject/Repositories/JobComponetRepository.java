package edu.uh.tech.cis3368.finalproject.Repositories;

import edu.uh.tech.cis3368.finalproject.Entities.Jobcomponent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.sql.Array;
import java.util.ArrayList;

public interface JobComponetRepository extends CrudRepository<Jobcomponent, Integer>, Repository<Jobcomponent, Integer> {
    @Query(value= "select * from jobcomponent where job_id=?1", nativeQuery = true)
    Jobcomponent[] findJobComponentsByJobIdArray(int jobId);
}
