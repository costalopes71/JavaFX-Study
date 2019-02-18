package m2.demo1.stage1;

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
		Button b1 = new Button("First");
		Button b2 = new Button("Second");

		g.getChildren().addAll(b1, b2);
		b1.setLayoutX(50);
		b1.setLayoutY(150);
		Scene scene = new Scene(g, 300, 400);
		
		stage.setScene(scene);
		stage.setTitle("Our Application!!!");
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String... args) {
		Application.launch(args);
	}

}
