package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import application.Sklad_123;
 
public class Dobavl_Prodzha implements Initializable {
   private Session session = null;
   @FXML
   public Stage primaryStage = new Stage();
   public ComboBox<String> tovar, numsklad;
   public Button back, dobavlen, sell;
   public TextField kol;
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   numsklad.setStyle("-fx-font-size:15");
       tovar.setStyle("-fx-font-size:15");
       ObservableList<String> sklady = FXCollections.observableArrayList("����� �1", "����� �2", "����� �3");
       ObservableList<String> tovary = FXCollections.observableArrayList("Iphone 11", "������� Xiaomi", "��������� Samsung");
       numsklad.setItems(sklady);
       tovar.setItems(tovary);
       Pattern p = Pattern.compile("(\\d+\\.?\\d*)?");
       kol.textProperty().addListener((observable, oldValue, newValue) -> {
           if (!p.matcher(newValue).matches()) kol.setText(oldValue);
       });
   }
 
   // When user click on myButton
   // this method will be called.
   
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
			primaryStage.setTitle("����");
			primaryStage.setScene(new Scene(menu));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
   }
   
   public void num(ActionEvent event) {
	   tovar.setDisable(false);
   }

   public void tovary(ActionEvent event) {
	   kol.setDisable(false);
   }
   
   public void kolvo(ActionEvent event) {
	   String a = kol.getText();
	   int b;
	   if (a.trim().length() != 0)
	   {
		   b = new Integer(a);
	   }
	   else
	   {
		   b = 0;
	   }
	   if ((b <= 0) || (b > 1000))
	   {
		   dobavlen.setDisable(true);
		   Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("���������");
	 
	       // Header Text: null
	       alert.setHeaderText(null);
	       alert.setContentText("���������� ������ ������ ���� ������ ���� � �� ������ 1000");
	 
	       alert.showAndWait();
	   }
	   else
	   {
		   dobavlen.setDisable(false);
		   kol.setText(Integer.toString(b));
	   }
   }
   
   public void kolvo_sell(ActionEvent event) {
	   String a = kol.getText();
	   int b;
	   if (a.trim().length() != 0)
	   {
		   b = new Integer(a);
	   }
	   else
	   {
		   b = 0;
	   }
	   if ((b <= 0) || (b > 1000))
	   {
		   sell.setDisable(true);
		   Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("���������");
	 
	       // Header Text: null
	       alert.setHeaderText(null);
	       alert.setContentText("���������� ������ ������ ���� ������ ���� � �� ������ 1000");
	 
	       alert.showAndWait();
	   }
	   else
	   {
		   sell.setDisable(false);
		   kol.setText(Integer.toString(b));
	   }
   }
   
   public void dobavit(ActionEvent event) {
	   String hbm = "", a = kol.getText();
	   int b;
	   if (a.trim().length() != 0)
	   {
		   b = new Integer(a);
	   }
	   else
	   {
		   b = 0;
	   }
	   boolean Empty_num = numsklad.getSelectionModel().isEmpty();
	   boolean Empty_tovar = tovar.getSelectionModel().isEmpty();
	   if ((Empty_num == false) && (Empty_tovar == false) && (b > 0))
	   {
		   // �������� ������
		   if (numsklad.getValue() == "����� �1")
		   {
			   hbm = "Sklad_1.hbm.xml";
		   }
		   else if (numsklad.getValue() == "����� �2")
		   {
			   hbm = "Sklad_2.hbm.xml";
		   }
		   else if (numsklad.getValue() == "����� �3")
		   {
			   hbm = "Sklad_3.hbm.xml";
		   }
		   session = createHibernateSession(hbm);
		   if (session != null) {
			   // ���������� ������� � �������
	           Transaction tx = session.beginTransaction();
	           if (tovar.getValue() == "Iphone 11")
	           {
	        	   Sklad_123 iphone = (Sklad_123) session.get(Sklad_123.class, 1);
	        	   if (iphone == null)
	        	   {
	        		   iphone = new Sklad_123();
	        	   }
	        	   int kolvo = b + iphone.getKol();
	        	   iphone.setId(1);
	        	   iphone.setTovar("Iphone 11");
	        	   iphone.setKol(kolvo);
	        	   session.saveOrUpdate(iphone);
	           }
	           else if (tovar.getValue() == "������� Xiaomi")
	           {
	        	   Sklad_123 Xiaomi = (Sklad_123) session.get(Sklad_123.class, 2);
	        	   if (Xiaomi == null)
	        	   {
	        		   Xiaomi = new Sklad_123();
	        	   }
	        	   int kolvo = b + Xiaomi.getKol();
	        	   Xiaomi.setId(2);
	        	   Xiaomi.setTovar("������� Xiaomi");
	        	   Xiaomi.setKol(kolvo);
	        	   session.saveOrUpdate(Xiaomi);
	           }
	           else if (tovar.getValue() == "��������� Samsung")
	           {
	        	   Sklad_123 Samsung = (Sklad_123) session.get(Sklad_123.class, 3);
	        	   if (Samsung == null)
	        	   {
	        		   Samsung = new Sklad_123();
	        	   }
	        	   int kolvo = b + Samsung.getKol();
	        	   Samsung.setId(3);
	        	   Samsung.setTovar("��������� Samsung");
	        	   Samsung.setKol(kolvo);
	        	   session.saveOrUpdate(Samsung);
	           }
	           Alert alert = new Alert(AlertType.INFORMATION);
		       alert.setTitle("���������");
		       alert.setHeaderText(null);
		       alert.setContentText("����� ��������");
		       alert.showAndWait();
	           tx.commit();
			   if (session.isOpen())
				   session.close();
		   }
	   }
	   else
	   {
		   Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("���������");
	       alert.setHeaderText(null);
	       alert.setContentText("�� ����� �� ��� ������ �����");
	       alert.showAndWait();
	   }
   }
   
   public void Sell(ActionEvent event) {
	   String hbm = "", a = kol.getText();
	   int b;
	   if (a.trim().length() != 0)
	   {
		   b = new Integer(a);
	   }
	   else
	   {
		   b = 0;
	   }
	   boolean Empty_num = numsklad.getSelectionModel().isEmpty();
	   boolean Empty_tovar = tovar.getSelectionModel().isEmpty();
	   if ((Empty_num == false) && (Empty_tovar == false) && (b > 0))
	   {
		   // �������� ������
		   if (numsklad.getValue() == "����� �1")
		   {
			   hbm = "Sklad_1.hbm.xml";
		   }
		   else if (numsklad.getValue() == "����� �2")
		   {
			   hbm = "Sklad_2.hbm.xml";
		   }
		   else if (numsklad.getValue() == "����� �3")
		   {
			   hbm = "Sklad_3.hbm.xml";
		   }
		   session = createHibernateSession(hbm);
		   if (session != null) {
			   // ���������� ������� � �������
	           Transaction tx = session.beginTransaction();
	           if (tovar.getValue() == "Iphone 11")
	           {
	        	   Sklad_123 iphone = (Sklad_123) session.get(Sklad_123.class, 1);
	        	   if (iphone == null)
	        	   {
	        		   iphone = new Sklad_123();
	        	   }
	        	   if (b > iphone.getKol())
	        	   {
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	        	       alert.setTitle("���������");
	        	       alert.setHeaderText(null);
	        	       alert.setContentText("���������� ����� ������ �� ������ ����� " + iphone.getKol() + ", �� �� ������ ������� ����� ���������� ������");
	        	       alert.showAndWait();
	        	   }
	        	   else if (b == iphone.getKol())
	        	   {
	        		   iphone.setId(1);
	        		   session.delete(iphone);
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	    		       alert.setTitle("���������");
	    		       alert.setHeaderText(null);
	    		       alert.setContentText("����� ������");
	    		       alert.showAndWait();
	        	   }
	        	   else
	        	   {
	        		   int kolvo = iphone.getKol() - b;
	        		   iphone.setId(1);
	        		   iphone.setTovar("Iphone 11");
	        		   iphone.setKol(kolvo);
	        		   session.saveOrUpdate(iphone);
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	    		       alert.setTitle("���������");
	    		       alert.setHeaderText(null);
	    		       alert.setContentText("����� ������");
	    		       alert.showAndWait();
	        	   }
	           }
	           else if (tovar.getValue() == "������� Xiaomi")
	           {
	        	   Sklad_123 Xiaomi = (Sklad_123) session.get(Sklad_123.class, 2);
	        	   if (Xiaomi == null)
	        	   {
	        		   Xiaomi = new Sklad_123();
	        	   }
	        	   if (b > Xiaomi.getKol())
	        	   {
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	        	       alert.setTitle("���������");
	        	       alert.setHeaderText(null);
	        	       alert.setContentText("���������� ����� ������ �� ������ ����� " + Xiaomi.getKol() + ", �� �� ������ ������� ����� ���������� ������");
	        	       alert.showAndWait();
	        	   }
	        	   else if (b == Xiaomi.getKol())
	        	   {
	        		   Xiaomi.setId(2);
	        		   session.delete(Xiaomi);
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	    		       alert.setTitle("���������");
	    		       alert.setHeaderText(null);
	    		       alert.setContentText("����� ������");
	    		       alert.showAndWait();
	        	   }
	        	   else
	        	   {
	        		   int kolvo = Xiaomi.getKol() - b;
	        		   Xiaomi.setId(2);
	        		   Xiaomi.setTovar("������� Xiaomi");
	        		   Xiaomi.setKol(kolvo);
	        		   session.saveOrUpdate(Xiaomi);
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	    		       alert.setTitle("���������");
	    		       alert.setHeaderText(null);
	    		       alert.setContentText("����� ������");
	    		       alert.showAndWait();
	        	   }
	           }
	           else if (tovar.getValue() == "��������� Samsung")
	           {
	        	   Sklad_123 Samsung = (Sklad_123) session.get(Sklad_123.class, 3);
	        	   if (Samsung == null)
	        	   {
	        		   Samsung = new Sklad_123();
	        	   }
	        	   if (b > Samsung.getKol())
	        	   {
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	        	       alert.setTitle("���������");
	        	       alert.setHeaderText(null);
	        	       alert.setContentText("���������� ����� ������ �� ������ ����� " + Samsung.getKol() + ", �� �� ������ ������� ����� ���������� ������");
	        	       alert.showAndWait();
	        	   }
	        	   else if (b == Samsung.getKol())
	        	   {
	        		   Samsung.setId(3);
	        		   session.delete(Samsung);
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	    		       alert.setTitle("���������");
	    		       alert.setHeaderText(null);
	    		       alert.setContentText("����� ������");
	    		       alert.showAndWait();
	        	   }
	        	   else
	        	   {
	        		   int kolvo = Samsung.getKol() - b;
	        		   Samsung.setId(3);
	        		   Samsung.setTovar("��������� Samsung");
	        		   Samsung.setKol(kolvo);
	        		   session.saveOrUpdate(Samsung);
	        		   Alert alert = new Alert(AlertType.INFORMATION);
	    		       alert.setTitle("���������");
	    		       alert.setHeaderText(null);
	    		       alert.setContentText("����� ������");
	    		       alert.showAndWait();
	        	   }
	           }
	           
	           tx.commit();
			   if (session.isOpen())
				   session.close();
		   }
	   }
	   else
	   {
		   Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("���������");
	       alert.setHeaderText(null);
	       alert.setContentText("�� ����� �� ��� ������ �����");
	       alert.showAndWait();
	   }
   }
   
}