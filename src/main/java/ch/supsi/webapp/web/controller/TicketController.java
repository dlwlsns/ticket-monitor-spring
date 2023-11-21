package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.*;
import ch.supsi.webapp.web.service.AttachmentService;
import ch.supsi.webapp.web.service.TicketService;
import ch.supsi.webapp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping(value="/")
    public String get(Model model) {
        model.addAttribute("tickets", this.ticketService.get());
        return "index";
    }

    @GetMapping(value="/ticket/{id}")
    public String getTicket(@PathVariable long id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicket(id);

        if(ticket.isEmpty()){
            model.addAttribute("thing", "Ticket");
            return "notFound";
        }

        model.addAttribute("ticket", ticket.get());
        model.addAttribute("authors", this.userService.get());
        model.addAttribute("editable", false);
        model.addAttribute("attachment", "");

        return "ticketDetails";
    }

    @GetMapping(value="/ticket/{id}/download")
    public ResponseEntity<byte[]> getTicketAttachment(@PathVariable long id) {
        Optional<Ticket> ticket = ticketService.getTicket(id);

        if(ticket.isEmpty())
            return null;

        Attachment attachment = ticket.get().getAttachment();
        if(attachment == null)
            return null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, attachment.getType());
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(attachment.getName()).build().toString());

        return ResponseEntity.ok().headers(httpHeaders).body(attachment.getContent());
    }

    @GetMapping(value="/ticket/new")
    public String getNewTicket(Model model) {
        model.addAttribute("authors", this.userService.get());
        return "createTicketForm";
    }

    @PostMapping(value="/ticket/new")
    public String postNewTicket(@RequestParam("title") String title,
                                @RequestParam("type") String type,
                                @RequestParam("author") long authorId,
                                @RequestParam("description") String description,
                                @RequestParam("attachment") MultipartFile attachment,
                                Model model) throws IOException {
        Optional<User> author = this.userService.getUser(authorId);

        if(author.isEmpty()) {
            model.addAttribute("thing", "Author");
            return "notFound";
        }

        Ticket ticket = new Ticket(title, description, author.get(), Type.fromValue(type));

        if(!attachment.isEmpty()){
            Attachment a = new Attachment(
                    attachment.getOriginalFilename(),
                    attachment.getBytes(),
                    attachment.getContentType());

            ticket.setAttachment(attachmentService.post(a).get());
            System.out.print("new attachment" + ticket.getAttachment().getId());
        }

        this.ticketService.post(ticket);

        return "redirect:/";
    }

    @GetMapping(value="/ticket/{id}/delete")
    public String getDeleteTicket(@PathVariable long id) {
        this.ticketService.delete(id);

        return "redirect:/";
    }

    @GetMapping(value="/ticket/{id}/edit")
    public String getEditTicket(@PathVariable long id, Model model) {
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
    public String postEditTicket(@PathVariable long id,
                                 @RequestParam("title") String title,
                                 @RequestParam("status") String status,
                                 @RequestParam("type") String type,
                                 @RequestParam("author") long authorId,
                                 @RequestParam("description") String description,
                                 @RequestParam("attachment") MultipartFile attachment,
                                 Model model) throws IOException {
        Optional<Ticket> ticket = ticketService.getTicket(id);

        if(ticket.isEmpty()) {
            model.addAttribute("thing", "Ticket");
            return "notFound";
        }

        Optional<User> author = this.userService.getUser(authorId);

        if(author.isEmpty()) {
            model.addAttribute("thing", "Author");
            return "notFound";
        }

        Ticket t = ticket.get();
        t.setTitle(title);
        t.setDescription(description);
        t.setAuthor(author.get());
        t.setStatus(Status.fromValue(status));
        t.setType(Type.fromValue(type));

        long prevAttach = -1;
        if(!attachment.isEmpty()){
            Attachment a = new Attachment(attachment.getOriginalFilename(),
                    attachment.getBytes(),
                    attachment.getContentType());

            if(t.getAttachment() != null)
                prevAttach = t.getAttachment().getId();
            t.setAttachment(attachmentService.post(a).get());
        }

        this.ticketService.put(t, id);

        if(prevAttach != -1)
            attachmentService.delete(prevAttach);

        return "redirect:/";
    }
}
