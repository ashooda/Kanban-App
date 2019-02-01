package edu.uh.tech.cis3368.finalproject.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Component implements Serializable {
    private int componentId;
    private String componentName;
    private Double componentPrice;
    private Collection<Jobcomponent> jobcomponentsByComponentId;

    public Component() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Basic
    @Column(name = "component_name")
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    @Basic
    @Column(name = "component_price")
    public Double getComponentPrice() {
        return componentPrice;
    }

    public void setComponentPrice(Double componentPrice) {
        this.componentPrice = componentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return componentId == component.componentId &&
                Objects.equals(componentName, component.componentName) &&
                Objects.equals(componentPrice, component.componentPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentId, componentName, componentPrice);
    }

    @OneToMany(mappedBy = "componentByComponentId")
    public Collection<Jobcomponent> getJobcomponentsByComponentId() {
        return jobcomponentsByComponentId;
    }

    public void setJobcomponentsByComponentId(Collection<Jobcomponent> jobcomponentsByComponentId) {
        this.jobcomponentsByComponentId = jobcomponentsByComponentId;
    }
}
