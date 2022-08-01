package br.com.guilherme.regesc.service;

import br.com.guilherme.regesc.orm.Professor;
import br.com.guilherme.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service        // Faz o Spring injetar os objetos automaticamente
public class CrudProfessorService {
    private ProfessorRepository professorRepository;    // Dependência da classe CrudProfessorService

    // Spring automaticamente cria um objeto com a interface 'ProfessorRepository'
    // e o injeta nesse construtor da classe atual ==> INJEÇÃO DE DEPENDÊNCIA
    public CrudProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo professor");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                default:
                    isTrue = false;
            }
        }
        System.out.println("==================================");
    }


    public void cadastrar(Scanner scanner) {

        System.out.print("Nome do professor: ");
        String nome = scanner.next();

        System.out.printf("Prontuário de %s: ", nome);
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);

        this.professorRepository.save(professor);

        System.out.println(">>Professor salvo no BANCO!!!<<\n");
    }
}

