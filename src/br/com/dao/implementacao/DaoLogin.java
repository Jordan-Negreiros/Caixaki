package br.com.dao.implementacao;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.repository.interfaces.RepositoryLogin;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DaoLogin extends ImplementacaoCrud<Object> implements RepositoryLogin {

    @Override
    public boolean autentico(String login, String senha) throws Exception {

        String sql = "select count(1) as autentica from entidade where ent_login = ? and ent_senha = ?";
        SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, login, senha);
        return sqlRowSet.next() && sqlRowSet.getInt("autentica") > 0;

    }
}
