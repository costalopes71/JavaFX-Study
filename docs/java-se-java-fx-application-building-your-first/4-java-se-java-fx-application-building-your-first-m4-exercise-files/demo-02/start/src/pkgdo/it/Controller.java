package pkgdo.it;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller implements Initializable {

	@FXML
    private ProgressBar progressBar;
	
    @FXML
    private TableView<?> tasksTable;

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
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		priorities.getItems().addAll("High", "Medium", "Low");
		progressSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
		
		progressSpinner.valueProperty().addListener((ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
			if(newValue == 100) {
				completedCheckBox.setSelected(true);
			} else {
				completedCheckBox.setSelected(false);
			}
			progressBar.setProgress(1.0 * newValue / 100);
		});
    }

}
