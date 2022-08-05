package br.com.guilherme.regesc.service;

import br.com.guilherme.regesc.orm.Aluno;
import br.com.guilherme.regesc.orm.Disciplina;
import br.com.guilherme.regesc.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudAlunoService {
    private AlunoRepository alunoRepository;

    public CrudAlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo aluno");
            System.out.println("2 - Atualizar um aluno");
            System.out.println("3 - Deletar um aluno");
            System.out.println("4 - Visualizar todos alunos");
            System.out.println("5 - Visualizar um aluno");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> this.cadastrar(scanner);
                case 2 -> this.atualizar(scanner);
                case 3 -> this.deletar(scanner);
                case 4 -> this.visualizar();
                case 5 -> this.visualizarAluno(scanner);
                default -> isTrue = false;
            }
        }
        System.out.println("==================================");
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.next();

        System.out.printf("Digite a idade de %s: ", nome);
        Integer idade = scanner.nextInt();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);

        this.alunoRepository.save(aluno);

        System.out.println(">>>Aluno salvo no Banco!!!<<<");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID do aluno a ser atualizado: ");
        Long alunoId = scanner.nextLong();

        Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoId);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            System.out.print("Digite o nome do aluno: ");
            String nome = scanner.next();

            System.out.printf("Digite a idade de %s: ", nome);
            Integer idade = scanner.nextInt();

            aluno.setNome(nome);
            aluno.setIdade(idade);

            this.alunoRepository.save(aluno);

            System.out.println(">>>Aluno atualizado!!!<<<");
        } else {
            System.out.println("ID do aluno inválido!");
        }
    }

    private void deletar(Scanner scanner) {
        System.out.print("Digite o ID do aluno a ser deletado: ");
        Long alunoId = scanner.nextLong();

        Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoId);
        if (alunoOptional.isPresent()) {
            this.alunoRepository.deleteById(alunoId);
        } else {
            System.out.println("ID do aluno inválido");
        }
    }

    private void visualizar() {
        Iterable<Aluno> alunos = this.alunoRepository.findAll();

        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    @Transactional
    private void visualizarAluno(Scanner scanner) {
        System.out.print("Digite o ID do aluno a ser visualizado: ");
        Long alunoId = scanner.nextLong();

        Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoId);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();

            System.out.println("\tAluno{");
            System.out.println("\tNome: " + aluno.getNome());
            System.out.println("\tIdade: " + aluno.getIdade());
            System.out.println("\tDisciplinas[");

            if (aluno.getDisciplinas() != null) {
                for (Disciplina disciplina : aluno.getDisciplinas()) {
                    System.out.println("\t\tNome: " + disciplina.getNome());
                    System.out.println("\t\tSemestre: " +disciplina.getSemestre());
                    System.out.println("]}");
                }
            }
        } else {
            System.out.println("ID do aluno inválido!");
        }
    }
}
