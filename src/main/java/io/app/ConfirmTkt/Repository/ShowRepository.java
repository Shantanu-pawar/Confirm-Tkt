package io.app.ConfirmTkt.Repository;

import io.app.ConfirmTkt.Entities.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
}
