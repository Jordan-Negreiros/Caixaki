package br.com.project.bean.view;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.EstadoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.util.List;

@Controller
@SessionScoped
@ManagedBean(name="estadoBeanView")
public class EstadoBeanView extends BeanManagedViewAbstract {
    private static final long serialVersionUID = 1L;

    @Autowired
    private EstadoController estadoController;

    public List<SelectItem> getEstados() throws Exception {
        return estadoController.getListEstado();
    }
}
