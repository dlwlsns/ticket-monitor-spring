package ch.supsi.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/tickets")
public class TicketsServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    private final ArrayList<Ticket> tickets;

    public TicketsServlet() {
        this.tickets = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.tickets));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("title") != null
                && req.getParameter("description") != null
                && req.getParameter("author") != null){
            this.tickets.add(new Ticket(req.getParameter("title"), req.getParameter("description"), req.getParameter("author")));
        }else{
            this.tickets.add(this.mapper.readValue(req.getReader().lines().collect(Collectors.joining()), Ticket.class));
        }

        ServletOutputStream out = resp.getOutputStream();
        out.println(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.tickets.get(this.tickets.size()-1)));
        out.close();
    }
}
