package ca.saultcollege.lab4.repositories;

import ca.saultcollege.lab4.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByAuthor(String author);


    List<BookEntity> findByTitleContainingIgnoreCase(String title);
}