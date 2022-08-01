package br.com.guilherme.regesc.service;

import br.com.guilherme.regesc.orm.Professor;
import br.com.guilherme.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
            System.out.println("2 - Atualizar um professor");
            System.out.println("3 - Deletar um professores");
            System.out.println("4 - Visualizar todos professores");


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
        System.out.print("Nome do professor: ");
        String nome = scanner.next();

        System.out.printf("Prontuário de %s: ", nome);
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);

        this.professorRepository.save(professor);

        System.out.println(">>Professor salvo no BANCO!!!<<\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID do professor a ser atualizado: ");
        Long id = scanner.nextLong();

        // Optional serve para não ter que ficar tratando valores NULL manualmente com exceções
        Optional<Professor> optional = this.professorRepository.findById(id);

        // Se o Hibernate conseguiu achar uma tupla/registro na tabela de professores com o id passado, faça:
        if (optional.isPresent()) {
            System.out.print("Nome do professor: ");
            String nome = scanner.next();

            System.out.printf("Prontuário de %s: ", nome);
            String prontuario = scanner.next();

            Professor professor = optional.get();   // "Recupera" os dados do professor com o id passado
            professor.setNome(nome);
            professor.setProntuario(prontuario);
            this.professorRepository.save(professor);

            System.out.println(">>Professor atualizado no BANCO!!!<<\n");
        } else {
            System.out.println("ID inválido!!!\n");
        }
    }


    private void visualizar() {
        Iterable<Professor> professores = this.professorRepository.findAll();   // Encontre todos os registros e jogue na variável do tipo Iterable de Professor

        for (Professor professor : professores) {       // foreach
            System.out.println(professor.toString());
        }
        System.out.println("\n");
    }


    private void deletar(Scanner scanner) {
        System.out.print("Digite o ID do professor a ser deletado: ");
        Long id = scanner.nextLong();

        this.professorRepository.deleteById(id);    // Se não existir, vai lançar uma Exception
        System.out.println(">>Professor deletado do BANCO!!!<<<\n");
    }
}

