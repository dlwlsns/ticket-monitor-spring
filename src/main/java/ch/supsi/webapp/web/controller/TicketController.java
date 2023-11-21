package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.model.Type;
import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.service.TicketService;
import ch.supsi.webapp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @GetMapping(value="/")
    public String get(Model model) {
        model.addAttribute("tickets", this.ticketService.get());
        return "index";
    }

    @GetMapping(value="/ticket/{id}")
    public String getTicket(@PathVariable String id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicket(id);

        if(ticket.isEmpty()){
            model.addAttribute("thing", "Ticket");
            return "notFound";
        }

        model.addAttribute("ticket", ticket.get());
        model.addAttribute("authors", this.userService.get());
        model.addAttribute("editable", false);

        return "ticketDetails";
    }

    @GetMapping(value="/ticket/new")
    public String getNewTicket(Model model) {
        model.addAttribute("authors", this.userService.get());
        return "createTicketForm";
    }

    @PostMapping(value="/ticket/new")
    public String postNewTicket(@RequestParam("title") String title,
                                @RequestParam("type") String type,
                                @RequestParam("author") String authorId,
                                @RequestParam("description") String description,
                                Model model) {
        Optional<User> author = this.userService.getUser(authorId);

        if(author.isEmpty()) {
            model.addAttribute("thing", "Author");
            return "notFound";
        }

        this.ticketService.post(
                new Ticket(title, description, author.get(), Type.fromValue(type))
        );

        return "redirect:/";
    }

    @GetMapping(value="/ticket/{id}/delete")
    public String getDeleteTicket(@PathVariable String id) {
        this.ticketService.delete(id);

        return "redirect:/";
    }

    @GetMapping(value="/ticket/{id}/edit")
    public String getEditTicket(@PathVariable String id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicket(id);

        if(ticket.isEmpty()){
            model.addAttribute("thing", "Ticket");
            return "notFound";
        }

        model.addAttribute("ticket", ticket.get());
        model.addAttribute("authors", this.userService.get());
        model.addAttribute("editable", true);

        return "ticketDetails";
    }

    @PostMapping(value="/ticket/{id}/edit")
    public String postEditTicket(@PathVariable String id,
                                 @RequestParam("title") String title,
                                 @RequestParam("status") String status,
                                 @RequestParam("type") String type,
                                 @RequestParam("author") String authorId,
                                 @RequestParam("description") String description,
                                 Model model) {
        Optional<User> author = this.userService.getUser(authorId);

        if(author.isEmpty()) {
            model.addAttribute("thing", "Author");
            return "notFound";
        }

        Ticket ticket = new Ticket(title,
                description,
                author.get(),
                Status.fromValue(status),
                Type.fromValue(type));

        this.ticketService.put(ticket, id);

        return "redirect:/";
    }
}
