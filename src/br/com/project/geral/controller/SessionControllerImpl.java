package br.com.project.geral.controller;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@ApplicationScoped
public class SessionControllerImpl implements SessionController{
    private static final long serialVersionUID = 1L;

    private HashMap<String, HttpSession> sessionHashMap = new HashMap<String, HttpSession>();

    @Override
    public void addSession(String keyLoginUser, HttpSession httpSession) {
        sessionHashMap.put(keyLoginUser, httpSession);
    }

    @Override
    public void invalidateSession(String keyLoginUser) {

        HttpSession session = sessionHashMap.get(keyLoginUser);

        if (session != null) {
            /* remove sessão do usuário passado como paramêtros */
            try {
                session.invalidate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sem usuário");
        }

        sessionHashMap.remove(keyLoginUser);
    }
}
