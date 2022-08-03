package br.com.guilherme.regesc.orm;

import javax.persistence.*;
import java.util.List;

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
    private List<Disciplina> disciplinas;       // Um aluno pode ter uma lista de disciplinas

    // Construtores
    public Aluno() {
    }

    public Aluno(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
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
}
