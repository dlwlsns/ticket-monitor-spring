package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> get() {
        return this.ticketRepository.findTickets();
    }

    public Optional<Ticket> getTicket(@PathVariable String id) {
        return this.ticketRepository.findTicketById(id);
    }

    public Optional<Ticket> post(@RequestBody Ticket ticket){
        return Optional.of(ticketRepository.save(ticket));
    }

    public Optional<Ticket> put(@RequestBody Ticket ticket, @PathVariable String id){
        Optional<Ticket> currentTicket = ticketRepository.findById(id);

        if (currentTicket.isPresent()) {
            Ticket newTicket = currentTicket.get();
            newTicket.setTitle(ticket.getTitle());
            newTicket.setDescription(ticket.getDescription());
            //TODO: check if user exists
            newTicket.setAuthor(ticket.getAuthor());
            newTicket.setStatus(ticket.getStatus());
            newTicket.setType(ticket.getType());

            currentTicket = Optional.of(newTicket);

            this.delete(id);
            this.post(newTicket);
        }

        return currentTicket;
    }

    public boolean delete(@PathVariable String id) {
        this.ticketRepository.deleteById(id);

        Optional<Ticket> ticketData = ticketRepository.findById(id);
        return ticketData.isEmpty();
    }
}
