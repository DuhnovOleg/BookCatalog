package com.example.book.catalog.config;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HibernateConfig {


    private static SessionFactory sessionFactory;

    @Bean
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
                configuration.configure();
                configuration.addAnnotatedClass(Author.class);
                configuration.addAnnotatedClass(Book.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
}
