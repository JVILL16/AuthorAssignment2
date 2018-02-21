package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javax.swing.text.html.ListView;

import Model.Author;

public class MenuController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

   /* @FXML private ObservableList<Author> authors;
    private AuthorListController alController;

    public MenuController(ObservableList<Author> authors) {
        this.authors = authors;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    alController = new AuthorListController(authors);
    }
    @FXML
    void quit(ActionEvent event) throws Exception{
        System.exit(0);
    }
    @FXML
    public void switchToAuthorListView() throws Exception{
        SingletonSwitcher.getInstance().switchToAuthorListView();
    }*/

}
