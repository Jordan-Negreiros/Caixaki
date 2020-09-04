package br.com.srv.interfaces;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface SrvLogin extends Serializable {

    boolean autentico(String login, String senha) throws Exception;
}
