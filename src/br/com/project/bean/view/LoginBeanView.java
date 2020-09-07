package br.com.project.bean.view;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.SessionController;
import br.com.srv.interfaces.SrvLogin;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(value = "request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private SrvLogin srvLogin;

    @RequestMapping(value = "**/invalidate_session", method = RequestMethod.POST)
    public void invalidateSessionContrala(HttpServletRequest httpServletRequest) throws Exception {

        String userLogadoSessao = null;

        if (httpServletRequest.getUserPrincipal() != null){
            userLogadoSessao = httpServletRequest.getUserPrincipal().getName();
        }

        if (userLogadoSessao == null || (userLogadoSessao != null && userLogadoSessao.trim().isEmpty())) {
            userLogadoSessao = httpServletRequest.getRemoteUser();
        }

        if (userLogadoSessao != null && !userLogadoSessao.isEmpty())
            sessionController.invalidateSession(userLogadoSessao);
    }

    public void invalidate(ActionEvent actionEvent) throws Exception {

        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;

        if (srvLogin.autentico(getUsername(), getPassword())) {
            sessionController.invalidateSession(getUsername());
            loggedIn = true;
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso negado", "Login ou Senha Incorretos.");
        }

        if (message != null) {
            FacesContext.getCurrentInstance().addMessage("msg", message);
        }

        requestContext.addCallbackParam("loggedIn", loggedIn);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
