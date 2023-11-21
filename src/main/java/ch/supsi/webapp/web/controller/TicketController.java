package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TicketController {
    private final Map<String, Ticket> tickets = new ConcurrentHashMap<>();

    @RequestMapping(value="/tickets", method= RequestMethod.GET)
    public List<Ticket> get() {
        return new ArrayList<>(this.tickets.values());
    }

    @RequestMapping(value="/tickets/{id}", method=RequestMethod.GET)
    public ResponseEntity<Ticket> getTicket(@PathVariable String id) {
        if(!this.tickets.containsKey(id))
            return  new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Ticket>(this.tickets.get(id), HttpStatus.OK);
    }

    @RequestMapping(value="/tickets", method = RequestMethod.POST)
    public ResponseEntity<Ticket> post(@RequestBody Ticket ticket){
        this.tickets.putIfAbsent(ticket.getId(), ticket);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
    }

    @RequestMapping(value="/tickets/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> put(@RequestBody Ticket ticket, @PathVariable String id){

        if(!this.tickets.containsKey(id))
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);

        ticket.setId(id);
        this.tickets.put(id, ticket);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @RequestMapping(value="/tickets/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable String id) {
        if(!this.tickets.containsKey(id))
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);

        this.tickets.remove(id);
        return new ResponseEntity<>("{success:true}", HttpStatus.OK);
        //System.out.println("Requested ticket " + id);
    }
}
