package br.com.framework.implementacao.crud;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {
    private static final long serialVersionUID = 1L;

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Autowired
    private JdbcTemplateImpl jdbcTemplate;

    @Autowired
    private SimpleJdbcTemplateImpl simpleJdbcTemplate;

    @Autowired
    private SimpleJdbcInsertImpl simpleJdbcInsert;

    @Autowired
    private SimpleJdbcClassImpl simpleJdbcClass;

    @Override
    public void save(T obj) throws Exception {
        validSessionFactory();
        sessionFactory.getCurrentSession().save(obj);
        executeFlushSession();
    }

    @Override
    public void persist(T obj) throws Exception {
        validSessionFactory();
        sessionFactory.getCurrentSession().persist(obj);
        executeFlushSession();
    }

    @Override
    public void saveOrUpdate(T obj) throws Exception {

    }

    @Override
    public void update(T obj) throws Exception {

    }

    @Override
    public void delete(T obj) throws Exception {

    }

    @Override
    public T merge(T obj) throws Exception {
        return null;
    }

    @Override
    public List<T> findList(Class<T> objs) throws Exception {
        return null;
    }

    @Override
    public Object findById(Class<T> entidade, Long id) throws Exception {
        return null;
    }

    @Override
    public T findPorId(Class<T> entidade, Long id) throws Exception {
        return null;
    }

    @Override
    public List<T> findListByDynamicQuery(String query) throws Exception {
        return null;
    }

    @Override
    public void executeUpdateByDynamicQuery(String query) throws Exception {

    }

    @Override
    public void executeUpdateByDynamicSQL(String sql) throws Exception {

    }

    @Override
    public void clearSession() throws Exception {

    }

    @Override
    public void evict(Object obj) throws Exception {

    }

    @Override
    public Session getSession() throws Exception {
        return null;
    }

    @Override
    public List<?> getListSQLDinamica(String sql) throws Exception {
        return null;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public SimpleJdbcCall getSimpleJdbcClass() {
        return simpleJdbcClass;
    }

    @Override
    public Long totalRegistros(String table) throws Exception {
        return null;
    }

    @Override
    public Query getQuery(String query) throws Exception {
        return null;
    }

    @Override
    public List<T> findListByDynamicQuery(String query, int initialRegistration, int maxResult) throws Exception {
        return null;
    }

    private void validSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        validTransaction();
    }

    private void validTransaction() {
        if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
            sessionFactory.getCurrentSession().beginTransaction();
        }
    }

    private void commitAjaxProcess() {
        sessionFactory.getCurrentSession().beginTransaction().commit();
    }

    private void rollbackAjaxProcess() {
        sessionFactory.getCurrentSession().beginTransaction().rollback();
    }

    /**
     * Roda instantaneamente o SQL no banco de dados
     */
    private void executeFlushSession() {
        sessionFactory.getCurrentSession().flush();
    }
}
