package ch.supsi.webapp.web.service;


import ch.supsi.webapp.web.model.Attachment;
import ch.supsi.webapp.web.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    public List<Attachment> get() {
        return this.attachmentRepository.findAttachments();
    }

    public Optional<Attachment> getAttachment(@PathVariable long id) {
        return this.attachmentRepository.findAttachmentById(id);
    }

    public Optional<Attachment> post(@RequestBody Attachment attachment){
        return Optional.of(attachmentRepository.save(attachment));
    }

    public Optional<Attachment> put(@RequestBody Attachment attachment, @PathVariable long id){
        attachmentRepository.save(attachment);

        Optional<Attachment> currentAttachment = attachmentRepository.findById(id);

        return currentAttachment;
    }

    public boolean delete(@PathVariable long id) {
        this.attachmentRepository.deleteById(id);

        Optional<Attachment> attachmentData = attachmentRepository.findById(id);
        return attachmentData.isEmpty();
    }
}
