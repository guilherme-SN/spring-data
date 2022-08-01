package br.com.guilherme.regesc.repository;

import br.com.guilherme.regesc.orm.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository     // Indica que essa interface é um repositório de interações
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
