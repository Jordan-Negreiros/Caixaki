package br.com.project.listener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class ContextLoaderListenerCaixakiUtils extends ContextLoader implements Serializable {
    private static final long serialVersionUID = 1L;

    private static WebApplicationContext getWebApplicationContext() {
        return WebApplicationContextUtils.getWebApplicationContext(getCurrentWebApplicationContext().getServletContext());
    }

    public static Object getBean(String idNomeBean) {
        return getWebApplicationContext().getBean(idNomeBean);
    }

    public static Object getBean(Class<?> classe) {
        return getWebApplicationContext().getBean(classe);
    }
}
