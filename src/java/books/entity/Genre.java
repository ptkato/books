package books.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author Ayrton Felipe, Patrick Augusto and Nilson Junior
 */
@NamedQueries({
    @NamedQuery(name = "Genre.listAll", query = "select c from Genre c order by c.name"),
    @NamedQuery(name = "Genre.selectByName",  query = "select c from Genre c where c.name like ?1 order by c.name")
})
@Entity
public class Genre extends AbstractEntity {
    private String name;
    private String age;
    private String color;
    @OneToMany(cascade=CascadeType.REMOVE, mappedBy = "genre")
    private List<Book> books = new ArrayList<>();

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
        
}
