package com.example.lb;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepository {
    private EntityManager entityManager;



    public BookRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Book> getBookList() {
        //noinspection JpaQlInspection
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        List<Book> bookList = query.getResultList();
        return bookList;
    }

    public List<Book> sort(String order) {
        //noinspection JpaQlInspection
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b order by b."+order, Book.class);
        List<Book> bookList = query.getResultList();
        return bookList;
    }

    public Book findById(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    public void save(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();

    }



    public void delete(Book book){
        entityManager.getTransaction().begin();
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }
}
