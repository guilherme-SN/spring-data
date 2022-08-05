package br.com.guilherme.regesc.orm;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "disciplinas")
public class Disciplina {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private Integer semestre;

    @ManyToOne      // As disciplinas podem ter apenas um professor
    @JoinColumn(name = "professor_id", nullable = true)     // Define o nome da coluna de junção (nome da FK da classe Professor)
    private Professor professor;

    @ManyToMany(fetch = FetchType.EAGER)        // Disciplinas podem ter vários alunos e vice versa
    @JoinTable(name = "disciplinas_alunos",     // Nome da tabela intermediária da relação disciplinas <--> alunos
               joinColumns = @JoinColumn(name = "disciplina_fk"),       // Nome da foreign key do owning side
               inverseJoinColumns = @JoinColumn(name = "aluno_fk"))     // Nome da foreign key do non-owning side
    private Set<Aluno> alunos;     // Uma disciplina pode ter uma lista de alunos

    // Construtores
    @Deprecated
    public Disciplina() {
    }

    public Disciplina(String nome, Integer semestre, Professor professor) {
        this.nome = nome;
        this.semestre = semestre;
        this.professor = professor;
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

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", semestre=" + semestre +
                ", professor=" + professor +
                ", alunos=" + alunos +
                '}';
    }
}
