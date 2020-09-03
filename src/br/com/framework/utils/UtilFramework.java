package br.com.framework.utils;

import org.springframework.stereotype.Component;

/**
 * Responsável por carregar qual usuário está fazendo a operação
 */
@Component
public class UtilFramework {
    private static final long serialVersionUID = 1L;

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    public synchronized static ThreadLocal<Long> getThreadLocal() {
        return threadLocal;
    }
}
