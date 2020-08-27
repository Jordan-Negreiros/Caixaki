package br.com.project.bean.geral;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class ObjetoCampoConsulta implements Serializable, Comparator<ObjetoCampoConsulta> {
    private static final long serialVersionUID = 1L;

    private String descricao;
    private String campoBanco;
    private Object tipoClasse;
    private Integer principal;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCampoBanco() {
        return campoBanco;
    }

    public void setCampoBanco(String campoBanco) {
        this.campoBanco = campoBanco;
    }

    public Object getTipoClasse() {
        return tipoClasse;
    }

    public void setTipoClasse(Object tipoClasse) {
        this.tipoClasse = tipoClasse;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetoCampoConsulta that = (ObjetoCampoConsulta) o;
        return Objects.equals(campoBanco, that.campoBanco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campoBanco);
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    @Override
    public int compare(ObjetoCampoConsulta o1, ObjetoCampoConsulta o2) {
        return ((ObjetoCampoConsulta)o1).getPrincipal()
                .compareTo(((ObjetoCampoConsulta)o2).getPrincipal());
    }
}
