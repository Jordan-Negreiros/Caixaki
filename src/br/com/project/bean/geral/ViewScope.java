package br.com.project.bean.geral;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementação do scopo em view para o spring
 */
@SuppressWarnings("unchecked")
public class ViewScope implements Scope, Serializable {
    private static final long serialVersionUID = 1L;

    public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

    @Override
    public synchronized Object get(String name, ObjectFactory<?> objectFactory) {
        Object instance = getViewMap().get(name);
        if (instance == null) {
            instance = objectFactory.getObject();
            getViewMap().put(name, instance);
        }
        return instance;
    }

    @Override
    public Object remove(String name) {
        Object instance = getViewMap().remove(name);
        if (instance != null) {
            Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
            if (callBacks != null) {
                callBacks.remove(name);
            }
        }
        return instance;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {
        Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
        if (callbacks != null) {
            callbacks.put(name, runnable);
        }
    }

    @Override
    public Object resolveContextualObject(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
        return facesRequestAttributes.resolveReference(name);
    }

    @Override
    public String getConversationId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);

        return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
    }

    /**
     *  getViewRoot retorna componente raiz que está associado a esta solicitação(request)
     *  getViewMap retorna um Map que atua como a interface para o armazenamento de dados
     */
    private Map<String, Object> getViewMap() {
        return FacesContext.getCurrentInstance() != null ?
                FacesContext.getCurrentInstance().getViewRoot().getViewMap() : new HashMap<String, Object>();
    }
}
