package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Database.AlertHelper;
import Database.MyController;
import Model.Author;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AuthorDetailController implements Initializable, MyController {
	
private static Logger logger = LogManager.getLogger();
	
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private TextField Website;
    @FXML private RadioButton Male;
    @FXML private RadioButton Female;
    @FXML private DatePicker datePicker;
    @FXML private Button Save;
   
    private Author author;
    private ObservableList<Author> authors;
    
    public AuthorDetailController() {
    	
    }
    
    public AuthorDetailController(Author author, ObservableList<Author> authors) {
    	this();
        this.author = author;
        this.authors = authors;
        logger.info("Now showing: " + author.toString());
    }
    
    @FXML
    public void saveAuthorClicked() {
    	
    	logger.info("Author's info is saved");
    	
    	if(!author.isValidAuthorName(author.getAuthorFullName())) {
    		logger.error("Invalid dog name " + author.getAuthorFullName());
    		
    		AlertHelper.showWarningMessage("ERROR", "Author's Name Invalid", "The name that you inputed is invalid, try again.");
    	return;
    	}
    	author.save();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

        FirstName.textProperty().bindBidirectional(author.authorFirstNameProperty());
        LastName.textProperty().bindBidirectional(author.authorLastNameProperty());
        Website.textProperty().bindBidirectional(author.authorWebsiteProperty());
        setGender();
        datePicker.valueProperty().bindBidirectional(author.authorBirthDateProperty());
	}
private void setGender(){
   if(author.getAuthorGender() == Author.Gender.Male.toString()) {
       Male.setSelected(true);
       Female.setSelected(false);
   }else if(author.getAuthorGender() == Author.Gender.Female.toString()){
       Male.setSelected(false);
       Female.setSelected(true);
   }
}
}
