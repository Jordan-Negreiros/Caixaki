package br.com.project.bean.geral;

import javax.faces.component.UIViewRoot;
import javax.faces.event.*;
import java.util.HashMap;
import java.util.Map;

import static br.com.project.bean.geral.ViewScope.*;

@SuppressWarnings("unchecked")
public class ViewScopeCallbackRegister implements ViewMapListener {

    @Override
    public void processEvent(SystemEvent systemEvent) throws AbortProcessingException {

        if (systemEvent instanceof PostConstructViewMapEvent) {
            PostConstructViewMapEvent viewMapEvent = (PostConstructViewMapEvent) systemEvent;
            UIViewRoot uiViewRoot = (UIViewRoot) viewMapEvent.getComponent();
            uiViewRoot.getViewMap().put(VIEW_SCOPE_CALLBACKS, new HashMap<String, Runnable>());

        } else if (systemEvent instanceof PreDestroyViewMapEvent) {
            PreDestroyViewMapEvent viewMapEvent = (PreDestroyViewMapEvent) systemEvent;
            UIViewRoot uiViewRoot = (UIViewRoot) viewMapEvent.getComponent();
            Map<String, Runnable> callbacks = (Map<String, Runnable>) uiViewRoot.getViewMap().get(VIEW_SCOPE_CALLBACKS);

            if (callbacks != null) {
                for (Runnable c : callbacks.values()) {
                    c.run();
                }
                callbacks.clear();
            }
        }
    }

    @Override
    public boolean isListenerForSource(Object source) {
        return source instanceof UIViewRoot;
    }
}
