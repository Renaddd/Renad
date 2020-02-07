package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

 
public class MyController implements Initializable {
 
   @FXML
   public Button myButton, myButton1, myButton2;
   public Stage primaryStage = new Stage();
   @Override
   public void initialize(URL location, ResourceBundle resources) {
 
       // TODO (don't really need to do anything here).
      
   }
 
   // When user click on myButton
   // this method will be called.
   public void dobav(ActionEvent event) {
	   try {
		    Stage stage = (Stage) myButton.getScene().getWindow();
		    stage.close();
			Parent dobav = FXMLLoader.load(getClass().getResource("/dobav.fxml"));
			primaryStage.setTitle("Добавление товаров");
			primaryStage.setScene(new Scene(dobav));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
   }
   public void prodazha(ActionEvent event) {
	   try {
		    Stage stage = (Stage) myButton1.getScene().getWindow();
		    stage.close();
			Parent prodazha = FXMLLoader.load(getClass().getResource("/Prodazha.fxml"));
			primaryStage.setTitle("Продажа товаров");
			primaryStage.setScene(new Scene(prodazha));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
   }
   
   public void tovary(ActionEvent event) {
	   try {
		    Stage stage = (Stage) myButton2.getScene().getWindow();
		    stage.close();
			Parent poisk = FXMLLoader.load(getClass().getResource("/Tovary.fxml"));
			primaryStage.setTitle("Поиск товаров");
			primaryStage.setScene(new Scene(poisk));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
   }
}