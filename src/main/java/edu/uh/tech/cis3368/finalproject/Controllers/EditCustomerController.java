package edu.uh.tech.cis3368.finalproject.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.uh.tech.cis3368.finalproject.Entities.Customer;
import edu.uh.tech.cis3368.finalproject.Repositories.CustomerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class EditCustomerController {
    Stage stage;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MainController mainController;

    @FXML
    protected JFXTextField custFnameTxt, custLnameTxt, custPhoneTxt, custEmailTxt;

    @FXML
    private JFXButton saveBtn, cancelBtn;

    @FXML
    public void initialize( ) {

    }

    protected Customer customer;
    @PostConstruct
    public void init() {

    }

    public void buttonEvent(ActionEvent event) {
        if (event.getSource() == saveBtn)
        {
            if(custFnameTxt.getText() != null && custLnameTxt.getText() != null && custPhoneTxt.getText() != null)
            {Customer customer1 = customerRepository.findCustById(customer.getCustomerId());
                customer1.setCustomerFirstName(custFnameTxt.getText());
                customer1.setCustomerLastName(custLnameTxt.getText());
                customer1.setCustomerPhoneNumber(custPhoneTxt.getText());
                customer1.setCustomerEmail(custEmailTxt.getText());
                customerRepository.save(customer1);

                custFnameTxt.setText(null); custLnameTxt.setText(null); custPhoneTxt.setText(null);
                custPhoneTxt.setText(null);


                stage.close();

                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setContentText("You have successfully edited a customer");
                    alert.showAndWait();}



            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setContentText("Please Enter Values in all of the Required Fields");
                alert.showAndWait();}
            mainController.loadCustData();
        }

        else if(event.getSource() == cancelBtn)
        {

            stage.close();



        }
    }

}

