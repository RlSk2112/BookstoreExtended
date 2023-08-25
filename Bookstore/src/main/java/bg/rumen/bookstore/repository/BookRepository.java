package bg.rumen.bookstore.repository;

import bg.rumen.bookstore.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("from Book b where b.id between :start and :end")
    List<Book> findAllLimited(@Param("start") Integer start, @Param("end") Integer end);

    @Override
    List<Book> findAll();
}
