package edu.uh.tech.cis3368.finalproject;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class JavaFxHandler extends Application{
    private static String[] savedArgs;


    protected ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception { // this method is called at launch time and initialize all spring components
        context = SpringApplication.run(getClass(), savedArgs); // telling the application javafx is controlled by spring
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected static void launchApp(Class<? extends JavaFxHandler> appClass, String[] args) {
        JavaFxHandler.savedArgs = args;
        Application.launch(appClass, args);
    }
}



