package View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Model.Author;
import Database.AppException;
import Database.ConnectionFactory;
import Database.AppController;

public class Launcher extends Application{

	private static Logger logger = LogManager.getLogger();
	private ObservableList<Author> authors;
	private Connection conn;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		logger.info("start() called");
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
		
		logger.info("Creating connection...");
		
		try {
			conn = ConnectionFactory.createConnection();
		} catch(AppException e) {
			logger.fatal("Cannot connect to db");
			Platform.exit();
		}
	}


	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		logger.info("Closing connection...");
		
		conn.close();
	}


	public static void main(String[] args) {
		launch(args);

	}

}
