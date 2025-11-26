package ca.saultcollege.lab4.repositories;

import ca.saultcollege.lab4.entities.DiscMagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscMagEntityRepository extends JpaRepository<DiscMagEntity, Long> {
}