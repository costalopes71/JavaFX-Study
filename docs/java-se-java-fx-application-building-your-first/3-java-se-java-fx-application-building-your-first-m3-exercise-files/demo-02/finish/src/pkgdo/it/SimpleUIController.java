package pkgdo.it;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SimpleUIController {

    @FXML
    public TextField textfield;

    public void initialize() {
        textfield.setText("This is set through controller");
    }
}
