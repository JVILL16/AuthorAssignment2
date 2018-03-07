package View;

import java.sql.Connection;

import Controller.AuthorDetailController;
import Controller.AuthorListController;
import Database.AuthorTableGateway;
import Model.Author;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
// Connection doesnt run through here so it doesnt make a connection and might not even get through this script

public class SingletonSwitcher {
        private static SingletonSwitcher instance = null;
    private VBox root, detailBox;
    private AnchorPane listPane;
   // private BorderPane listPane = null;
    private ObservableList<Author> authors;
    private Connection conn;
        protected SingletonSwitcher() {
        }
        public static SingletonSwitcher getInstance() {
            if(instance == null) {
                instance = new SingletonSwitcher();
            }
            return instance;
        }
        public void switchToAuthorListView() throws Exception{
        	//putting logs here wont log so the connection = null error is before this
        	MyController controller = null;
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Controller/AuthorListView.fxml"));
            controller = new AuthorListController(new AuthorTableGateway(conn));
			loader.setController(controller);
            listPane = loader.load();
            root.getChildren().remove(detailBox);
            root.getChildren().add(listPane);
        }
    public void switchToAuthorDetailView(Author author) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Controller/AuthorDetailView.fxml"));
        loader.setController(new AuthorDetailController(author, authors));   //Just for testing
        detailBox = loader.load();
        root.getChildren().remove(listPane);
        root.getChildren().add(detailBox);
    }
    public void setRootPane(Parent rootPane){
        this.root = (VBox) rootPane;
    }
    public void setAuthors(ObservableList<Author> authors){
        this.authors = authors;
    }
    
    
}
