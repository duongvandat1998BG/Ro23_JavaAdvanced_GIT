package com.vti.ultis;

import com.vti.entity.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUlti {
    private static HibernateUlti instance;

    private Configuration configuration;
    private SessionFactory sessionFactory;

    public static HibernateUlti getInstance() {
        if (null == instance) {
            instance = new HibernateUlti();
        }
        return instance;
    }

    private HibernateUlti() {
        configure();
    }

    private void configure() {
        // load configuration
        configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // add entity
        configuration.addAnnotatedClass(Article.class);
    }

    private SessionFactory buildSessionFactory() {
        if (null == sessionFactory || sessionFactory.isClosed()) {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

    public void closeFactory() {
        if (null != sessionFactory && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }

    public Session openSession() {
        buildSessionFactory();
        return sessionFactory.openSession();
    }

}
