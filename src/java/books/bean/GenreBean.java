/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books.bean;

import books.dao.DAO;
import books.entity.Genre;
import books.entity.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.Transactional;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ayrton Felipe, Patrick Augusto and Nilson Junior
 */
@Named
@SessionScoped
public class GenreBean implements Serializable {

    private Genre genre = new Genre();
    private Book book = new Book();
    private List<Genre> genres = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Genre> filterGenres;

    /**
     * Creates a new instance of ContaBackBean
     */
    public GenreBean() {
        DAO dao = new DAO(Genre.class);
        genres = dao.listarGenerico("Genre.listAll");
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Genre> getFilterGenres() {
        return filterGenres;
    }

    public void setFilterGenres(List<Genre> filterGenres) {
        this.filterGenres = filterGenres;
    }

    public String procurar() {
        DAO dao = new DAO(Genre.class);
        genres = dao.listarGenerico("Genre.selectByName", '%' + genre.getName() + '%');
        return null;
    }

    public String gravar() {
        DAO dao = new DAO(Genre.class);
        dao.adicionar(genre);
        genre = new Genre();
        return null;
    }

    @Transactional
    public String remove(Genre c) throws Exception {
        DAO dao = new DAO(Genre.class);
        dao.excluir(c.getId());
        genres.remove(c);
        FacesMessage msg = new FacesMessage("Conta excluída", c.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public String editar(Genre g) {
        genre = g;
        return "/edit_genre";
    }

    public String alterar() {
        DAO dao = new DAO(Genre.class);
        dao.alterar(genre);
        return "/index";
    }

    public String paginaInicial() {
        genre = new Genre();
        DAO dao = new DAO(Genre.class);
        genres = dao.listarGenerico("Genre.listAll");
        return "/index";
    }

    public String newGenrePage() {
        genre = new Genre();
        return "/new_genre";
    }

    public String booksPage(Genre g) {
        genre = g;
        DAO dao = new DAO(Book.class);
        books = dao.listarGenerico("Book.listBooks", genre);
        return "/book";
    }

    public String paginaNovaMovimentacao() {
        book = new Book();
        return "/new_book";
    }

    public String gravarMovimentacao() {
        book.setGenre(genre);
        DAO dao = new DAO(Book.class);
        dao.adicionar(book);
        book = new Book();
        return null;
    }

    public boolean consultarPorNome(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null
                : filter.toString().trim();
        String valueText = (value == null) ? null
                : value.toString();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (valueText == null) {
            return false;
        }
        return valueText.matches("(?i).*" + filterText + ".*");
    }

    public void onEdit(RowEditEvent event) {
        Genre c = (Genre) event.getObject();
        DAO<Genre> dao = new DAO(Genre.class);
        dao.alterar(c);
        FacesMessage msg = new FacesMessage("Conta atualizada",
                                            c.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public void onCancel(RowEditEvent event) {
        Genre c = (Genre) event.getObject();
        FacesMessage msg = new FacesMessage("Atualização cancelada",
                                            c.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
