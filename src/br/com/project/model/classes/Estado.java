package br.com.project.model.classes;

import br.com.project.annotation.IdentificaCampoPesquisa;
import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Audited
@Entity
@Table(name = "estado")
@SequenceGenerator(name = "estado_seq", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "est_codigo")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_seq")
    private Long est_codigo;

    @Column(length = 10, nullable = true)
    private String est_uf;

    @IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "est_nome", principal = 1)
    @Column(nullable = false, length = 100)
    private String est_nome;

    @NotAudited
    @OneToMany(mappedBy = "estado", orphanRemoval = false)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Cidade> cidades = new ArrayList<Cidade>();

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pais")
    @NotNull
    @ForeignKey(name = "pais_fk")
    private Pais pais = new Pais();

    @Version
    @Column(name = "versionNum")
    private int versionNum;

    public Long getEst_codigo() {
        return est_codigo;
    }

    public void setEst_codigo(Long est_codigo) {
        this.est_codigo = est_codigo;
    }

    public String getEst_uf() {
        return est_uf;
    }

    public void setEst_uf(String est_uf) {
        this.est_uf = est_uf;
    }

    public String getEst_nome() {
        return est_nome;
    }

    public void setEst_nome(String est_nome) {
        this.est_nome = est_nome;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
        Estado estado = (Estado) o;
        return Objects.equals(est_codigo, estado.est_codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(est_codigo);
    }
}
