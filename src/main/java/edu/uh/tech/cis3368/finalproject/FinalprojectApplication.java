package edu.uh.tech.cis3368.finalproject;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FinalprojectApplication extends JavaFxHandler{

    @Autowired
    @Qualifier("main")
    SceneRootHandler.FXML ccomproot;
@Override
   public void start(Stage stage) throws Exception {
        stage.setTitle("3368 Final Project");

        stage.setScene(new Scene(ccomproot.getSceneroot()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args) {
        launchApp(FinalprojectApplication.class, args);
    }

    /*

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Drag and Drop Demo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
     */

}
