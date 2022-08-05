package br.com.guilherme.regesc.orm;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alunos")
public class Aluno {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private Integer idade;

    @ManyToMany(mappedBy = "alunos")            // Alunos podem ter várias disciplinas e vice versa
    private Set<Disciplina> disciplinas;       // Um aluno pode ter uma lista de disciplinas

    // Construtores
    public Aluno() {
    }

    public Aluno(String nome, Integer idade, Set<Disciplina> disciplinas) {
        this.nome = nome;
        this.idade = idade;
        this.disciplinas = disciplinas;
    }


    // Métodos especiais
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
