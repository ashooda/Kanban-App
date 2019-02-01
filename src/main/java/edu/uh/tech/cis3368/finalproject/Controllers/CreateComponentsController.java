package edu.uh.tech.cis3368.finalproject.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.uh.tech.cis3368.finalproject.Entities.Component;
import edu.uh.tech.cis3368.finalproject.Repositories.ComponentRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class CreateComponentsController {
    @FXML
    Stage stage;

    @Autowired
    ViewComponentsController viewComponentsController;

    @Autowired
    ComponentRepository componentRepository;

    @FXML
    private JFXTextField compNametxt, compPriceTxt;

    @FXML
    private JFXButton saveBtn, cancelBtn;

    private Component component;

    @FXML
    public void initialize( ) {

    }

    @PostConstruct
    public void init() {

        compNametxt.setText(null);
        compPriceTxt.setText(null);





    }

    public void buttonEvent(ActionEvent event) {

        if (event.getSource() == cancelBtn)
        {

            stage.close();}
        else if (event.getSource() == saveBtn)
        {
            if(compNametxt.getText() != null && compPriceTxt.getText() != null)
            {
                component = new Component();
                component.setComponentName(compNametxt.getText());
                component.setComponentPrice(Double.valueOf(compPriceTxt.getText()));


                componentRepository.save(component);


                compNametxt.setText(null);
                compPriceTxt.setText(null);

                stage.close();

                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setContentText("You have successfully added a component");
                    alert.showAndWait();}
            }
            else if (compNametxt.getText() == null || compPriceTxt.getText() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setContentText("Please Enter Values in all of the text fields");
                alert.showAndWait();}
                viewComponentsController.loadComp();

        }
    }



}
