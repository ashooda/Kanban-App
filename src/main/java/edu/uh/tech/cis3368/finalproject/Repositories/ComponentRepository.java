package edu.uh.tech.cis3368.finalproject.Repositories;

import edu.uh.tech.cis3368.finalproject.Entities.Component;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.ArrayList;
import java.util.Set;

public interface ComponentRepository extends CrudRepository<Component, Integer>, Repository<Component, Integer> {
    @Query(value= "select component.component_name from component", nativeQuery = true)
    ArrayList<String> findComponentsListArray();

    @Query(value= "select component.component_price from component where component.component_name=?1" , nativeQuery = true)
    float findPriceByCompName(String compName);

    @Query(value= "select component.component_id from component where component.component_name=?1" , nativeQuery = true)
    int findIdByCompName(String compName);

    @Query(value= "select component.component_name from component where component.component_id=?1" , nativeQuery = true)
    String findCompNameById(int compId);

    @Query(value="select * from component", nativeQuery = true)
    Set<Component> findAllComponents();

    @Query("select c from Component c where c.componentId = ?1")
    Component findCompById(int num);
}

