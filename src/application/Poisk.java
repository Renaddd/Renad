package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Poisk implements Initializable{
	private Session session = null;
	public Button back, poisk;
	public ComboBox<String> Tovar, numsklad;
	public TableView<Sklad_123> tovary;
    @FXML
    private TableColumn<Sklad_123, Integer> id;
    @FXML
    private TableColumn<Sklad_123, String> tovar;
    @FXML
    private TableColumn<Sklad_123, Integer> kol;
	public Stage primaryStage = new Stage();
	
	public void initialize(URL location, ResourceBundle resources) {
		   numsklad.setStyle("-fx-font-size:15");
	       Tovar.setStyle("-fx-font-size:15");
	       tovary.setStyle("-fx-font-size:15");
	       ObservableList<String> sklady = FXCollections.observableArrayList("Склад №1", "Склад №2", "Склад №3");
	       ObservableList<String> tovary = FXCollections.observableArrayList(" ", "Iphone 11", "Ноутбук Xiaomi", "Телевизор Samsung");
	       numsklad.setItems(sklady);
	       Tovar.setItems(tovary);
	       id.setCellValueFactory(new PropertyValueFactory<Sklad_123, Integer>("id"));
	       tovar.setCellValueFactory(new PropertyValueFactory<Sklad_123, String>("tovar"));
	       kol.setCellValueFactory(new PropertyValueFactory<Sklad_123, Integer>("kol"));
	   }
	
	private Session createHibernateSession(String hbm)
	   {
	       SessionFactory sessionFactory  = null;
	       ServiceRegistry serviceRegistry = null;
	       try {
	           try {
	               Configuration cfg = new Configuration().
	                                       addResource(hbm).configure();
	               serviceRegistry = new StandardServiceRegistryBuilder().
	                                     applySettings(cfg.getProperties()).build();
	               sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	           } catch (Throwable e) {
	               System.err.println("Failed to create sessionFactory object." + e);
	               throw new ExceptionInInitializerError(e);
	           }
	           session = sessionFactory.openSession();
	       } catch (Exception e) {
	           System.out.println(e.getMessage());
	       }
	       return session;
	   }
	
	public void Back(ActionEvent event) {
		   try {
			    Stage stage = (Stage) back.getScene().getWindow();
			    stage.close();
				Parent menu = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
				primaryStage.setTitle("Меню");
				primaryStage.setScene(new Scene(menu));
				primaryStage.show();
				primaryStage.setResizable(false);
			} catch(Exception e) {
				e.printStackTrace();
			}
	   }
	
	public void num(ActionEvent event) {
		   Tovar.setDisable(false);
		   poisk.setDisable(false);
	   }
	
	public void Poisk_tov(ActionEvent event) {
		  String hbm = "";
		  boolean Empty_num = numsklad.getSelectionModel().isEmpty();		  
		  if (Empty_num == false)
		   {
			  if (numsklad.getValue() == "Склад №1")
			   {
				   hbm = "Sklad_1.hbm.xml";
			   }
			   else if (numsklad.getValue() == "Склад №2")
			   {
				   hbm = "Sklad_2.hbm.xml";
			   }
			   else if (numsklad.getValue() == "Склад №3")
			   {
				   hbm = "Sklad_3.hbm.xml";
			   }
			   session = createHibernateSession(hbm);
			   if (session != null) {
				   // Добавление записей в таблицу
		           Transaction tx = session.beginTransaction();
		           if (Tovar.getValue() == "Iphone 11")
		           {
		        	   Sklad_123 iphone = (Sklad_123) session.get(Sklad_123.class, 1);
		        	   if (iphone == null)
		        	   {
		        		   Alert alert = new Alert(AlertType.INFORMATION);
		    		       alert.setTitle("Сообщение");
		    		       alert.setHeaderText(null);
		    		       alert.setContentText("Такого товара нет на этом складе, выберите другой товар или оставьте поле пустым и вам выведутся все товары на этом складе");
		    		       alert.showAndWait();
		        	   }
		        	   else
		        	   {
		        		    tovary.getItems().clear();
		        	   		tovary.getItems().addAll(iphone);
		        	   }
		           }
		           else if (Tovar.getValue() == "Ноутбук Xiaomi")
		           {
		        	   Sklad_123 Xiaomi = (Sklad_123) session.get(Sklad_123.class, 2);
		        	   if (Xiaomi == null)
		        	   {
		        		   Alert alert = new Alert(AlertType.INFORMATION);
		    		       alert.setTitle("Сообщение");
		    		       alert.setHeaderText(null);
		    		       alert.setContentText("Такого товара нет на этом складе, выберите другой товар или оставьте поле пустым и вам выведутся все товары на этом складе");
		    		       alert.showAndWait();
		        	   }
		        	   else
		        	   {
		        		    tovary.getItems().clear();
		        	   		tovary.getItems().addAll(Xiaomi);
		        	   }
		           }
		           else if (Tovar.getValue() == "Телевизор Samsung")
		           {
		        	   Sklad_123 Samsung = (Sklad_123) session.get(Sklad_123.class, 3);
		        	   if (Samsung == null)
		        	   {
		        		   Alert alert = new Alert(AlertType.INFORMATION);
		    		       alert.setTitle("Сообщение");
		    		       alert.setHeaderText(null);
		    		       alert.setContentText("Такого товара нет на этом складе, выберите другой товар или оставьте поле пустым и вам выведутся все товары на этом складе");
		    		       alert.showAndWait();
		        	   }
		        	   else
		        	   {
		        		    tovary.getItems().clear();
		        	   		tovary.getItems().addAll(Samsung);
		        	   }
		           }
		           else 
		           {
		        	    tovary.getItems().clear();
	        	   		String query = "select p from " + "Sklad_123" + " p";
	        	   		@SuppressWarnings("unchecked")
	        	   		List<Sklad_123> list = (List<Sklad_123>)session.createQuery(query).list();
	        	   		tovary.getItems().addAll(list);
		           }
		           tx.commit();
				   if (session.isOpen())
					   session.close();
			   }
		   }
		   else
		   {
			   Alert alert = new Alert(AlertType.INFORMATION);
		       alert.setTitle("Сообщение");
		       alert.setHeaderText(null);
		       alert.setContentText("Вы ввели не все данные верно");
		       alert.showAndWait();
		   }
	   }
}
