package br.com.guilherme.regesc.service;

import br.com.guilherme.regesc.orm.Disciplina;
import br.com.guilherme.regesc.orm.Professor;
import br.com.guilherme.regesc.repository.DisciplinaRepository;
import br.com.guilherme.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudDisciplinaService {
    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
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


            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.deletar(scanner);
                    break;
                case 4:
                    this.visualizar();
                    break;
                default:
                    isTrue = false;
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
}
