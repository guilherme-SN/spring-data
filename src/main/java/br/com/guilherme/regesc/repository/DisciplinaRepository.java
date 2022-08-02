package br.com.guilherme.regesc.repository;

import br.com.guilherme.regesc.orm.Disciplina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

}
