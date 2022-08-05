package br.com.guilherme.regesc.repository;

import br.com.guilherme.regesc.orm.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}
