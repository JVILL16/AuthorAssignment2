package View;

import Controller.AuthorDetailController;
import Controller.AuthorListController;
import Model.Author;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class SingletonSwitcher {
        private static SingletonSwitcher instance = null;
    private VBox root, detailBox;
    private AnchorPane listPane;
    private ObservableList<Author> authors;
        protected SingletonSwitcher() {
        }
        public static SingletonSwitcher getInstance() {
            if(instance == null) {
                instance = new SingletonSwitcher();
            }
            return instance;
        }
        public void switchToAuthorListView() throws Exception{
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AuthorListView.fxml"));
            loader.setController(new AuthorListController(authors));
            listPane = loader.load();
            root.getChildren().remove(detailBox);
            root.getChildren().add(listPane);
        }
    public void switchToAuthorDetailView(Author author) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AuthorDetailView.fxml"));
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
