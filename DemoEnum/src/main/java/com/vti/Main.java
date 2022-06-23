package com.vti;

import com.vti.entity.Article;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        try{
            session = buildSessionFactory().openSession();

            // save - insert
//            Product product = new Product();
//            product.setProductName("Áo");
//            product.setPrice("10.000");
//            product.setUpdatedDate(new Date());
//            product.setRegisterDate(new Date());
//            session.save(product);

            // get all
            // sql native: SELECT * FROM tbl_product
            // Hibernate: FROM Product
//            Query<Product> query = session.createQuery("FROM Product");
//            List<Product> products = query.list();
//            for (int i=0; i<products.size(); i++){
//                System.out.println(products.get(i).getProductName());
//            }

                // get by id
//            Product product = session.get(Product.class, 4);
//            System.out.println(product.getProductName());

            // get by name
            // FROM Product WHERE thuoc_tinh = :param
//            Query<Product> query = session.createQuery("FROM Product WHERE productName = :nameParam");
//            query.setParameter("nameParam", "Quần áo 2");
//            Product product = query.uniqueResult();
//            System.out.println(product.getId());
//            System.out.println(product.getProductName());

//            // update
//            session.beginTransaction();
//            Product product =  session.load(Product.class, 2);
////            product.setProductName("Giầy dép");
//            product.setRegisterDate(new Date());
//            session.getTransaction().commit();


            // delete
//            session.beginTransaction();
//            Product product = session.load(Product.class, 4);
//            session.delete(product);
//            session.getTransaction().commit();


            System.out.println("-------------- CRUD Article Hibernate --------------");
            // insert
            Article article = new Article();
//            article.setTitle("Title 5");
//            session.save(article);

            // update
//            session.beginTransaction();
//            article = session.load(Article.class, 1);
//            article.setTitle("Title Update");
//            session.getTransaction().commit();

            // delete
//            session.beginTransaction();
//            article = session.load(Article.class, 1);
//            session.delete(article);
//            session.getTransaction().commit();

            // get by id
            System.out.println("Get by id");
            article = session.get(Article.class, 4);
            System.out.println(article.getTitle());

            // get by title
            System.out.println("Get by title");
            Query<Article> query = session.createQuery("FROM Article WHERE title = :paramTitle");
            query.setParameter("paramTitle", "Title 4");
            article = query.uniqueResult();
            System.out.println(article.getId() + " - " + article.getCreatedDate());

            // delete nhieu id
            System.out.println("Delete ID 2, ID 3");
            session.beginTransaction();
            query = session.createQuery("DELETE Article WHERE id IN (2, 3)");
            int result = query.executeUpdate();
            session.getTransaction().commit();

            // get all
            System.out.println("Get All: ");
            query = session.createQuery("FROM Article");
            List<Article> articles = query.list();
            for (Article item : articles){
                System.out.println("id: " + item.getId() + " - Title: " + item.getTitle());
            }

            System.out.println("Test git");
        }finally {
            if (session != null){
                session.close();
            }
        }
    }
    private static SessionFactory buildSessionFactory() {
        // load configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // add entity
        configuration.addAnnotatedClass(Article.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

}
