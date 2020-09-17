package br.com.repository.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
@Repository
public interface RepositoryCidade extends Serializable {
}
