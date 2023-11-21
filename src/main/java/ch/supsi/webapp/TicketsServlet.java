package ch.supsi.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/tickets/*")
public class TicketsServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    private final HashMap<String, Ticket> tickets;

    public TicketsServlet() {
        this.tickets = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] splitUri = req.getRequestURI().substring(1).split("/");

        resp.setStatus(HttpServletResponse.SC_OK);

        String toRespond;
        switch (splitUri.length){
            case 1:
                toRespond = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.tickets);
                break;
            case 2:
                Ticket ticket = this.tickets.get(splitUri[1]);
                if(ticket != null)
                    toRespond = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticket);
                else{
                    toRespond = "Item not found.";
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

                break;
            default:
                toRespond = "Request not valid.";
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }

        resp.getWriter().println(toRespond);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ticket ticket = null;

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        if(req.getParameter("title") != null
                && req.getParameter("description") != null
                && req.getParameter("author") != null){
            ticket = new Ticket(req.getParameter("title"), req.getParameter("description"), req.getParameter("author"));

        }else{
            String json = req.getReader().lines().collect(Collectors.joining());
            if(json.length() > 0){
                ticket = this.mapper.readValue(json, Ticket.class);
            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }

        if(resp.getStatus() == HttpServletResponse.SC_CREATED) {
            this.tickets.put(ticket.getId(), ticket);
        }

        ServletOutputStream out = resp.getOutputStream();
        if(resp.getStatus() == HttpServletResponse.SC_CREATED)
            out.println(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticket));
        out.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] splitUri = req.getRequestURI().substring(1).split("/");

        Ticket ticket = null;

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        if(splitUri.length == 2){
            ticket = this.tickets.get(splitUri[1]);

            if(req.getParameter("title") == null
                    && req.getParameter("description") == null
                    && req.getParameter("author") == null) {

                String json = req.getReader().lines().collect(Collectors.joining());
                if(json.length() > 0){
                    ticket.setTicket(this.mapper.readValue(json, Ticket.class));
                }else{
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }else{
                ticket.setTitle(req.getParameter("title"));
                ticket.setDescription(req.getParameter("description"));
                ticket.setAuthor(req.getParameter("author"));
            }
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        ServletOutputStream out = resp.getOutputStream();

        if(resp.getStatus() == HttpServletResponse.SC_OK)
            out.println(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticket));
        
        out.close();
    }

    public void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] splitUri = req.getRequestURI().substring(1).split("/");

        Ticket ticket = null;

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        if(splitUri.length == 2){
            ticket = this.tickets.get(splitUri[1]);

            Map<?, ?> map = req.getParameterMap();

            if(map.size() > 0){
                if(map.containsKey("title"))
                    ticket.setTitle(req.getParameter("title"));

                if(map.containsKey("description"))
                    ticket.setDescription(req.getParameter("description"));

                if(map.containsKey("author"))
                    ticket.setAuthor(req.getParameter("author"));
            }

            String json = req.getReader().lines().collect(Collectors.joining());
            if(json.length() > 0){
                map = this.mapper.readValue(json, Map.class);

                if(map.containsKey("title"))
                    ticket.setTitle(map.get("title").toString());

                if(map.containsKey("description"))
                    ticket.setDescription(map.get("description").toString());

                if(map.containsKey("author"))
                    ticket.setAuthor(map.get("author").toString());
            }
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        ServletOutputStream out = resp.getOutputStream();
        
        if(resp.getStatus() == HttpServletResponse.SC_OK)
            out.println(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticket));
        
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] splitUri = req.getRequestURI().substring(1).split("/");

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        if(splitUri.length == 2){
            Ticket t = this.tickets.remove(splitUri[1]);
            if(t == null)
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

            resp.getWriter().println("Removed ticket " + splitUri[1]);
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("PATCH")){
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }
}
