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

public class EditEmployeeController {
    Stage stage;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MainController mainController;

    @FXML
    protected JFXTextField empFnameTxt, empLnameTxt, empPhoneTxt, empEmailTxt;



    @FXML
    private JFXButton saveBtn, cancelBtn;

    @FXML
    public void initialize( ) {

    }

    protected Employee employee;
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
            {Employee employee1 = employeeRepository.findEmpById(employee.getEmployeeId());
                employee1.setEmployeeFirstName(empFnameTxt.getText());
                employee1.setEmployeeLastName(empLnameTxt.getText());
                employee1.setEmployeePhoneNumber(empPhoneTxt.getText());
                employee1.setEmployeeEmail(empEmailTxt.getText());
                employeeRepository.save(employee1);

                empFnameTxt.setText(null); empLnameTxt.setText(null); empPhoneTxt.setText(null);
                empPhoneTxt.setText(null);


                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("You have successfully edited an employee");
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


