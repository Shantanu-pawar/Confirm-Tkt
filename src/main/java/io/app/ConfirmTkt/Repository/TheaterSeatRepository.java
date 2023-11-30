package io.app.ConfirmTkt.Repository;

import io.app.ConfirmTkt.Entities.TheaterSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeatEntity, Integer> {
}
