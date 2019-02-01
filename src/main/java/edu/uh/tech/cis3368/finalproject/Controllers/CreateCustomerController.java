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

public class CreateCustomerController {
    Stage stage;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MainController mainController;

    @FXML
    private JFXTextField custFnameTxt, custLnameTxt, custPhoneTxt, custEmailTxt;



    @FXML
    private JFXButton saveBtn, cancelBtn;

    @FXML
    public void initialize( ) {

    }

    private Customer customer;
    @PostConstruct
    public void init() {
        custFnameTxt.setText(null); custLnameTxt.setText(null); custPhoneTxt.setText(null);
        custPhoneTxt.setText(null);

    }

    public void buttonEvent(ActionEvent event) {
        if (event.getSource() == saveBtn)
        {

            if(custFnameTxt.getText() == null || custLnameTxt.getText() == null || custPhoneTxt.getText() == null)
            {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setContentText("Please Enter Values in all of the Required Fields");
            alert.showAndWait();}

            else if(custFnameTxt.getText() != null && custLnameTxt.getText() != null && custPhoneTxt.getText() != null)
            {Customer customer = new Customer();
                customer.setCustomerFirstName(custFnameTxt.getText());
                customer.setCustomerLastName(custLnameTxt.getText());
                customer.setCustomerPhoneNumber(custPhoneTxt.getText());
                customer.setCustomerEmail(custEmailTxt.getText());
                customerRepository.save(customer);

                custFnameTxt.setText(null); custLnameTxt.setText(null); custPhoneTxt.setText(null);
                custPhoneTxt.setText(null);


                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setContentText("You have successfully added a customer");
                    alert.showAndWait();



            }

            mainController.loadCustData();

        }

        else //if(event.getSource() == cancelBtn)
        {

          stage.close();



        }
    }

    }
