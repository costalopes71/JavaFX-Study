package pkgdo.it;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox box = new HBox();

        VBox vBox = new VBox(new Label("This goes down"),
                             new Button("Down"),
                             new Button("Further Down"));

        box.getChildren().addAll(new Label("User Name"),
                                 new TextField(),
                                 new Button("Connect"),
                                 vBox);

        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String... args) {
        Application.launch(args);
    }
}
