package io.app.ConfirmTkt.Repository;

import io.app.ConfirmTkt.Entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
}
