package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AuthorTableGateway {
	private Connection conn;
	
	public AuthorTableGateway(Connection conn) {
		this.conn = conn;
	}
	
	public void updateAuthor(Author authors) throws AppException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update author set first_name = ?, last_name = ?, dob = ?, gender = ?, web_site = ?, where id = ?");
			st.setInt(1, authors.getId());
			st.setString(2, authors.getAuthorFirstName());
			st.setString(3, authors.getAuthorLastName());
			st.setObject(4, authors.authorBirthDateProperty());
			st.setString(5, authors.getAuthorGender());
			st.setString(6, authors.getAuthorWebsite());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e);
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException(e);
			}
		}
	}
	
	public ObservableList<Author> getAuthors() throws AppException {
		ObservableList<Author> authors = FXCollections.observableArrayList();
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("SELECT * FROM AuthorTable ORDER BY first_name");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Author author = new Author(rs.getString("first_name"), 
										rs.getString("last_name"), 
										rs.getString("gender"), 
										rs.getString("dob"), 
										rs.getString("web_site"));
				author.setGateway(this);
				author.setId(rs.getInt("id"));
				authors.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e);
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException(e);
			}
		}
		
		return authors;
	}
}
