package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.*;
import ch.supsi.webapp.web.repository.TicketRepository;
import ch.supsi.webapp.web.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TicketRepository ticketRepository, UserRepository userRepository) {
		return (args) -> {
			if (userRepository.count() == 0 && ticketRepository.count() == 0) {
				List<User> users = new ArrayList<>();

				users.add(new User("admin", "admin"));
				users.get(0).setRole(Role.ROLE_ADMIN);

				users.add(new User("Patrick", "Ceppi", "ceppi.patrick", "patrick.ceppi"));
				users.get(1).setRole(Role.ROLE_USER);
				users.add(new User("Lorenzo", "Sommaruga", "sommaruga.lorenzo", "lorenzo.sommaruga"));
				users.get(2).setRole(Role.ROLE_USER);
				users.add(new User("Davide", "Ravani", "ravani.davide", "davide.ravani"));
				users.get(3).setRole(Role.ROLE_USER);

				userRepository.saveAll(users);

				List<Ticket> tickets = new ArrayList<>();

				tickets.add(new Ticket("Problema internet", "La rete della SUPSI a volte non funziona.", users.get(1), Status.OPEN, Type.ISSUE));
				tickets.add(new Ticket("Pipeline non funziona", "La pipeline di ingegneria del software 2 non funziona, send help.", users.get(2), Status.IN_PROGRESS, Type.TASK));
				tickets.add(new Ticket("Persona dispersa", "Non so se questo è il posto giusto, ma non trovo più Minnie.", users.get(3), Status.DONE, Type.INVESTIGATION));
				tickets.add(new Ticket("AAAA", "CCCC", users.get(1), Status.DONE, Type.INVESTIGATION));

				ticketRepository.saveAll(tickets);
			}
		};
	}
}
