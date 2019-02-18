package pkgdo.it;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Home extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		GridPane grid = FXMLLoader.load(getClass().getResource("ui.fxml"));
		Scene scene = new Scene(grid, 600, 400);
		
		stage.setScene(scene);
		stage.setTitle("Do-It!!!");
		stage.setAlwaysOnTop(true);
		stage.setResizable(true);
		
		stage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("Height is changed to " + newValue);
			}
		});
		
		stage.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("Width is changed to " + newValue);
			}
		});
		
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
