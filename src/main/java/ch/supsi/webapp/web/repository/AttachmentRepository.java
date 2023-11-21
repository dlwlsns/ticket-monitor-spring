package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Attachment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
    public Optional<Attachment> findAttachmentById(long id);
    @Query(value = "SELECT * FROM attachment", nativeQuery = true)
    public List<Attachment> findAttachments();
}
