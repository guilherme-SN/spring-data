package br.com.guilherme.regesc.service;

import br.com.guilherme.regesc.orm.Aluno;
import br.com.guilherme.regesc.orm.Professor;
import br.com.guilherme.regesc.repository.AlunoRepository;
import br.com.guilherme.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RelatorioService {
    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;

    public RelatorioService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual relatório você deseja?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Alunos por um dado nome");
            System.out.println("2 - Alunos por um dado nome e idade menor ou igual");
            System.out.println("3 - Alunos por um dado nome e idade maior ou igual");
            System.out.println("4 - Alunos matriculados com um dado nome e uma idade maior ou igual");
            System.out.println("5 - Professores atribuidos a uma dada disciplina");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> this.alunosPorNome(scanner);
                case 2 -> this.alunosPorNomeIdadeMenorOuIgual(scanner);
                case 3 -> this.alunosPorNomeIdadeMaiorOuIgual(scanner);
                case 4 -> this.alunosMatriculadosComNomeIdadeMaiorOuIgual(scanner);
                case 5 -> this.professoresAtribuidos(scanner);
                default -> isTrue = false;
            }
        }
        System.out.println("==================================");
    }

    private void alunosPorNome(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();

        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWith(nome);
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    private void alunosPorNomeIdadeMenorOuIgual(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Idade: ");
        Integer idade = scanner.nextInt();

        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWithAndIdadeLessThanEqual(nome, idade);
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    private void alunosPorNomeIdadeMaiorOuIgual(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Idade: ");
        Integer idade = scanner.nextInt();

        List<Aluno> alunos = this.alunoRepository.findNomeIdadeIgualOuMaior(nome, idade);
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    private void alunosMatriculadosComNomeIdadeMaiorOuIgual(Scanner scanner) {
        System.out.print("Nome: ");
        String nomeAluno = scanner.next();

        System.out.print("Idade: ");
        Integer idadeAluno = scanner.nextInt();

        System.out.print("Disciplina: ");
        String nomeDisciplina = scanner.next();

        List<Aluno> alunos = this.alunoRepository.findNomeIdadeIgualOuMaiorMatriculado(nomeAluno, idadeAluno, nomeDisciplina);
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    private void professoresAtribuidos(Scanner scanner) {
        System.out.print("Nome do professor: ");
        String nomeProfessor = scanner.next();

        System.out.print("Nome da disciplina: ");
        String nomeDisciplina = scanner.next();

        List<Professor> professores = this.professorRepository.findProfessorAtribuido(nomeProfessor, nomeDisciplina);
        for (Professor professor : professores) {
            System.out.println(professor);
        }
    }
}
