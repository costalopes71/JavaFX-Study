/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgdo.it;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author joao
 */
public class Home extends Application {
   
    Controller controller;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        
        GridPane grid = loader.load();
        controller = loader.getController();
        
        Scene scene = new Scene(grid, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Do-it!!");
        primaryStage.setAlwaysOnTop(false);
        primaryStage.setResizable(false);
        
        primaryStage.setOnCloseRequest(this::close);
        
        controller.setTaskMap(readTasksFile());
        
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
    private Map<Integer, Task> readTasksFile() {
        
        Map<Integer, Task> taskMap = new HashMap<>();
        try (InputStream is = new FileInputStream("task.xml");
                XMLDecoder decoder = new XMLDecoder(is))
        {
            
            taskMap = (Map<Integer, Task>) decoder.readObject();
            decoder.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return taskMap;
    }
    
    private void close(WindowEvent event) {

        try (OutputStream os = new FileOutputStream("task.xml");
                XMLEncoder encoder = new XMLEncoder(os))
        {
            
            encoder.writeObject(controller.getTasksMap());
            encoder.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
    }
    
//    exemplo: 
    
//    @Override
//    public void start(Stage primaryStage) {
//        
//        GridPane grid = new GridPane();
//        grid.setGridLinesVisible(true);
//        grid.setVgap(10);
//        grid.setHgap(10);
//        
//        Button b1 = new Button("First Button Gets Longer");
//        Button b2 = new Button("Longer Second Button");
//        Button b3 = new Button("Third");
//        Button b4 = new Button("Fourth");
//        
//        GridPane.setConstraints(b1, 1, 1);
//        GridPane.setConstraints(b2, 2, 1);
//        GridPane.setConstraints(b3, 1, 2);
//        GridPane.setConstraints(b4, 2, 2);
//        
//        BorderPane borderPane = new BorderPane();
//        borderPane.setTop(new Button("top"));
//        borderPane.setBottom(new Button("botton"));
//        borderPane.setCenter(new Button("center"));
//        borderPane.setRight(new Button("right"));
//        borderPane.setLeft(new Button("left"));
//        
//        GridPane.setConstraints(borderPane, 1, 3, 2, 1);
//        
//        grid.getChildren().addAll(b1, b2, b3, b4, borderPane);
//        
//        Scene scene = new Scene(grid, 400, 400);
//        primaryStage.setScene(scene);
//        
//        primaryStage.setTitle("Do-it!!");
//        primaryStage.setAlwaysOnTop(true);
//        primaryStage.setResizable(false);
//        
//        primaryStage.show();
//        
//    }

    
}
