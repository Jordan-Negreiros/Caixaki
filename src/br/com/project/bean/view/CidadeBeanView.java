package br.com.project.bean.view;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionScoped
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {
    private static final long serialVersionUID = 1L;

    private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";

    private Cidade objetoSelecionado = new Cidade();

    private List<Cidade> cidades = new ArrayList<Cidade>();

    @Autowired
    private CidadeController cidadeController;

    @Override
    public String save() throws Exception {
        objetoSelecionado = cidadeController.merge(objetoSelecionado);
        return ""; // retorna para mesma tela
    }

    @Override
    public String novo() throws Exception {
        objetoSelecionado = new Cidade();
        return url;
    }

    @Override
    public String editar() throws Exception {

        return url;
    }

    @Override
    public void excluir() throws Exception {
        cidadeController.delete(objetoSelecionado);
        novo();
    }

    public Cidade getObjetoSelecionado() {
        return objetoSelecionado;
    }

    public void setObjetoSelecionado(Cidade objetoSelecionado) {
        this.objetoSelecionado = objetoSelecionado;
    }

    public List<Cidade> getCidades() throws Exception {
        cidades = cidadeController.findList(Cidade.class);
        return cidades;
    }
}
