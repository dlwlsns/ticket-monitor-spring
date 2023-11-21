package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.model.Ticket;
import ch.supsi.webapp.web.model.Type;
import org.springframework.data.jpa.repository.Modifying;
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
    //@Modifying
    //@Query("update ticket t set t.title = ?1, t.status = ?2, t.author_id = ?3, t.type = ?4, t.description = ?5, t.attachment_id = ?6 where t.id = ?7")
    //void updateTicket(String title, Status status, long authorId, Type type, String decription, long attchmentId, long id);
}
