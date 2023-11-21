package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    public Optional<Ticket> findTicketById(long id);
    @Query(value = "SELECT * FROM ticket", nativeQuery = true)
    public List<Ticket> findTickets();
}
