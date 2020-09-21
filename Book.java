

/*
 *  ============================================================================================
 *  Book.java : A book
 *  ============================================================================================
 */
class Book {
	private int id;
	private String title;
	private String author;
	private String ISBN;
	private String publisher;
	private String status;

	public Book(int id, String title, String author, String isbn, String publisher, String status) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.ISBN = isbn;
		this.publisher = publisher;
		this.status = status;
	}

	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public int getID() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String toString() {
		return String.format("ID: %d%nTitle: %s, by %s%nISBN: %s%nPublisher: %s%nStatus=%s%n", id, title, author, ISBN,
				publisher, status);
	}
}
