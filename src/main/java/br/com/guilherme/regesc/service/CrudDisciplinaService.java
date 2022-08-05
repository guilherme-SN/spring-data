package br.com.guilherme.regesc.service;

import br.com.guilherme.regesc.orm.Aluno;
import br.com.guilherme.regesc.orm.Disciplina;
import br.com.guilherme.regesc.orm.Professor;
import br.com.guilherme.regesc.repository.AlunoRepository;
import br.com.guilherme.regesc.repository.DisciplinaRepository;
import br.com.guilherme.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrudDisciplinaService {
    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova disciplina");
            System.out.println("2 - Atualizar uma disciplina");
            System.out.println("3 - Deletar uma disciplina");
            System.out.println("4 - Visualizar todas disciplinas");
            System.out.println("5 - Matricular alunos");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> this.cadastrar(scanner);
                case 2 -> this.atualizar(scanner);
                case 3 -> this.deletar(scanner);
                case 4 -> this.visualizar();
                case 5 -> this.matricularAlunos(scanner);
                default -> isTrue = false;
            }
        }
        System.out.println("==================================");
    }


    private void cadastrar(Scanner scanner) {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.next();

        System.out.print("Semestre: ");
        Integer semestre = scanner.nextInt();

        System.out.print("Professor ID: ");
        Long professorId = scanner.nextLong();

        Optional<Professor> optional = professorRepository.findById(professorId);       // Para evitar erro (cadastrar uma disciplina com professor inexistente)
        if (optional.isPresent()) {
            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println(">>>Disciplina salva no banco!!!<<<");
        }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID da disciplina a ser atualizada: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();

            System.out.print("Nome da disciplina: ");
            String nome = scanner.next();

            System.out.print("Semestre: ");
            Integer semestre = scanner.nextInt();

            System.out.print("Professor ID: ");
            Long professorId = scanner.nextLong();

            Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);
            if (optionalProfessor.isPresent()) {
                Professor professor = optionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                this.disciplinaRepository.save(disciplina);

                System.out.println(">>>Disciplina atualizada<<<");
            }


        } else {
            System.out.println("ID inválido!!!\n");
        }
    }

    private void visualizar() {
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();

        for (Disciplina disciplina : disciplinas) {       // foreach
            System.out.println(disciplina.toString());
        }
        System.out.println("\n");
    }


    private void deletar(Scanner scanner) {
        System.out.print("Digite o ID da disciplina a ser deletada: ");
        Long id = scanner.nextLong();

        this.disciplinaRepository.deleteById(id);    // Se não existir, vai lançar uma Exception
        System.out.println(">>>Disciplina deletada do BANCO!!!<<<\n");
    }

    private Set<Aluno> matricular(Scanner scanner) {
        Boolean isTrue = true;
        Set<Aluno> alunos = new HashSet<Aluno>();

        while (isTrue) {
            System.out.print("Digite o ID do aluno a ser matriculado (0 para sair): ");
            Long alunoId = scanner.nextLong();

            if (alunoId > 0) {
                Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoId);
                if (alunoOptional.isPresent()) {
                    alunos.add(alunoOptional.get());
                } else {
                    System.out.println("ID do aluno inválido!");
                }
            } else {
                isTrue = false;
            }
        }
        return alunos;
    }

    private void matricularAlunos(Scanner scanner) {
        System.out.print("Digite o ID da disciplina para matricular alunos: ");
        Long disciplinaId = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(disciplinaId);
        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();

            Set<Aluno> novosAlunos = this.matricular(scanner);
            disciplina.getAlunos().addAll(novosAlunos);

            this.disciplinaRepository.save(disciplina);
        } else {
            System.out.println("ID da disciplina inválido!");
        }
    }
}
