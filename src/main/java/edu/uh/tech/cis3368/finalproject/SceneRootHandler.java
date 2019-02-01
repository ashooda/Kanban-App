package edu.uh.tech.cis3368.finalproject;

import edu.uh.tech.cis3368.finalproject.Controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class SceneRootHandler {


    @Bean
    @Qualifier("main")
    public FXML getMainScene() throws IOException {
        return loadfxml("mainPane.fxml");
    }
    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainScene().getController();
    }

    @Bean
    @Qualifier("editJob")
    public FXML getEJobScene() throws IOException {
        return loadfxml("editJob.fxml");
    }
    @Bean
    public EditJobController getEJobController() throws IOException {
        return (EditJobController) getEJobScene().getController();
    }

    @Bean
    @Qualifier("createJob")
    public FXML getCJobScene() throws IOException {
        return loadfxml("createJob.fxml");
    }

    @Bean
    public CreateJobController getCJobController() throws IOException {
        return (CreateJobController) getCJobScene().getController();
    }

    @Bean
    @Qualifier("createCustomer")
    public FXML getCCustomerScene() throws IOException {
        return loadfxml("createCustomer.fxml");
    }

    @Bean
    public CreateCustomerController getCCustomerController() throws IOException {
        return (CreateCustomerController) getCCustomerScene().getController();
    }

    @Bean
    @Qualifier("editCustomer")
    public FXML getECustomerScene() throws IOException {
        return loadfxml("editCustomer.fxml");
    }

    @Bean
    public EditCustomerController getECustomerController() throws IOException {
        return (EditCustomerController) getECustomerScene().getController();
    }

    @Bean
    @Qualifier("createEmployee")
    public FXML getCEmpScene() throws IOException {
        return loadfxml("createEmployee.fxml");
    }

    @Bean
    public CreateEmployeeController getCEmpController() throws IOException {
        return (CreateEmployeeController) getCEmpScene().getController();
    }

    @Bean
    @Qualifier("editEmployee")
    public FXML getEEmpScene() throws IOException {
        return loadfxml("editEmployee.fxml");
    }

    @Bean
    public EditEmployeeController getEEmpController() throws IOException {
        return (EditEmployeeController) getEEmpScene().getController();
    }


    @Bean
    @Qualifier("createComponent")
    public FXML getCComponentScene() throws IOException {
        return loadfxml("createComponent.fxml");
    }

    @Bean
    public CreateComponentsController getCCompentController() throws IOException {
        return (CreateComponentsController) getCComponentScene().getController();
    }

    @Bean
    @Qualifier("editComponent")
    public FXML getEComponentScene() throws IOException {
        return loadfxml("editComponent.fxml");
    }

    @Bean
    public EditComponentController getECompentController() throws IOException {
        return (EditComponentController) getEComponentScene().getController();
    }

    @Bean
    @Qualifier("viewComponents")
    public FXML getVComponentScene() throws IOException {
        return loadfxml("viewComponents.fxml");
    }

    @Bean
    public ViewComponentsController getVCompentController() throws IOException {
        return (ViewComponentsController) getVComponentScene().getController();
    }





    protected FXML loadfxml(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new FXML(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }




    public class FXML {
        private Parent sceneroot;
        private Object controller;

        public FXML(Parent sceneroot, Object controller) {
            this.sceneroot = sceneroot;
            this.controller = controller;
        }

        public Parent getSceneroot() {
            return sceneroot;
        }

        public Object getController() {
            return controller;
        }


    }

}
