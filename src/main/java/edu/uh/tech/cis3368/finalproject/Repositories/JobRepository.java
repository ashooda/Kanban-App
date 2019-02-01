package edu.uh.tech.cis3368.finalproject.Repositories;

import edu.uh.tech.cis3368.finalproject.Entities.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Integer>, Repository<Job, Integer> {
    @Query(value="select * from job where job_status_id = 1", nativeQuery = true)
    Set<Job> findAllPreProdJobs();

    @Query(value="select * from job where job_status_id = 2", nativeQuery = true)
    Set<Job> findAllProdJobs();

    @Query(value="select * from job where job_status_id = 3", nativeQuery = true)
    Set<Job> findAllClOutJobs();

    @Query(value="select * from job where job_status_id = 4", nativeQuery = true)
    Set<Job> findAllArchiveJobs();

    @Query("select j from Job j where j.jobId = ?1")
    Job findJobById(int num);
}
