package pkgdo.it;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

    private Task currentTask = new Task();

    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Task> tasksTable;

    @FXML
    private TableColumn<Task, String> priorityColumn;
    
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    
    @FXML
    private TableColumn<Task, String> progressColumn;
    
    @FXML
    private ComboBox<String> priorities;

    @FXML
    private TextField taskDescription;

    @FXML
    private Button add;

    @FXML
    private Spinner<Integer> progressSpinner;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private Button cancel;
    
    @FXML
    private ProgressBar progressBar;

    private int lastId = 0;
    
    private final HashMap<Integer, Task> tasksMap = new HashMap<>();

    public HashMap<Integer, Task> getTasksMap() {
        return tasksMap;
    }
    
    @FXML
    void addTask(ActionEvent event) {

        if (currentTask.getId() == null) {
            
            Task t = new Task(++lastId, currentTask.getPriority(), currentTask.getDescription(), currentTask.getProgress());
            tasks.add(t);
            tasksMap.put(lastId, t);
            
        } else {
            
            Task t = tasksMap.get(currentTask.getId());
            t.setDescription(currentTask.getDescription());
            t.setPriority(currentTask.getPriority());
            t.setProgress(currentTask.getProgress());
            
        }
        
        setCurrentTask(null);
        
    }
    
    @FXML
    void cancelAction(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setTitle("Cancelling Update");
        alert.getButtonTypes().remove(0, 2);
        alert.getButtonTypes().add(0, ButtonType.YES);
        alert.getButtonTypes().add(1, ButtonType.NO);
        
        Optional<ButtonType> confirmationResponse = alert.showAndWait();
        
        if (confirmationResponse.get() == ButtonType.YES) {
            setCurrentTask(null);
            tasksTable.getSelectionModel().clearSelection();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        priorities.getItems().addAll("High", "Medium", "Low");
        progressSpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        
        //
        // usando annonimous class (java < 8 )
        //
//        progressSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
//            @Override
//            public void changed(ObservableValue<? extends Integer> ov, Integer oldValue, Integer newValue) {
//
//                if (newValue == 100) {
//                    completedCheckBox.setSelected(true);
//                } else {
//                    completedCheckBox.setSelected(false);
//                }
//                
//            }
//        });
        
        //
        // com lambda *java > 8)
        //
        progressSpinner.valueProperty().addListener((ov, oldValue, newValue) -> {
            
            if (newValue == 100) {
                    completedCheckBox.setSelected(true);
            } else {
                completedCheckBox.setSelected(false);
            }
            
            System.out.println(currentTask.getDescription());
            System.out.println(currentTask.getPriority());
            System.out.println(currentTask.getProgress());
            
        });
        
        ReadOnlyIntegerProperty intProgress = ReadOnlyIntegerProperty.readOnlyIntegerProperty(progressSpinner.valueProperty());
        progressBar.progressProperty().bind(intProgress.divide(100.0));
        
        priorities.valueProperty().bindBidirectional(currentTask.priorityProperty());
        taskDescription.textProperty().bindBidirectional(currentTask.descriptionProperty());
        progressSpinner.getValueFactory().valueProperty().bindBidirectional(currentTask.progressProperty());
        
        //
        // setando a table para que receba como itens a observable list de tasks
        //
        tasksTable.setItems(tasks);
        
        priorityColumn.setCellValueFactory(rowData -> rowData.getValue().priorityProperty());
        descriptionColumn.setCellValueFactory(rowData -> rowData.getValue().descriptionProperty());
        progressColumn.setCellValueFactory(rowData -> Bindings.concat(rowData.getValue().progressProperty(), "%"));
        
        StringBinding addButtonTextBinding = new StringBinding() {
            
            {
                super.bind(currentTask.idProperty());
            }
            
            @Override
            protected String computeValue() {
                if (currentTask.getId() == null)
                    return "Add";
                else
                    return "Update";
            }
        };
        
        add.textProperty().bind(addButtonTextBinding);
        add.disableProperty().bind(Bindings.greaterThan(3, currentTask.descriptionProperty().length()));
        
         tasksTable.getSelectionModel().selectedItemProperty().addListener((ov, oldTask, newTask) -> {
            
            setCurrentTask(newTask);
            
        });
        
    }

    private void setCurrentTask(Task newTask) {

        if (newTask != null) {
            
            currentTask.setId(newTask.getId());
            currentTask.setPriority(newTask.getPriority());
            currentTask.setDescription(newTask.getDescription());
            currentTask.setProgress(newTask.getProgress());
            
        } else {
            
            currentTask.setId(null);
            currentTask.setPriority("");
            currentTask.setDescription("");
            currentTask.setProgress(0);
            
        }

    }

    void setTaskMap(Map<Integer, Task> readTasks) {

        tasksMap.clear();
        tasks.clear();
        tasksMap.putAll(readTasks);
        tasks.addAll(readTasks.values());
        lastId = tasksMap.keySet().stream().max(Integer::compare).get();
        
    }

}
