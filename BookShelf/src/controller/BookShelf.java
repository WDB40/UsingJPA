package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Book;

public class BookShelf {
	
	private static EntityManagerFactory entityManagerFactory 
		= Persistence.createEntityManagerFactory("BookShelf");
	
	public void addBook(Book book) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(book);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<Book> showAllBooks(){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		List<Book> allBooks = entityManager.createQuery(""
				+ "SELECT book "
				+ "FROM Book book")
				.getResultList();
		
		return allBooks;
	}
	
	public void removeBook(Book book) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		TypedQuery<Book> typedQuery = entityManager.createQuery(""
				+ "SELECT book "
				+ "FROM Book book "
				+ "WHERE book.authorLastName = :selectedAuthorLastName "
				+ "and book.authorFirstName = :selectedAuthorFirstName "
				+ "and book.title = :selectedTitle "
				+ "and book.genre = :selectedGenre",
				Book.class);
		
		typedQuery.setParameter("selectedAuthorLastName", book.getAuthorLastName());
		typedQuery.setParameter("selectedAuthorFirstName", book.getAuthorFirstName());
		typedQuery.setParameter("selectedTitle", book.getTitle());
		typedQuery.setParameter("selectedGenre", book.getGenre());
		
		Book result = typedQuery.getSingleResult();
		
		entityManager.remove(result);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	public Book searchForBookById(int bookId) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Book found = entityManager.find(Book.class, bookId);
		
		entityManager.close();
		
		return found;
	}
	
	public void updateBook(Book book) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(book);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<Book> searchForBookByGenre(String genre){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		TypedQuery<Book> typedQuery = entityManager.createQuery(""
				+ "SELECT book "
				+ "FROM Book book "
				+ "WHERE book.genre = :selectedGenre",
				Book.class);
		typedQuery.setParameter("selectedGenre", genre);
		
		List<Book> foundBooks = typedQuery.getResultList();
		
		entityManager.close();
		
		return foundBooks;
	}
	
	public List<Book> searchForBookByAuthorName(String firstName, String lastName){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		TypedQuery<Book> typedQuery = entityManager.createQuery(""
				+ "SELECT book "
				+ "FROM Book book "
				+ "WHERE book.authorFirstName = :selectedAuthorFirstName "
				+ "and book.authorLastName = :selectedAuthorLastName",
				Book.class);
		typedQuery.setParameter("selectedAuthorFirstName", firstName);
		typedQuery.setParameter("selectedAuthorLastName", lastName);
		
		List<Book> foundBooks = typedQuery.getResultList();
		
		entityManager.close();
		
		return foundBooks;
	}
	
	public List<Book> searchForBookByTitle(String title){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		TypedQuery<Book> typedQuery = entityManager.createQuery(""
				+ "SELECT book "
				+ "FROM Book book "
				+ "WHERE book.title = :selectedTitle",
				Book.class);
		typedQuery.setParameter("selectedTitle", title);
		
		List<Book> foundBooks = typedQuery.getResultList();
		
		entityManager.close();
		
		return foundBooks;
	}
	
	public void cleanUp() {
		entityManagerFactory.close();
	}
}
