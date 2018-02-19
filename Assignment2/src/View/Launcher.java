package View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Connection;

import Model.Author;
import Database.AppController;

public class Launcher extends Application{

	private ObservableList<Author> authors;
	private Connection conn;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		AppController controller = AppController.getInstance();
		controller.setConnection(conn);
		URL fxmlFile = this.getClass().getResource("MenuPanelNoView.fxml");
		FXMLLoader loader = new FXMLLoader(fxmlFile);
		
		loader.setController(controller);
		
		Parent root = loader.load();
		controller.setRootPane((BorderPane) root);
		
		Scene scene = new Scene(root, 600, 400);
	    
		primaryStage.setTitle("Single Document Interface Book Inventory System");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

	}
	@Override
	public void init() throws Exception {
		super.init();

		authors = FXCollections.observableArrayList();
		authors.add(new Author("Ragnar", "Ragnarson", "male", "http://www.ah-the-aliens-are-abducting-me.biz", "2016-08-16"));	//Remove after testing
		authors.add(new Author("Sweetie", "Sweetson", "female"));	//Remove after testing
	}


	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}


	public static void main(String[] args) {
		launch(args);

	}

}
