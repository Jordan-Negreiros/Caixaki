package br.com.project.converters;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Estado;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String codigo) {

        if (codigo != null && !codigo.isEmpty()) {
            return (Estado) HibernateUtil.getCurrentSession().get(Estado.class, new Long(codigo));
        }

        return codigo;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objeto) {

        if (objeto != null) {
            Estado c = (Estado) objeto;
            return c.getEst_codigo() != null && c.getEst_codigo() > 0 ? c.getEst_codigo().toString() : null;
        }

        return null;
    }
}
