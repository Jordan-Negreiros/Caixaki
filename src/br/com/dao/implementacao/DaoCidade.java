package br.com.dao.implementacao;


import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Cidade;
import br.com.repository.interfaces.RepositoryCidade;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCidade extends ImplementacaoCrud<Cidade> implements RepositoryCidade {
    private static final long serialVersionUID = 1L;
}
