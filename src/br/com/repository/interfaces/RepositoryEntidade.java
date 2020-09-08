package br.com.repository.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;

@Transactional
@Repository
public interface RepositoryEntidade extends Serializable {

    Date getUltimoAcessoEntidadeLogada(String name);

    void updateUltimoAcessoUser(String ent_login);

    boolean existeUsuario(String ent_login);

}
