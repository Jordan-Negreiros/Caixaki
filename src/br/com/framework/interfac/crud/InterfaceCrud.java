package br.com.framework.interfac.crud;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

    // salva os dados
    void save(T obj) throws Exception;

    // salva os dados
    void persist(T obj) throws Exception;

    // salva ou atualiza
    void saveOrUpdate(T obj) throws Exception;

    // realiza o update/atualizacao dos dados
    void update(T obj) throws Exception;

    // realiza o delete de dados
    void delete(T obj) throws Exception;

    // salva ou atualiza e retorna o objeto em estado persistente
    T merge(T obj) throws Exception;

    // carrega a lista de dados de determinada classe
    List<T> findList(Class<T> objs) throws Exception;

    // retorna entidade por id
    Object findById(Class<T> entidade, Long id) throws Exception;

    // retorna entidade por id
    T findPorId(Class<T> entidade, Long id) throws Exception;

    // retorna uma lista de acordo com uma query dinamica
    List<T> findListByDynamicQuery(String query) throws Exception;

    // executar update de acordo com uma query dinamica
    void executeUpdateByDynamicQuery(String query) throws Exception;

    // executar update de acordo com sql dinamico
    void executeUpdateByDynamicSQL(String sql) throws Exception;

    // limpa a sessao do hibernate
    void clearSession() throws Exception;

    // retira um objeto da sessao do hibernate
    void evict(Object obj) throws Exception;

    // retorna a sessao do hibernate
    Session getSession() throws Exception;

    // retorna uma lista de objetos de acordo com sql dinamico
    List<?> getListSQLDinamica(String sql) throws Exception;

    // metodos para trabalhar com jdbc do Spring
    JdbcTemplate getJdbcTemplate();

    // metodos para trabalhar com jdbc do Spring
    SimpleJdbcTemplate getSimpleJdbcTemplate();

    // metodos para trabalhar com jdbc do Spring
    SimpleJdbcInsert getSimpleJdbcInsert();

    // metodos para trabalhar com jdbc do Spring
    SimpleJdbcCall getSimpleJdbcClass();

    // retorna o total de registro de uma tabela
    Long totalRegistros(String table) throws Exception;

    // monta querys dinamicas
    Query getQuery(String query) throws Exception;

    // retorna lista generica de dados por demanda
    List<T> findListByDynamicQuery(String query, int initialRegistration, int maxResult) throws Exception;
}
