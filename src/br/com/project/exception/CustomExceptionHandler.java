package br.com.project.exception;

import br.com.framework.hibernate.session.HibernateUtil;
import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;

/**
 * Responsavel por manipular as execeções em JSF
 */
@SuppressWarnings({"ConstantConditions", "IndexOfReplaceableByContains"})
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private final ExceptionHandler wrapperd;

    /* Obtém uma instância do FacesContext */
    final FacesContext facesContext = FacesContext.getCurrentInstance();

    /* Obtém um Map do FacesContext */
    final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();

    /* Obtém o estado atual de navegação entre as páginas do JSF */
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

    public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
        this.wrapperd = exceptionHandler;
    }

    /**
     * Sobrescreve o método ExceptionHandle
     * @return pilha de exceções
     */
    @Override
    public ExceptionHandler getWrapped() {
        return wrapperd;
    }

    /**
     * Sobrescreve o método handle que é responsável por manipular as exceções do JSF
     * @throws FacesException JSF Exceptions
     */
    @Override
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();

        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            /* Recupera a exceção do contexto */
            Throwable exception = context.getException();

            /* Aqui trabalhamos a exceção */
            try {
                requestMap.put("exceptionMessage", exception.getMessage());

                if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("ConstraintViolationException") != -1) {

                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
                            .SEVERITY_WARN, "Registro não pode ser removido por" + " estar associado.", ""));

                } else if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("ConstraintViolationException") != -1) {

                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
                            .SEVERITY_ERROR, "Registro foi atualizado ou excluído por outro usuário." + " Consulte novamente.", ""));

                } else {

                    /* Avisa o usário do erro */
                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
                            .SEVERITY_FATAL, "O sistema se recuperou de um erro inesperado.", ""));

                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
                            .SEVERITY_INFO, "Você pode continuar usando o sistema normalmente!", ""));

                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
                            .SEVERITY_FATAL, "O erro foi causado por: \n" + exception.getMessage(), ""));

                    /* PrimeFaces - Alert exibido caso a página não seja redirecionada */
                    RequestContext.getCurrentInstance().execute("alert('O sistema se recuperou de um erro inesperado.')");

                    /* PrimeFaces - Dialog */
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage
                            .SEVERITY_INFO, "Erro", "O Sistemma se recuperou de um erro inesperado"));

                    /* Redireciona para página de erro */
                    navigationHandler.handleNavigation(facesContext, null, "/error/error.jsf?faces-redirect=true&expired=true");
                }

                /* Renderiza a página de erro e exibe as mensagens */
                facesContext.renderResponse();

            } finally {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
                /* Imprime erro no console */
                exception.printStackTrace();
                iterator.remove();
            }
        }

        getWrapped().handle();
    }

}
