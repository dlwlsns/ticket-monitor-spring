package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;


    @GetMapping(value="/tickets")
    public List<Ticket> get() {
        return this.ticketService.get();
    }

    @GetMapping(value="/tickets/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String id) {
        Optional<Ticket> ticket = ticketService.getTicket(id);

        if(ticket.isEmpty())
            return  new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Ticket>(ticket.get(), HttpStatus.OK);
    }

    @PostMapping(value="/tickets")
    public ResponseEntity<Ticket> post(@RequestBody Ticket ticket){
        Optional<Ticket> newTicket = ticketService.post(ticket);
        return newTicket
                .map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping(value="/tickets/{id}")
    public ResponseEntity<Ticket> put(@RequestBody Ticket ticket, @PathVariable String id){
        Optional<Ticket> ticketData = ticketService.put(ticket, id);

        return ticketData
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value="/tickets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable String id) {
        if(this.ticketService.delete(id))
            return new ResponseEntity<>("{success:true}", HttpStatus.OK);
        else
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
    }
}
