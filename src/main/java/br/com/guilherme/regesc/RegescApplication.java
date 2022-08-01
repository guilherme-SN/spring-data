package br.com.guilherme.regesc;

import br.com.guilherme.regesc.orm.Professor;
import br.com.guilherme.regesc.repository.ProfessorRepository;
import br.com.guilherme.regesc.service.CrudProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {
    private CrudProfessorService professorService;

    public RegescApplication(CrudProfessorService professorService) {
        this.professorService = professorService;
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

            int opcao = scanner.nextInt();

            if (opcao == 1) {
                this.professorService.menu(scanner);
            } else {
                isTrue = false;
            }
       }
    }
}

