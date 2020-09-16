package br.com.project.model.classes;

import br.com.project.annotation.IdentificaCampoPesquisa;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Audited
@Entity
@Table(name = "pais")
@SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1)
public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;

    @IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "pais_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")
    private Long pais_id;

    @IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "pais_nome", principal = 1)
    @Column(nullable = false, length = 80)
    private String pais_nome;

    @Column(nullable = true, length = 15)
    private String pais_sigla;

    @Version
    @Column(name = "versionNum")
    private int versionNum;

    public Long getPais_id() {
        return pais_id;
    }

    public void setPais_id(Long pais_id) {
        this.pais_id = pais_id;
    }

    public String getPais_nome() {
        return pais_nome;
    }

    public void setPais_nome(String pais_nome) {
        this.pais_nome = pais_nome;
    }

    public String getPais_sigla() {
        return pais_sigla;
    }

    public void setPais_sigla(String pais_sigla) {
        this.pais_sigla = pais_sigla;
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
        Pais pais = (Pais) o;
        return Objects.equals(pais_id, pais.pais_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pais_id);
    }
}
