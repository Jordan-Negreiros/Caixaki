package br.com.project.util.all;

import javax.annotation.PostConstruct;
import java.io.Serializable;

public interface ActionViewPadrao extends Serializable {

    abstract void limparLista() throws Exception;

    abstract String save() throws Exception;

    abstract void saveNotReturn() throws Exception;

    abstract void saveEdit() throws Exception;

    abstract void excluir() throws Exception;

    abstract String ativar() throws Exception;

    /* @PostConstruct realiza a inicialização de metodos, valores ou variáveis */
    @PostConstruct
    abstract String novo() throws Exception;

    abstract String editar() throws Exception;

    abstract void setarVariaveisNulas() throws Exception;

    abstract void consultarEntidades() throws Exception;

    abstract void statusOperation(EstatusPersistencia estatusPersistencia) throws Exception;

    abstract String redirecionarNewEntidade() throws Exception;

    abstract String redirecionarFindEntidade() throws Exception;

    abstract void addMsg(String msg) throws Exception;

}
