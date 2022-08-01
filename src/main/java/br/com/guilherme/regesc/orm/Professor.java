package br.com.guilherme.regesc.orm;

import javax.persistence.*;

@Entity                                                 // Faz a classe professor virar uma entidade (tabela no BD)
@Table(name = "professores")                            // Anotação para adicionar configurações na Table
public class Professor {
    // Atributos
    @Id                                                 // A variável id se torna a chave primária
    @GeneratedValue(                                    // Esse id será gerado automaticamente
            strategy = GenerationType.IDENTITY          // Seguindo a estratégia de ser sequencial e único
    )
    private Long id;

    @Column(nullable = false)                           // Anotações para adicionar configurações na Coluna
    private String nome;

    @Column(nullable = false, unique = true)
    private String prontuario;


    // Método Construtor
    @Deprecated                                         // O Hibernate exige esse tipo de construtor, mas não usaremos ele
    public Professor() {
    }

    public Professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
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

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prontuario='" + prontuario + '\'' +
                '}';
    }
}


