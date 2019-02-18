package pkgdo.it;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Home extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();

        TableView table = new TableView();
        GridPane.setConstraints(table, 1, 1, 3, 1);

        TableColumn column1 = new TableColumn("Priority");
        TableColumn column2 = new TableColumn("Task Description");
        TableColumn column3 = new TableColumn("Progress");

        table.getColumns().addAll(column1, column2, column3);

        TextField taskName = new TextField();
        GridPane.setConstraints(taskName, 2, 2);

        ComboBox priority = new ComboBox();
        GridPane.setConstraints(priority, 1, 2);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 3, 2);

        Button cancelButton = new Button("Cancel");
        GridPane.setConstraints(cancelButton, 3, 3);

        HBox progressArea = new HBox();
        Label progressLabel = new Label("Progress");
        CheckBox completedCheckBox = new CheckBox("Completed");
        progressArea.getChildren().addAll(progressLabel, new Spinner<Integer>(0, 100, 0), completedCheckBox);

        GridPane.setConstraints(progressArea, 1, 3, 2, 1);

        grid.getChildren().addAll(table, taskName, priority, addButton, progressArea, cancelButton);

        Scene scene = new Scene(grid, 600, 400);

        stage.setScene(scene);
        stage.setTitle("Do-It!!!");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(args);
    }

}
