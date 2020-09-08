package br.com.srv.implementacao;

import br.com.repository.interfaces.RepositoryEntidade;
import br.com.srv.interfaces.SrvEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SrvEntidadeImpl implements SrvEntidade {
    private static final long serialVersionUID = 1L;

    @Autowired
    private RepositoryEntidade repositoryEntidade;

    @Override
    public Date getUltimoAcessoEntidadeLogada(String name) {
        return repositoryEntidade.getUltimoAcessoEntidadeLogada(name);
    }

    @Override
    public void updateUltimoAcessoUser(String ent_login) {
        repositoryEntidade.updateUltimoAcessoUser(ent_login);
    }

    @Override
    public boolean existeUsuario(String ent_login) {
        return repositoryEntidade.existeUsuario(ent_login);
    }
}
