package edu.uh.tech.cis3368.finalproject.Repositories;

import edu.uh.tech.cis3368.finalproject.Entities.Jobstatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface JobStatusRepository extends CrudRepository<Jobstatus, Integer>, Repository<Jobstatus, Integer> {

}
