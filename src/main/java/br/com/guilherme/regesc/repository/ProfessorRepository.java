package br.com.guilherme.regesc.repository;

import br.com.guilherme.regesc.orm.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository     // Indica que essa interface é um repositório de interações
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
    // SQL nativo
    @Query(nativeQuery = true, value = "SELECT * FROM professores p INNER JOIN disciplinas d ON p.id = d.professor_id WHERE p.nome like :nomeProfessor% AND d.nome like :nomeDisciplina%")
    List<Professor> findProfessorAtribuido(String nomeProfessor, String nomeDisciplina);
}
