package edu.uh.tech.cis3368.finalproject.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.uh.tech.cis3368.finalproject.Entities.Employee;
import edu.uh.tech.cis3368.finalproject.Repositories.EmployeeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class CreateEmployeeController {
    Stage stage;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MainController mainController;

    @FXML
    private JFXTextField empFnameTxt, empLnameTxt, empPhoneTxt, empEmailTxt;



    @FXML
    private JFXButton saveBtn, cancelBtn;

    @FXML
    public void initialize( ) {

    }

    private Employee employee;
    @PostConstruct
    public void init() {
        empFnameTxt.setText(null); empLnameTxt.setText(null); empPhoneTxt.setText(null);
        empPhoneTxt.setText(null);

    }

    public void buttonEvent(ActionEvent event) {
        if (event.getSource() == saveBtn)
        {

            if(empFnameTxt.getText() == null || empLnameTxt.getText() == null || empPhoneTxt.getText() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setContentText("Please Enter Values in all of the Required Fields");
                alert.showAndWait();}


                else if(empFnameTxt.getText() != null && empLnameTxt.getText() != null && empPhoneTxt.getText() != null)
            {Employee employee = new Employee();
                employee.setEmployeeFirstName(empFnameTxt.getText());
                employee.setEmployeeLastName(empLnameTxt.getText());
                employee.setEmployeePhoneNumber(empPhoneTxt.getText());
                employee.setEmployeeEmail(empEmailTxt.getText());
                employeeRepository.save(employee);

                empFnameTxt.setText(null); empLnameTxt.setText(null); empPhoneTxt.setText(null);
                empPhoneTxt.setText(null);


                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("You have successfully added an employee");
                alert.showAndWait();



            }

            mainController.loadEmpData();

        }

        else
        {

            stage.close();



        }
    }

}

