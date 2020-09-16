package br.com.project.model.classes;

import br.com.project.annotation.IdentificaCampoPesquisa;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Audited
@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "cidade_seq", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "cid_codigo")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_seq")
    private Long cid_codigo;

    @IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "cid_descricao", principal = 1)
    @Column(nullable = false, length = 100)
    private String cid_descricao;

    @IdentificaCampoPesquisa(descricaoCampo = "Estado", campoConsulta = "estado.est_nome")
    @Basic
    @ManyToOne
    @JoinColumn(name = "estado", nullable = false)
    @ForeignKey(name = "estado_fk")
    private Estado estado = new Estado();

    @Version
    @Column(name = "versionNum")
    private int versionNum;

    public Long getCid_codigo() {
        return cid_codigo;
    }

    public void setCid_codigo(Long cid_codigo) {
        this.cid_codigo = cid_codigo;
    }

    public String getCid_descricao() {
        return cid_descricao;
    }

    public void setCid_descricao(String cid_descricao) {
        this.cid_descricao = cid_descricao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(cid_codigo, cidade.cid_codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid_codigo);
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "cid_codigo=" + cid_codigo +
                ", cid_descricao='" + cid_descricao + '\'' +
                '}';
    }
}
