package pkgdo.it;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
		stage.setResizable(false);
		
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
			System.out.println("Scene Filter: " + event.getEventType().getName());
			event.consume();
		});
		
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
