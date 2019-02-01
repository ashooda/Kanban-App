package edu.uh.tech.cis3368.finalproject.Controllers;

import com.jfoenix.controls.JFXButton;
import edu.uh.tech.cis3368.finalproject.Entities.Customer;
import edu.uh.tech.cis3368.finalproject.Entities.Employee;
import edu.uh.tech.cis3368.finalproject.Entities.Job;
import edu.uh.tech.cis3368.finalproject.Entities.Jobcomponent;
import edu.uh.tech.cis3368.finalproject.Repositories.CustomerRepository;
import edu.uh.tech.cis3368.finalproject.Repositories.EmployeeRepository;
import edu.uh.tech.cis3368.finalproject.Repositories.JobComponetRepository;
import edu.uh.tech.cis3368.finalproject.Repositories.JobRepository;
import edu.uh.tech.cis3368.finalproject.SceneRootHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainController {

    private static final DataFormat PERSON_LIST = new DataFormat("cis3368/personList");


    private Stage addJobStage, editJobStage, addCustStage, editCustStage, addEmpStage, editEmpStage, viewCompStage;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CreateJobController createJobController;

    @Autowired
    EditJobController editJobController;

    @Autowired
    CreateCustomerController createCustomerController;

    @Autowired
    EditCustomerController editCustomerController;

    @Autowired
    CreateEmployeeController createEmployeeController;

    @Autowired
    EditEmployeeController editEmployeeController;

    @Autowired
    ViewComponentsController viewComponentsController;

    @Autowired
    JobComponetRepository jobComponetRepository;

    @Autowired
    @Qualifier("createJob")
    SceneRootHandler.FXML addJobroot;

    @Autowired
    @Qualifier("editJob")
    SceneRootHandler.FXML editJobroot;

    @Autowired
    @Qualifier("createCustomer")
    SceneRootHandler.FXML addCustroot;

    @Autowired
    @Qualifier("editCustomer")
    SceneRootHandler.FXML editCustRoot;

    @Autowired
    @Qualifier("createEmployee")
    SceneRootHandler.FXML addEmpRoot;

    @Autowired
    @Qualifier("editEmployee")
    SceneRootHandler.FXML editEmpRoot;

    @Autowired
    @Qualifier("viewComponents")
    SceneRootHandler.FXML viewComproot;


    @FXML
    private ListView<Job> preProdListView;
    @FXML
    private ListView<Job> prodListView;

    @FXML
    private ListView<Job> clOutListView;

    @FXML
    private ListView<Job> archiveListView;



    @FXML
    private TableView<Customer> custTable;

    @FXML
    private TableView<Employee> emplTable;

    @FXML
    private TableColumn<Employee, Integer> empIdCol;

    @FXML
    private TableColumn<Employee, String> empFnameCol;

    @FXML
    private TableColumn<Employee, String> empLnameCol;

    @FXML
    private TableColumn<Employee, String> empPhoneCol;

    @FXML
    private TableColumn<Employee, String> empEmailCol;




    @FXML
    private TableColumn<Customer, Integer> custIdCol;

    @FXML
    private TableColumn<Customer, String> custFnameCol;

    @FXML
    private TableColumn<Customer, String> custLnameCol;

    @FXML
    private TableColumn<Customer, String> custPhoneCol;

    @FXML
    private TableColumn<Customer, String> custEmailCol;

    @FXML
    private JFXButton jobDeleteBtn, jobEditBtn, custEditBtn, custDeleteBtn, empAddBtn, empEditBtn, empDeleteBtn;

    private int editnum = 0;
    private int addCustnum = 0;
    private int editCustnum = 0;
    private int addEmpnum = 0;
    private int editEmpnum = 0;
    private int addJobnum = 0;
    private int viewCompnum = 0;










    @FXML
    public void initialize( ) {

    }

    private ObservableList<Job> preProdData, prodData, clOutData, archiveData;

    private ObservableList<Customer> custData;

    private ObservableList<Employee> empData;
    @PostConstruct
    public void init() {
        loadPreProdJobs();
        loadCustData();
        loadEmpData();
        loadProdJobs();
        loadClOutJobs();
        loadArchiveJobs();


        custEditBtn.setDisable(true);
        custDeleteBtn.setDisable(true);
        custTable.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            custEditBtn.setDisable(false);
            custDeleteBtn.setDisable(false);
        });

        empEditBtn.setDisable(true);
        empDeleteBtn.setDisable(true);
        emplTable.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            empEditBtn.setDisable(false);
            empDeleteBtn.setDisable(false);
        });


        jobDeleteBtn.setDisable(true);
        jobEditBtn.setDisable(true);
        preProdListView.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            jobEditBtn.setDisable(false);
            jobDeleteBtn.setDisable(false);
        });




    }

    public void loadPreProdJobs()
    {

        Set<Job> jobs = jobRepository.findAllPreProdJobs();
        preProdData = FXCollections.observableArrayList(jobs);
        preProdListView.setCellFactory(new Callback<ListView<Job>, ListCell<Job>>() {
            @Override
            public ListCell<Job> call(ListView<Job> param) {
                return new ListCell<Job>(){
                    @Override
                    public void updateItem(Job job, boolean empty) {
                        super.updateItem(job, empty);
                        if (job == null) {
                            setText(null);
                        } else {
                            setText(job.getProductName());
                        }
                    }
                };
            }
        });
        preProdListView.setItems(preProdData);
        preProdListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void loadProdJobs()
    {

        Set<Job> jobs = jobRepository.findAllProdJobs();
        prodData = FXCollections.observableArrayList(jobs);
        prodListView.setCellFactory(new Callback<ListView<Job>, ListCell<Job>>() {
            @Override
            public ListCell<Job> call(ListView<Job> param) {
                return new ListCell<Job>(){
                    @Override
                    public void updateItem(Job job, boolean empty) {
                        super.updateItem(job, empty);
                        if (job == null) {
                            setText(null);
                        } else {
                            setText(job.getProductName());
                        }
                    }
                };
            }
        });
        prodListView.setItems(prodData);

    }

    public void loadClOutJobs()
    {

        Set<Job> jobs = jobRepository.findAllClOutJobs();
        clOutData = FXCollections.observableArrayList(jobs);
        clOutListView.setCellFactory(new Callback<ListView<Job>, ListCell<Job>>() {
            @Override
            public ListCell<Job> call(ListView<Job> param) {
                return new ListCell<Job>(){
                    @Override
                    public void updateItem(Job job, boolean empty) {
                        super.updateItem(job, empty);
                        if (job == null) {
                            setText(null);
                        } else {
                            setText(job.getProductName());
                        }
                    }
                };
            }
        });
        clOutListView.setItems(clOutData);

    }

    public void loadArchiveJobs()
    {

        Set<Job> jobs = jobRepository.findAllArchiveJobs();
        archiveData = FXCollections.observableArrayList(jobs);
        archiveListView.setCellFactory(new Callback<ListView<Job>, ListCell<Job>>() {
            @Override
            public ListCell<Job> call(ListView<Job> param) {
                return new ListCell<Job>(){
                    @Override
                    public void updateItem(Job job, boolean empty) {
                        super.updateItem(job, empty);
                        if (job == null) {
                            setText(null);
                        } else {
                            setText(job.getProductName());
                        }
                    }
                };
            }
        });
        archiveListView.setItems(archiveData);

    }


    public void loadCustData()
    {
        Set<Customer> customers = customerRepository.findAllCustomers();
        custData = FXCollections.observableArrayList(customers);

        custIdCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        custFnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerFirstName"));
        custLnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerLastName"));
        custEmailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerEmail"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhoneNumber"));


        custTable.setItems(custData);


    }

    public void loadEmpData()
    {
        Set<Employee> employees = employeeRepository.findAllEmployees();
        empData = FXCollections.observableArrayList(employees);

        empIdCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
        empFnameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeFirstName"));
        empLnameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeLastName"));
        empPhoneCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeePhoneNumber"));
        empEmailCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeEmail"));
        emplTable.setItems(empData);



    }

    public void addJobEvent(ActionEvent event) {

        while (addJobnum < 1)
        {
            addJobStage = new Stage();
            addJobStage.setTitle("Create a Job");
            addJobStage.setScene(new Scene(addJobroot.getSceneroot()));
            addJobStage.setResizable(true);
            addJobStage.centerOnScreen();
            addJobStage.initModality(Modality.APPLICATION_MODAL);

            addJobnum++;

        }

        addJobStage.show();
        createJobController.stage = addJobStage;
        jobDeleteBtn.setDisable(true);
        jobEditBtn.setDisable(true);

    }

    public void editJobEvent(ActionEvent event) {

        while (editnum < 1)
        {
            editJobStage = new Stage();
            editJobStage.setTitle("Edit a Job");
            editJobStage.setScene(new Scene(editJobroot.getSceneroot()));
            editJobStage.setResizable(true);
            editJobStage.centerOnScreen();
            editJobStage.initModality(Modality.APPLICATION_MODAL);

            editnum++;

        }


        editJobStage.show();
        editJobController.stage = editJobStage;
        jobDeleteBtn.setDisable(true);
        jobEditBtn.setDisable(true);




        Job job = preProdListView.getSelectionModel().getSelectedItem();
        editJobController.job = job;
        editJobController.phoneTxt.setText(job.getCustomerByCustomerId().getCustomerPhoneNumber());
        editJobController.lnameTxt.setText(job.getCustomerByCustomerId().getCustomerLastName());
        editJobController.prodNameTxt.setText(job.getProductName());
        editJobController.priceTxt.setText(String.valueOf(job.getTotalPrice()));

        Jobcomponent[] jobcomponents = jobComponetRepository.findJobComponentsByJobIdArray(job.getJobId());
        editJobController.comp1Combo.setValue(jobcomponents[0].getComponentByComponentId().getComponentName());
        editJobController.comp1qtyTxt.setText(String.valueOf(jobcomponents[0].getQuantity()));
        editJobController.jobcomponent1 = jobcomponents[0];
        int length = jobcomponents.length;

        if( length > 1 &&  jobcomponents[1] != null)
        {   editJobController.jobcomponent2 = jobcomponents[1];
            editJobController.comp2Combo.setValue(jobcomponents[1].getComponentByComponentId().getComponentName());
            editJobController.comp2qtyTxt.setText(String.valueOf(jobcomponents[1].getQuantity()));

        }

        if(length > 2 && jobcomponents[2] != null)
        {
            editJobController.jobcomponent3 = jobcomponents[2];
            editJobController.comp3Combo.setValue(jobcomponents[2].getComponentByComponentId().getComponentName());
            editJobController.comp3qtyTxt.setText(String.valueOf(jobcomponents[2].getQuantity()));

        }

        if(length > 3 && jobcomponents[3] != null)
        {
            editJobController.jobcomponent4 = jobcomponents[3];
            editJobController.comp4Combo.setValue(jobcomponents[3].getComponentByComponentId().getComponentName());
            editJobController.comp4qtyTxt.setText(String.valueOf(jobcomponents[3].getQuantity()));

        }

        if(length > 4 && jobcomponents[4] != null)
        {
            editJobController.jobcomponent5 = jobcomponents[4];
            editJobController.comp5Combo.setValue(jobcomponents[4].getComponentByComponentId().getComponentName());
            editJobController.comp5qtyTxt.setText(String.valueOf(jobcomponents[4].getQuantity()));

        }

    }

    public void deleteJobEvent(ActionEvent event) {
        Job job = preProdListView.getSelectionModel().getSelectedItem();
        job.setJobStatusId(5);
        jobRepository.save(job);
        jobDeleteBtn.setDisable(true);
        jobEditBtn.setDisable(true);
        loadPreProdJobs();


    }

    public void addCustEvent(ActionEvent event) {

        while (addCustnum < 1)
        {
            addCustStage = new Stage();
            addCustStage.setTitle("Add a Customer");
            addCustStage.setScene(new Scene(addCustroot.getSceneroot()));
            addCustStage.setResizable(true);
            addCustStage.centerOnScreen();
            addCustStage.initModality(Modality.APPLICATION_MODAL);

            addCustnum++;

        }


        addCustStage.show();
        createCustomerController.stage = addCustStage;

        custEditBtn.setDisable(true);
        custDeleteBtn.setDisable(true);


    }

    public void editCustEvent(ActionEvent event) {
        while (editCustnum < 1)
        {
            editCustStage = new Stage();
            editCustStage.setTitle("Edit a Customer");
            editCustStage.setScene(new Scene(editCustRoot.getSceneroot()));
            editCustStage.setResizable(true);
            editCustStage.centerOnScreen();
            editCustStage.initModality(Modality.APPLICATION_MODAL);

            editCustnum++;

        }


        editCustStage.show();
        editCustomerController.stage = editCustStage;

        Customer customer = custTable.getSelectionModel().getSelectedItem();
        editCustomerController.customer = customer;
        editCustomerController.custFnameTxt.setText(customer.getCustomerFirstName());
        editCustomerController.custLnameTxt.setText(customer.getCustomerLastName());
        editCustomerController.custPhoneTxt.setText(customer.getCustomerPhoneNumber());
        editCustomerController.custEmailTxt.setText(customer.getCustomerEmail());

        custEditBtn.setDisable(true);
        custDeleteBtn.setDisable(true);

    }

    public void deleteCustEvent(ActionEvent event) {
        Customer customer = custTable.getSelectionModel().getSelectedItem();
        customerRepository.deleteById(customer.getCustomerId());
        custEditBtn.setDisable(true);
        custDeleteBtn.setDisable(true);
        loadCustData();

    }

    public void addEmpEvent(ActionEvent event) {

        while (addEmpnum < 1)
        {
            addEmpStage = new Stage();
            addEmpStage.setTitle("Add an Employee");
            addEmpStage.setScene(new Scene(addEmpRoot.getSceneroot()));
            addEmpStage.setResizable(true);
            addEmpStage.centerOnScreen();
            addEmpStage.initModality(Modality.APPLICATION_MODAL);

            addEmpnum++;

        }


        addEmpStage.show();
        createEmployeeController.stage= addEmpStage;

        empEditBtn.setDisable(true);
        empDeleteBtn.setDisable(true);

    }

    public void editEmpEvent(ActionEvent event) {
        while (editEmpnum < 1)
        {
            editEmpStage = new Stage();
            editEmpStage.setTitle("Edit an Employee");
            editEmpStage.setScene(new Scene(editEmpRoot.getSceneroot()));
            editEmpStage.setResizable(true);
            editEmpStage.centerOnScreen();
            editEmpStage.initModality(Modality.APPLICATION_MODAL);

            editEmpnum++;

        }


        editEmpStage.show();
        editEmployeeController.stage = editEmpStage;

        Employee employee = emplTable.getSelectionModel().getSelectedItem();
        editEmployeeController.employee = employee;
        editEmployeeController.empFnameTxt.setText(employee.getEmployeeFirstName());
        editEmployeeController.empLnameTxt.setText(employee.getEmployeeLastName());
        editEmployeeController.empPhoneTxt.setText(employee.getEmployeePhoneNumber());
        editEmployeeController.empEmailTxt.setText(employee.getEmployeeEmail());

        empEditBtn.setDisable(true);
        empDeleteBtn.setDisable(true);

    }

    public void deleteEmpEvent(ActionEvent event) {
        Employee employee = emplTable.getSelectionModel().getSelectedItem();
        employeeRepository.deleteById(employee.getEmployeeId());
        empEditBtn.setDisable(true);
        empDeleteBtn.setDisable(true);
        loadEmpData();

    }

    public void viewCompEvent(ActionEvent event) {
        while (viewCompnum < 1)
        {
            viewCompStage = new Stage();
            viewCompStage.setTitle("Edit an Employee");
            viewCompStage.setScene(new Scene(viewComproot.getSceneroot()));
            viewCompStage.setResizable(true);
            viewCompStage.centerOnScreen();
            viewCompStage.initModality(Modality.APPLICATION_MODAL);

            viewCompnum++;

        }


        viewCompStage.show();
        viewComponentsController.stage = viewCompStage;

    }

    private void removeSelectedJobs(int stage) {
        List<Job> selectedJob = new ArrayList<>();

        System.out.println(selectedJob+"removeSelectedContent");
        if(stage==1) {
            for (Job job : preProdListView.getSelectionModel().getSelectedItems()) {
                selectedJob.add(job);
            }
            preProdListView.getSelectionModel().clearSelection();

            preProdListView.getItems().removeAll(selectedJob);
        }

        if(stage==2){
            System.out.println("Inside REmove Stage 2");
            for (Job job : prodListView.getSelectionModel().getSelectedItems()) {
                selectedJob.add(job);
            }
            prodListView.getSelectionModel().clearSelection();

            prodListView.getItems().removeAll(selectedJob);
        }

        if(stage==3){
            System.out.println("Inside REmove Stage 3");
            for (Job job : clOutListView.getSelectionModel().getSelectedItems()) {
                selectedJob.add(job);
            }
            clOutListView.getSelectionModel().clearSelection();
            System.out.println("Selected Jobs to remove:"+archiveListView.getItems());
            clOutListView.getItems().removeAll(selectedJob);

        }

    }

    public void preProdDragDetected(MouseEvent mouseEvent) {

        System.out.println("Drag event detected");
        int selected = preProdListView.getSelectionModel().getSelectedIndices().size();
        System.out.println(String.format("%d selected",selected));
        if(selected > 0){
            Dragboard dragboard = preProdListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ArrayList<Job> selectedItems = new ArrayList<Job>(preProdListView.getSelectionModel().getSelectedItems());
            ClipboardContent content = new ClipboardContent();
            content.put(PERSON_LIST,selectedItems);
            dragboard.setContent(content);
            mouseEvent.consume();
        } else {
            System.out.println("nothing selected");
            mouseEvent.consume();
        }
    }


    public void preProdDragDone(DragEvent dragEvent) {

        System.out.println("Drag done detected");
        TransferMode tm = dragEvent.getAcceptedTransferMode();
        if(tm == TransferMode.MOVE) {
            removeSelectedJobs(1);
        }
        dragEvent.consume();
    }

    public void prodDragDrop(DragEvent dragEvent) {


         /*
         preProdList.getItems().addAll(buildJobData());
        preProdList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
         */

        boolean dragCompleted = false;
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasContent(PERSON_LIST)) {
            ArrayList<Job> prodJobs = (ArrayList<Job>) dragboard.getContent(PERSON_LIST);

            // now update the status of each job

            for (Job job : prodJobs)
            { job.setJobStatusId(2);
                jobRepository.save(job);
            }



            prodListView.getItems().addAll(prodJobs);
            prodListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            dragCompleted = true;
        }
        dragEvent.setDropCompleted(dragCompleted);
        dragEvent.consume();
    }

    public void productionDragOver(DragEvent dragEvent) {
        System.out.println("Drag over Production detected");
        Dragboard dragboard = dragEvent.getDragboard();
        if(dragboard.hasContent(PERSON_LIST)){
            dragEvent.acceptTransferModes(TransferMode.MOVE);

        }
        dragEvent.consume();


    }


    public void prodDragDetected(MouseEvent mouseEvent) {




        System.out.println("Drag event detected in Prod");
        int selected = prodListView.getSelectionModel().getSelectedIndices().size();
        System.out.println(String.format("%d selected",selected));

       /* if (event.getGestureSource() != target &&
                event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
        // event.acceptTransferModes()(TransferMode.COPY_OR_MOVE);*/
        if ((mouseEvent.getSource()== prodListView)&&(selected > 0))
        {
            Dragboard dragboard = prodListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ArrayList<Job> selectedItems = new ArrayList<Job>(prodListView.getSelectionModel().getSelectedItems());
            System.out.println(selectedItems);
            ClipboardContent content = new ClipboardContent();


            content.put(PERSON_LIST,selectedItems);
            System.out.println(content+"fromContent IN PROD");
            dragboard.setContent(content);
            mouseEvent.consume();
        } else {
            System.out.println("nothing selected");
            mouseEvent.consume();
        }

    }

    public void prodDragDone(DragEvent dragEvent) {
        System.out.println("Drag done detected in Prod");
        TransferMode tm = dragEvent.getAcceptedTransferMode();
        if(tm == TransferMode.MOVE) {
            removeSelectedJobs(2);
        }
        dragEvent.consume();
    }
    public void closeoutDragDetected(MouseEvent mouseEvent) {
        System.out.println("Drag event detected in Closeout");
        int selected = clOutListView.getSelectionModel().getSelectedIndices().size();
        System.out.println(String.format("%d selected",selected));

       /* if (event.getGestureSource() != target &&
                event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
        // event.acceptTransferModes()(TransferMode.COPY_OR_MOVE);*/
        if ((selected > 0))
        {
            Dragboard dragboard = clOutListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ArrayList<Job> selectedItems = new ArrayList<Job>(clOutListView.getSelectionModel().getSelectedItems());
            System.out.println(selectedItems);
            ClipboardContent content = new ClipboardContent();


            content.put(PERSON_LIST,selectedItems);
            System.out.println(content+"fromContent IN PROD");
            dragboard.setContent(content);
            mouseEvent.consume();
        } else {
            System.out.println("nothing selected");
            mouseEvent.consume();
        }



    }

    public void closeoutDragDone(DragEvent dragEvent) {
        System.out.println("Drag done detected in closeout");
        TransferMode tm = dragEvent.getAcceptedTransferMode();
        if(tm == TransferMode.MOVE) {
            removeSelectedJobs(3);
        }
        dragEvent.consume();
    }
    public void closeoutDragDrop(DragEvent dragEvent) {

        boolean dragCompleted = false;
        Dragboard dragboard = dragEvent.getDragboard();
        System.out.println(dragboard+"fromDragboARD");
        if (dragboard.hasContent(PERSON_LIST) && dragEvent.getGestureSource() == prodListView){
            ArrayList<Job> closeoutJobs = (ArrayList<Job>) dragboard.getContent(PERSON_LIST);


            // now update the status of each job
            for (Job job : closeoutJobs)
            { job.setJobStatusId(3);
                jobRepository.save(job);
            }


            clOutListView.getItems().addAll(closeoutJobs);
            dragCompleted = true;
        }
        dragEvent.setDropCompleted(dragCompleted);
        dragEvent.consume();
    }

    public void closeoutDragOver(DragEvent dragEvent) {
        System.out.println("Drag over Closeout detected");
        Dragboard dragboard = dragEvent.getDragboard();
        if(dragboard.hasContent(PERSON_LIST)){
            dragEvent.acceptTransferModes(TransferMode.MOVE);

        }
        dragEvent.consume();


    }

    public void archiveDragDrop(DragEvent dragEvent) {
        boolean dragCompleted = false;
        Dragboard dragboard = dragEvent.getDragboard();
        System.out.println(dragboard+"fromDragboARD");
        if (dragboard.hasContent(PERSON_LIST) && dragEvent.getGestureSource() == clOutListView){
            ArrayList<Job> archiveJobs = (ArrayList<Job>) dragboard.getContent(PERSON_LIST);



            for (Job job : archiveJobs)
            { job.setJobStatusId(4);
                jobRepository.save(job);
            }



            archiveListView.getItems().addAll(archiveJobs);
            dragCompleted = true;
        }
        dragEvent.setDropCompleted(dragCompleted);
        dragEvent.consume();
    }
    public void archiveDragOver(DragEvent dragEvent) {
        System.out.println("Drag over Closeout detected");
        Dragboard dragboard = dragEvent.getDragboard();
        if(dragboard.hasContent(PERSON_LIST)){
            dragEvent.acceptTransferModes(TransferMode.MOVE);

        }
        dragEvent.consume();
    }

    public void preProdDragOver(DragEvent dragEvent) {
        System.out.println("Drag over Pre Production detected");
        Dragboard dragboard = dragEvent.getDragboard();
        if(dragboard.hasContent(PERSON_LIST)){
            dragEvent.acceptTransferModes(TransferMode.MOVE);

        }
        dragEvent.consume();


    }

    public void preProdDragDrop(DragEvent dragEvent) {


         /*
         preProdList.getItems().addAll(buildJobData());
        preProdList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
         */

        boolean dragCompleted = false;
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasContent(PERSON_LIST)) {
            ArrayList<Job> preProdJobs = (ArrayList<Job>) dragboard.getContent(PERSON_LIST);

            // now update the status of each job
            for (Job job : preProdJobs)
            {
                job.setJobStatusId(1);
                jobRepository.save(job);
            }


            preProdListView.getItems().addAll(preProdJobs);
            preProdListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            dragCompleted = true;
        }
        dragEvent.setDropCompleted(dragCompleted);
        dragEvent.consume();
    }
}
