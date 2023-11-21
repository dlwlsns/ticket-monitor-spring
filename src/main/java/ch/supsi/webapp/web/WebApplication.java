package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.repository.TicketRepository;
import ch.supsi.webapp.web.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

				users.add(new User("Paolo", "Bonolis", "bono"));
				users.add(new User("Topolino", "P", "Mickey"));
				users.add(new User("Franco", "Rossi", "Frank"));

				userRepository.saveAll(users);

				List<Ticket> tickets = new ArrayList<>();

				tickets.add(new Ticket("Problema internet", "La rete della SUPSI a volte non funziona.", users.get(0), Status.OPEN));
				tickets.add(new Ticket("Pipeline non funziona", "La pipeline di ingegneria del software 2 non funziona, send help.", users.get(2), Status.IN_PROGRESS));
				tickets.add(new Ticket("Persona dispersa", "Non so se questo è il posto giusto, ma non trovo più Minnie.", users.get(1), Status.DONE));

				ticketRepository.saveAll(tickets);
			}
		};
	}
}
