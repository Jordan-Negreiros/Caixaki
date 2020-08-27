package br.com.project.annotation;

import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampoPesquisa {

    String descricaoCampo(); // descrição do campo para a tela
    String campoConsulta(); // campo do banco
    int principal() default 10000; // posição que irá aparecer no campo

}
