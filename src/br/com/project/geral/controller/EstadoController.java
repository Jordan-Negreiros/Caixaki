package br.com.project.geral.controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Estado;
import org.springframework.stereotype.Controller;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EstadoController extends ImplementacaoCrud<Estado> implements InterfaceCrud<Estado> {
    private static final long serialVersionUID = 1L;

    public List<SelectItem> getListEstado() throws Exception {

        List<SelectItem> list = new ArrayList<SelectItem>();
        List<Estado> estados = super.findListOrderByProperty(Estado.class,
                "est_nome");

        for (Estado estado: estados) {
            list.add(new SelectItem(estado, estado.getEst_nome()));
        }

        return list;
    }
}
