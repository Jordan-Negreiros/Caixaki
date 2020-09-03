package br.com.project.filter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.utils.UtilFramework;
import br.com.project.listener.ContextLoaderListenerCaixakiUtils;
import br.com.project.model.classes.Entidade;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

/**
 * Responsavel por iniciar a Session/Transaction do hibernate e iterceptar as
 * Requests/Responses, Commit e Rollback.
 */
@WebFilter(filterName = "conexaoFilter")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements Serializable {
    private static final long serialVersionUID = 1L;

    private static SessionFactory sessionFactory;

    /* Executado quando a aplicação está sendo iniciada, executado apenas uma vez */
    @Override
    protected void initFilterBean() throws ServletException {

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /* JDBC Spring Framework */
        BasicDataSource springBasicDataSource = (BasicDataSource) ContextLoaderListenerCaixakiUtils.getBean("springDataSource");
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(springBasicDataSource);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            /* Define a codificação */
            request.setCharacterEncoding("UTF-8");

            /* Captura o usuário logado na sessão */
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            HttpSession session = servletRequest.getSession();
            Entidade userLogadoSessao = (Entidade) session.getAttribute("userLogadoSessao");

            if (userLogadoSessao != null) {
                UtilFramework.getThreadLocal().set(userLogadoSessao.getEnt_codigo());
            }

            /* Inicia uma transação */
            sessionFactory.getCurrentSession().beginTransaction();

            /* Antes da execução (Request) */
            filterChain.doFilter(request, response); /* Executa ação no servidor */
            /* Após a execução (Response) */

            transactionManager.commit(transactionStatus);

            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().flush();
                sessionFactory.getCurrentSession().getTransaction().commit();
            }

            if (sessionFactory.getCurrentSession().isOpen()) {
                sessionFactory.getCurrentSession().close();
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

        } catch (Exception e) {

            transactionManager.rollback(transactionStatus);
            e.printStackTrace();

            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }

            if (sessionFactory.getCurrentSession().isOpen()) {
                sessionFactory.getCurrentSession().close();
            }

        } finally {

            if (sessionFactory.getCurrentSession().isOpen()) {

                if (sessionFactory.getCurrentSession().beginTransaction().isActive()) {
                    sessionFactory.getCurrentSession().flush();
                    sessionFactory.getCurrentSession().clear();
                }

                if (sessionFactory.getCurrentSession().isOpen()) {
                    sessionFactory.getCurrentSession().close();
                }
            }

        }

    }
}
