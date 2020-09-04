package br.com.project.geral.controller;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ApplicationScoped
public interface SessionController extends Serializable {

    void addSession(String keyLoginUser, HttpSession httpSession);

    void invalidateSession(String keyLoginUser);
}
