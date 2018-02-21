package View;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Controller.AuthorDetailController;
import Controller.AuthorListController;
import Database.AppException;
import Database.AuthorTableGateway;
import Model.Author;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class AppController implements Initializable{
	
	@FXML private ObservableList<Author> authors;
	private static Logger logger = LogManager.getLogger(Launcher.class);

	public static final int AUTHOR_LIST = 1;
	public static final int AUTHOR_DETAIL = 2;

	private static AppController myInstance = null;
	
	private BorderPane rootPane = null;
	
	private Connection conn;
	
	private AppController() {
		//TODO: instantiate models
	}
	
	public void changeView(int viewType, Object arg) throws AppException {
		try {
			MyController controller = null;
			URL fxmlFile = null;
			switch(viewType) {
				case AUTHOR_LIST:
					fxmlFile = this.getClass().getResource("Controller/AuthorListView.fxml");
					controller = new AuthorListController(new AuthorTableGateway(conn));
					break;
				case AUTHOR_DETAIL:
					fxmlFile = this.getClass().getResource("Controller/AuthorDetailView.fxml");
					AuthorTableGateway gateway = new AuthorTableGateway(conn);
					controller = new AuthorDetailController((Author) arg, gateway.getAuthors());
					break;
			}
		
			FXMLLoader loader = new FXMLLoader(fxmlFile);
			loader.setController(controller);
		
			Parent viewNode = loader.load();
			rootPane.setCenter(viewNode);
		} catch (IOException e) {
			throw new AppException(e);
		}
	}

	@FXML
    void switchToAuthorListView(ActionEvent event) {
		try {
			SingletonSwitcher.getInstance().switchToAuthorListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("Author list failed");
		}
		logger.info("Author list menu item clicked");
		//changeView(AUTHOR_LIST, null);
		
    }
	
	@FXML
    void quit(ActionEvent event) {
		logger.info("Quit menu item clicked");
		Platform.exit();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public static AppController getInstance() {
		if(myInstance == null)
			myInstance = new AppController();
		return myInstance;
	}
	
	public BorderPane getRootPane() {
		return rootPane;
	}

	public void setRootPane(BorderPane rootPane) {
		this.rootPane = rootPane;
	}

	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
