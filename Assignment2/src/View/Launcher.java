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

import Database.AppException;
import Database.ConnectionFactory;
import Model.Author;

public class Launcher extends Application{

	private static Logger logger = LogManager.getLogger();
	private ObservableList<Author> authors;
	private Connection conn;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		logger.info("start() called");
		AppController controller = AppController.getInstance();
		controller.setConnection(conn);
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MenuPanelNoView.fxml"));
		//MenuController menuController = new MenuController(authors);
		loader.setController(controller);
		Parent rootPane = loader.load();
		SingletonSwitcher.getInstance().setRootPane(rootPane);
		SingletonSwitcher.getInstance().setAuthors(authors);
		Scene menuView = new Scene(rootPane, 600, 400);
		primaryStage.setScene(menuView);
		primaryStage.setTitle("Single Document Interface Book Inventory System");
		primaryStage.show();	


	}
	@Override
	public void init() throws Exception {
		super.init();
		//authors = FXCollections.observableArrayList();
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
