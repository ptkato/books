package books.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Ayrton Felipe, Patrick Augusto and Nilson Junior
 * 
 */
@NamedQueries ({
    @NamedQuery(name = "Book.listBook", query = "select b from Book b where b.genre = ?1 order by b.title")
})
@Entity
public class Book extends AbstractEntity {
    private String title;
    private String summary;
    private String author;


    @ManyToOne
    private Genre genre = new Genre();

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
}
