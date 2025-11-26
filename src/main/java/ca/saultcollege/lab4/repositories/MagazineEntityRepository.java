package ca.saultcollege.lab4.repositories;

import ca.saultcollege.lab4.entities.MagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineEntityRepository extends JpaRepository<MagazineEntity, Long> {
}