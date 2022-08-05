package br.com.guilherme.regesc;

import br.com.guilherme.regesc.orm.Professor;
import br.com.guilherme.regesc.repository.ProfessorRepository;
import br.com.guilherme.regesc.service.CrudAlunoService;
import br.com.guilherme.regesc.service.CrudDisciplinaService;
import br.com.guilherme.regesc.service.CrudProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {
    private CrudProfessorService professorService;
    private CrudDisciplinaService disciplinaService;
    private CrudAlunoService alunoService;

    public RegescApplication(CrudProfessorService professorService, CrudDisciplinaService disciplinaService, CrudAlunoService alunoService) {
        this.professorService = professorService;
        this.disciplinaService = disciplinaService;
        this.alunoService = alunoService;
    }

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Boolean isTrue = true;
        Scanner scanner = new Scanner(System.in);

        while (isTrue) {
            System.out.println("Qual entidade você deseja interagir?");
            System.out.println("0 - Sair");
            System.out.println("1 - Professor");
            System.out.println("2 - Disciplina");
            System.out.println("3 - Aluno");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> this.professorService.menu(scanner);
                case 2 -> this.disciplinaService.menu(scanner);
                case 3 -> this.alunoService.menu(scanner);
                default -> isTrue = false;
            }
       }
    }
}

