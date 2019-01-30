package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="AUTHOR_FIRST_NAME")
	private String authorFirstName;
	
	@Column(name="AUTHOR_LAST_NAME")
	private String authorLastName;
	
	@Column(name="GENRE")
	private String genre;
	
	public Book() {
		super();
	}
	
	public Book(String title, String genre, String authorFirstName, String authorLastName) {
		super();
		this.title = title;
		this.genre = genre;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	
	@Override
	public String toString() {
		return this.title + " by " + this.authorFirstName + " " + this.authorLastName + " (" + this.genre + ")";
	}
}
