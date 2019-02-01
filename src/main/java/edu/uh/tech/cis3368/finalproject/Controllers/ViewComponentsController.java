package edu.uh.tech.cis3368.finalproject.Controllers;

import com.jfoenix.controls.JFXButton;
import edu.uh.tech.cis3368.finalproject.Entities.Component;
import edu.uh.tech.cis3368.finalproject.Repositories.ComponentRepository;
import edu.uh.tech.cis3368.finalproject.SceneRootHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.Set;

public class ViewComponentsController {
    private Stage addCompStage, editCompStage;
    protected Stage stage;

    @Autowired
    @Qualifier("createComponent")
    SceneRootHandler.FXML addCompRoot;

    @Autowired
    @Qualifier("editComponent")
    SceneRootHandler.FXML editCompRoot;

    @Autowired
    CreateComponentsController createComponentsController;

    @Autowired
    EditComponentController editComponentController;

    @Autowired
    ComponentRepository componentRepository;


    @FXML
    private TableView<Component> compTable;

    @FXML
    private TableColumn<Component, Integer> compIdCol;

    @FXML
    private TableColumn<Component, String> compNameCol;

    @FXML
    private TableColumn<Component, Double> compPriceCol;

    @FXML
    private JFXButton compEditBtn, compDeleteBtn;

    private ObservableList<Component> compData;
    private int addcompnum = 0;
    private int editcompnum = 0;


    @FXML
    public void initialize( ) {

    }

    @PostConstruct
    public void init() {

        loadComp();
        compEditBtn.setDisable(true);
        compDeleteBtn.setDisable(true);
        compTable.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            compEditBtn.setDisable(false);
            compDeleteBtn.setDisable(false);
        });

    }

    public void loadComp()
    {
        Set<Component> components = componentRepository.findAllComponents();
        compData = FXCollections.observableArrayList(components);

        compIdCol.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentId"));
        compNameCol.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
        compPriceCol.setCellValueFactory(new PropertyValueFactory<Component, Double>("componentPrice"));

        compTable.setItems(compData);
    }

    public void addCompEvent(ActionEvent event) {
        while (addcompnum < 1)
        {
            addCompStage = new Stage();
            addCompStage.setTitle("Add a Component");
            addCompStage.setScene(new Scene(addCompRoot.getSceneroot()));
            addCompStage.setResizable(true);
            addCompStage.centerOnScreen();
            addCompStage.initModality(Modality.APPLICATION_MODAL);

            addcompnum++;

        }


        addCompStage.show();
        createComponentsController.stage= addCompStage;

        compEditBtn.setDisable(true);
        compDeleteBtn.setDisable(true);

    }

    public void editCompEvent(ActionEvent event) {

        while (editcompnum < 1)
        {
            editCompStage = new Stage();
            editCompStage.setTitle("Add a Component");
            editCompStage.setScene(new Scene(editCompRoot.getSceneroot()));
            editCompStage.setResizable(true);
            editCompStage.centerOnScreen();
            editCompStage.initModality(Modality.APPLICATION_MODAL);

            editcompnum++;

        }


        editCompStage.show();
        editComponentController.stage= editCompStage;

        Component component = compTable.getSelectionModel().getSelectedItem();
        editComponentController.component = component;
        editComponentController.compNametxt.setText(component.getComponentName());
        editComponentController.compPriceTxt.setText(String.valueOf(component.getComponentPrice()));


        compEditBtn.setDisable(true);
        compDeleteBtn.setDisable(true);

    }

    public void deleteCompEvent(ActionEvent event) {
        Component component = compTable.getSelectionModel().getSelectedItem();
        componentRepository.deleteById(component.getComponentId());
        compEditBtn.setDisable(true);
        compDeleteBtn.setDisable(true);
        loadComp();

    }
}
