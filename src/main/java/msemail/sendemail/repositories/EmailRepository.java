package msemail.sendemail.repositories;

import msemail.sendemail.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
