package br.com.framework.hibernate.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Responsavel por estabelecer a conexão com hibernate
 */
public class HibernateUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    private static SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Responsavel por ler o arquivo de configuracao hibernate.cfg.xml
     * @return sessioFactory
     */
    private static SessionFactory buildSessionFactory() {

        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }

            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Erro ao criar conexão Session Factory");
        }
    }

    /**
     * Retorna o Session Factory corrente
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Retorna a sessão do Session Factory
     * @return Session
     */
    public static Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    /**
     * Abre uma nova sessão no Session Factory
     * @return Session
     */
    public static Session openSession() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }

        return sessionFactory.openSession();
    }

    /**
     * Obtem a Connetion do provedor de conexoes configurado
     * @return Connection SQL
     */
    public static Connection getConnectionProvider() throws SQLException {
        return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
    }
}
