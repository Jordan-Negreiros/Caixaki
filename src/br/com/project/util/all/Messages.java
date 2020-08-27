package br.com.project.util.all;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public abstract class Messages extends FacesContext implements Serializable {
    private static final long serialVersionUID = 1L;

    public Messages() {
    }

    public static FacesContext getFacesContext () {
        return FacesContext.getCurrentInstance();
    }

    public static boolean facesContextValido () {
        return getFacesContext() != null;
    }

    public static void msgSeverityWarn (String msg) {
        if (facesContextValido()) {
            getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
        }
    }

    public static void msgSeverityFatal (String msg) {
        if (facesContextValido()) {
            getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
        }
    }

    public static void msgSeverityError (String msg) {
        if (facesContextValido()) {
            getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }

    public static void msgSeverityInfo (String msg) {
        if (facesContextValido()) {
            getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
    }

    public static void erroNaOperacao () {
        if (facesContextValido()) {
            msgSeverityFatal(Constante.ERRO_NA_OPERACAO);
        }
    }

    public static void sucesso () {
        msgSeverityInfo(Constante.SECESSO);
    }

    /**
     * Criar uma mensagem generica
     * @param msg mensagem a ser passada
     */
    public static void msg (String msg) {
        if (facesContextValido()) {
            getFacesContext().addMessage("msg", new FacesMessage(msg));
        }
    }

    public static void responseOperation (EstatusPersistencia estatusPersistencia) {
        if (estatusPersistencia != null && estatusPersistencia.equals(EstatusPersistencia.SUCESSO)) {
            sucesso();
        } else if (estatusPersistencia != null && estatusPersistencia.equals(EstatusPersistencia.OBJETO_REFERENCIADO)) {
            msgSeverityFatal(EstatusPersistencia.OBJETO_REFERENCIADO.toString());
        } else {
            erroNaOperacao();
        }
    }
}
