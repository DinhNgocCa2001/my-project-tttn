package com.bookstore.dao;

import com.bookstore.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class ProductDAO implements IProductDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Product> getProducts() {

        String hql = "FROM Product as pr ORDER BY pr.id";
        return (List<Product>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public Product getProduct(int bookId) {

        return entityManager.find(Product.class, bookId);
    }
    @Override
    public Product createProduct(Product book) {
        entityManager.persist(book);
        Product b = getLastInsertedProduct();
        return b;
    }

    @Override
    public Product updateProduct(int bookId, Product book) {

        Product bookFromDB = getProduct(bookId);
        bookFromDB.setName(book.getName());
        bookFromDB.setPrice(book.getPrice());
        bookFromDB.setDiscount(book.getDiscount());
        bookFromDB.setDescription(book.getDescription());
        bookFromDB.setLink(book.getLink());
        bookFromDB.setStatus(book.getStatus());
        entityManager.flush();

        Product updatedBook = getProduct(bookId);
        return updatedBook;
    }
    @Override
    public boolean deleteProduct(int bookId) {
        Product book = getProduct(bookId);
        entityManager.remove(book);

        boolean status = entityManager.contains(book);
        if(status){
            return false;
        }
        return true;
    }

    private Product getLastInsertedProduct(){
        String hql = "from Product order by id DESC";
        Query query = entityManager.createQuery(hql);
        query.setMaxResults(1);
        Product book = (Product)query.getSingleResult();
        return book;
    }

}

