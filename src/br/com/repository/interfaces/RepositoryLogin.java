package br.com.repository.interfaces;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositoryLogin extends Serializable {

    boolean autentico(String login, String senha) throws Exception;

}
