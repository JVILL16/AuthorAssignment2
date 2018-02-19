package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Database.AppController;
import Database.AuthorTableGateway;
import Database.MyController;
import Model.Author;
import View.SingletonSwitcher;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AuthorListController implements Initializable, MyController {
	
	 private static Logger logger = LogManager.getLogger();
	
	 @FXML private ListView<Author> authorList;
	 private ObservableList<Author> authors;

	private AuthorTableGateway gateway;
	    
	 public AuthorListController(ObservableList<Author> authors) {
	    this.authors = authors;
	 }
	 
	 public AuthorListController(AuthorTableGateway gateway) {
	    	this.gateway = gateway;
	    	authors = this.gateway.getAuthors();
	    }
	    
	 @FXML void onDogListClicked(MouseEvent event) {
	    if(event.getClickCount() > 1) {
	    		Author author = authorList.getSelectionModel().getSelectedItem();
	   			if(author != null) {
	    			AppController.getInstance().changeView(AppController.AUTHOR_DETAIL, author);
	        		logger.info(author.getAuthorFullName() + " clicked");
	    		}
	    	}
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
			this.authorList.setItems(authors);
	}

}
