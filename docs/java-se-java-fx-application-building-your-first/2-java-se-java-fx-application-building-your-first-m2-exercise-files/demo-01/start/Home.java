package m2.demo1.stage0;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author jbuddha
 */
public class Home extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group g = new Group();
		g.getChildren().add(new Button("First Button"));
		
		Scene scene = new Scene(g, 300, 400);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String... args) {
		Application.launch(args);
	}

}
