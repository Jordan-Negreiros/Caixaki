package br.com.project.bean.view;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Controller
@SessionScoped
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {
    private static final long serialVersionUID = 1L;

    private Cidade objetoSelecionado = new Cidade();

    @Autowired
    private CidadeController cidadeController;

    @Override
    public String save() throws Exception {
        System.out.println(objetoSelecionado.getCid_descricao());
        return "";
    }

    public Cidade getObjetoSelecionado() {
        return objetoSelecionado;
    }

    public void setObjetoSelecionado(Cidade objetoSelecionado) {
        this.objetoSelecionado = objetoSelecionado;
    }
}
