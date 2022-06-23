package com.vti.repository;

import com.vti.entity.Article;
import com.vti.ultis.HibernateUlti;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ArticleRepository {
    private HibernateUlti hibernateUlti;

    public ArticleRepository(){
        hibernateUlti = HibernateUlti.getInstance();
    }

    public List<Article> getAll(){
        Session session = null;

        try {
            session = hibernateUlti.openSession();
            Query<Article> query = session.createQuery("FROM Article");
            List<Article> articles = query.list();

            return articles;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return  null;
    }

    public void save(Article article){
        Session session = null;

        try {
            session = hibernateUlti.openSession();
            session.save(article);
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }
}
