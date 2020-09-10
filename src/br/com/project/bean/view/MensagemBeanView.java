package br.com.project.bean.view;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;

@Controller
@Scope(value = "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract {
    private static final long serialVersionUID = 1L;

    @Override
    public String novo() throws Exception {
        System.out.println("método bean view mensagem");
        return super.novo();
    }
}
