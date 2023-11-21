package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, String> {
    public Optional<Ticket> findTicketById(String id);
    @Query(value = "SELECT * FROM ticket", nativeQuery = true)
    public List<Ticket> findTickets();
}
