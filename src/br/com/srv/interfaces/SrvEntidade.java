package br.com.srv.interfaces;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Service
public interface SrvEntidade extends Serializable {

    Date getUltimoAcessoEntidadeLogada(String name);

    void updateUltimoAcessoUser(String ent_login);

    boolean existeUsuario(String ent_login);
}
