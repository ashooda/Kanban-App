package edu.uh.tech.cis3368.finalproject.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.uh.tech.cis3368.finalproject.Entities.Component;
import edu.uh.tech.cis3368.finalproject.Entities.Customer;
import edu.uh.tech.cis3368.finalproject.Entities.Job;
import edu.uh.tech.cis3368.finalproject.Entities.Jobcomponent;
import edu.uh.tech.cis3368.finalproject.Repositories.ComponentRepository;
import edu.uh.tech.cis3368.finalproject.Repositories.CustomerRepository;
import edu.uh.tech.cis3368.finalproject.Repositories.JobComponetRepository;
import edu.uh.tech.cis3368.finalproject.Repositories.JobRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Array;
import java.util.ArrayList;

public class CreateJobController {

    protected Stage stage;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    MainController mainController;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobComponetRepository jobComponetRepository;

    @FXML
    private JFXTextField phoneTxt, lnameTxt, priceTxt, prodNameTxt;

    @FXML
    private JFXTextField comp1qtyTxt, comp2qtyTxt, comp3qtyTxt, comp4qtyTxt, comp5qtyTxt;

    @FXML
    private JFXButton custLookBtn;

    @FXML
    private JFXButton addComp2Btn, addComp3Btn, addComp4Btn, addComp5Btn;

    @FXML
    private JFXButton remComp2Btn, remComp3Btn, remComp4Btn, remComp5Btn;

    @FXML
    private JFXComboBox<String> comp1Combo, comp2Combo, comp3Combo, comp4Combo, comp5Combo;

    @FXML
    public void initialize( ) {

    }
    private Customer customer;
    private Job job;
    private Jobcomponent jobcomponent1, jobcomponent2, jobcomponent3, jobcomponent4, jobcomponent5;
    private Component component;
    private double total_price = 0;
    private double profit = 0;
    private ArrayList<String> componentsData;
    private ObservableList<String> componentsComboData;


    @PostConstruct
    public void init() {
        componentsData = componentRepository.findComponentsListArray();
        componentsComboData = FXCollections.observableArrayList(componentsData);

        comp1Combo.getItems().addAll(componentsComboData);
        comp2Combo.getItems().addAll(componentsComboData);
        comp3Combo.getItems().addAll(componentsComboData);
        comp4Combo.getItems().addAll(componentsComboData);
        comp5Combo.getItems().addAll(componentsComboData);

        priceTxt.setText(String.valueOf(total_price));







        lnameTxt.setDisable(true);

        comp2Combo.setDisable(true);
        comp3Combo.setDisable(true);
        comp4Combo.setDisable(true);
        comp5Combo.setDisable(true);
        remComp2Btn.setDisable(true);
        remComp3Btn.setDisable(true);
        remComp4Btn.setDisable(true);
        remComp5Btn.setDisable(true);
        comp2qtyTxt.setDisable(true);
        comp3qtyTxt.setDisable(true);
        comp4qtyTxt.setDisable(true);
        comp5qtyTxt.setDisable(true);
        priceTxt.setDisable(true);





    }



    public void lookupCust(ActionEvent event) {
        customer = customerRepository.findByPhone(phoneTxt.getText());
        if (customer == null)
        { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setContentText("A customer with this number does not exist. Please add the customer before continuing");
            alert.showAndWait();}
        else
        {lnameTxt.setText(customer.getCustomerLastName());}



    }

    public void addComp(ActionEvent event) {
        if (event.getSource() == addComp2Btn)
        {
            comp2Combo.setDisable(false);
            remComp2Btn.setDisable(false);
            comp2qtyTxt.setDisable(false);
        }
        else if(event.getSource() == addComp3Btn)
        {
            comp3Combo.setDisable(false);
            remComp3Btn.setDisable(false);
            comp3qtyTxt.setDisable(false);
        }

        else if(event.getSource() == addComp4Btn)
        {
            comp4Combo.setDisable(false);
            remComp4Btn.setDisable(false);
            comp4qtyTxt.setDisable(false);
        }
        else if(event.getSource() == addComp5Btn)
        {
            comp5Combo.setDisable(false);
            remComp5Btn.setDisable(false);
            comp5qtyTxt.setDisable(false);
        }
    }

    public void remComp(ActionEvent event) {
        if (event.getSource() == remComp2Btn)
        {
            comp2Combo.setDisable(true); comp2Combo.setValue(null);
            remComp2Btn.setDisable(true);
            comp2qtyTxt.setDisable(true); comp1qtyTxt.setText(null);
        }
        else if(event.getSource() == remComp3Btn)
        {
            comp3Combo.setDisable(true); comp3Combo.setValue(null);
            remComp3Btn.setDisable(true);
            comp3qtyTxt.setDisable(true); comp3qtyTxt.setText(null);
        }

        else if(event.getSource() == remComp4Btn)
        {
            comp4Combo.setDisable(true); comp4Combo.setValue(null);
            remComp4Btn.setDisable(true);
            comp4qtyTxt.setDisable(true); comp4qtyTxt.setText(null);
        }
        else if(event.getSource() == remComp5Btn)
        {
            comp5Combo.setDisable(true); comp5Combo.setValue(null);
            remComp5Btn.setDisable(true);
            comp5qtyTxt.setDisable(true); comp5qtyTxt.setText(null);
        }
    }

    public void jobSaveEvent(ActionEvent event) {

        if((comp1Combo.getValue() != null && comp1qtyTxt == null) || (comp2Combo.getValue() != null && comp2qtyTxt == null) || (comp3Combo.getValue() != null && comp3qtyTxt == null) || (comp4Combo.getValue() != null && comp4qtyTxt == null) || (comp5Combo.getValue() != null && comp5qtyTxt == null))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setContentText("Please Enter a Quantity for each added component");
            alert.showAndWait();
        }

       else if (lnameTxt.getText() != null && prodNameTxt.getText() != null && comp1Combo.getValue() != null && comp1qtyTxt != null)
        {
            job = new Job();
            job.setCustomerId(customer.getCustomerId());
            job.setJobStatusId(1);
            job.setProductName(prodNameTxt.getText());
            job.setTotalPrice((double)total_price);
            job.setProfit((double)profit);
            jobRepository.save(job);

            jobcomponent1 = new Jobcomponent();
            jobcomponent1.setJobId(job.getJobId());
            jobcomponent1.setComponentId(componentRepository.findIdByCompName(comp1Combo.getValue()));
            jobcomponent1.setQuantity(Integer.valueOf(comp1qtyTxt.getText()));
            jobComponetRepository.save(jobcomponent1);

            if (comp2Combo.getValue() != null)
            {
                jobcomponent2 = new Jobcomponent();
                jobcomponent2.setJobId(job.getJobId());
                jobcomponent2.setComponentId(componentRepository.findIdByCompName(comp2Combo.getValue()));
                jobcomponent2.setQuantity(Integer.valueOf(comp2qtyTxt.getText()));
                jobComponetRepository.save(jobcomponent2);
            }

            if (comp3Combo.getValue() != null)
            {
                jobcomponent3 = new Jobcomponent();
                jobcomponent3.setJobId(job.getJobId());
                jobcomponent3.setComponentId(componentRepository.findIdByCompName(comp3Combo.getValue()));
                jobcomponent3.setQuantity(Integer.valueOf(comp3qtyTxt.getText()));
                jobComponetRepository.save(jobcomponent3);
            }

            if (comp4Combo.getValue() != null)
            {
                jobcomponent4 = new Jobcomponent();
                jobcomponent4.setJobId(job.getJobId());
                jobcomponent4.setComponentId(componentRepository.findIdByCompName(comp4Combo.getValue()));
                jobcomponent4.setQuantity(Integer.valueOf(comp4qtyTxt.getText()));
                jobComponetRepository.save(jobcomponent4);
            }

            if (comp5Combo.getValue() != null)
            {
                jobcomponent5 = new Jobcomponent();
                jobcomponent5.setJobId(job.getJobId());
                jobcomponent5.setComponentId(componentRepository.findIdByCompName(comp5Combo.getValue()));
                jobcomponent5.setQuantity(Integer.valueOf(comp5qtyTxt.getText()));
                jobComponetRepository.save(jobcomponent5);
            }


            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("You have successfully created a job");
                alert.showAndWait();}
                mainController.loadPreProdJobs();

                stage.close();


        }
        else if(lnameTxt.getText() == null || prodNameTxt.getText() == null || comp1Combo.getValue() == null || comp1qtyTxt == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setContentText("Please Enter values in the required fields");
            alert.showAndWait();
        }

    }

    public void computePrice(ActionEvent event) {
        if(comp1Combo.getValue() == null && comp2Combo.getValue() == null && comp3Combo.getValue() == null && comp4Combo.getValue() == null && comp5Combo.getValue() == null)
        {
            total_price = 0;
            profit = 0;
        }
        if ( comp1Combo.getValue() != null && comp1qtyTxt != null)
        {
            float comp1Price = componentRepository.findPriceByCompName(comp1Combo.getValue());
            float cost = comp1Price * Integer.valueOf(comp1qtyTxt.getText());
            profit = profit + (float)(cost * 0.1);
            total_price = total_price + cost + (float)(cost * 0.134);


        }
        if ( comp2Combo.getValue() != null && comp2qtyTxt != null)
        {
            float comp2Price = componentRepository.findPriceByCompName(comp2Combo.getValue());
            float cost = comp2Price * Integer.valueOf(comp2qtyTxt.getText());
            profit = profit + (float)(cost * 0.1);
            total_price = total_price + cost + (float)(cost * 0.134);


        }
        if ( comp3Combo.getValue() != null && comp3qtyTxt != null)
        {
            float comp3Price = componentRepository.findPriceByCompName(comp3Combo.getValue());
            float cost = comp3Price * Integer.valueOf(comp3qtyTxt.getText());
            profit = profit + (float)(cost * 0.1);
            total_price = total_price + cost + (float)(cost * 0.134);


        }
        if ( comp4Combo.getValue() != null && comp4qtyTxt != null)
        {
            float comp4Price = componentRepository.findPriceByCompName(comp4Combo.getValue());
            float cost = comp4Price * Integer.valueOf(comp4qtyTxt.getText());
            profit = profit + (float)(cost * 0.1);
            total_price = total_price + cost + (float)(cost * 0.134);


        }

        if ( comp5Combo.getValue() != null && comp5qtyTxt != null)
        {
            float comp5Price = componentRepository.findPriceByCompName(comp5Combo.getValue());
            float cost = comp5Price * Integer.valueOf(comp5qtyTxt.getText());
            profit = profit + (float)(cost * 0.1);
            total_price = total_price + cost + (float)(cost * 0.134);


        }
        priceTxt.setText(String.valueOf(total_price));

    }

    public void jobCloseEvent(ActionEvent event) {
       stage.close();
    }
}
