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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
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
        validSessionFactory();
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
        executeFlushSession();
    }

    @Override
    public void update(T obj) throws Exception {
        validSessionFactory();
        sessionFactory.getCurrentSession().update(obj);
        executeFlushSession();
    }

    @Override
    public void delete(T obj) throws Exception {
        validSessionFactory();
        sessionFactory.getCurrentSession().delete(obj);
        executeFlushSession();
    }

    @Override
    public T merge(T obj) throws Exception {
        validSessionFactory();
        obj = (T) sessionFactory.getCurrentSession().merge(obj);
        executeFlushSession();
        return obj;
    }

    @Override
    public List<T> findList(Class<T> entity) throws Exception {
        validSessionFactory();
        String query = "select distinct(entity) from " + entity.getSimpleName() + " entity ";

        return (List<T>) sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @Override
    public Object findById(Class<T> entidade, Long id) throws Exception {
        validSessionFactory();
        return sessionFactory.getCurrentSession().load(getClass(), id);
    }

    @Override
    public T findPorId(Class<T> entidade, Long id) throws Exception {
        return null;
    }

    @Override
    public List<T> findListByDynamicQuery(String query) throws Exception {
        validSessionFactory();
        List<T> lista = new ArrayList<T>();
        lista = sessionFactory.getCurrentSession()
                .createQuery(query.toString()).list();
        return lista;
    }

    @Override
    public List<T> findListOrderByProperty(Class<T> entidade, String propriedade) throws Exception{
        validSessionFactory();
        StringBuilder query = new StringBuilder();
        query.append("select entity from ").append(entidade.getSimpleName())
                .append(" entity ").append(" order by entity.")
                .append(propriedade);
        List<T> lista = sessionFactory.getCurrentSession()
                .createQuery(query.toString()).list();
        return lista;
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
        validSessionFactory();
        sessionFactory.getCurrentSession().evict(obj);
    }

    @Override
    public Session getSession() throws Exception {
        validSessionFactory();
        return sessionFactory.getCurrentSession();
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

    public T findUniqueByQueryDinamica(String query) throws Exception {
        validSessionFactory();
        T obj = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();
        return obj;
    }

    protected T findUniqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {

        validSessionFactory();

        StringBuilder query = new StringBuilder();
        query.append(" select entity from ")
                .append(entidade.getSimpleName())
                .append(" entity where entity.")
                .append(atributo).append(" = '").append(valor)
                .append("' ")
                .append(condicao);

        T obj = (T) this.findUniqueByQueryDinamica(query.toString());

        return obj;
    }
}
