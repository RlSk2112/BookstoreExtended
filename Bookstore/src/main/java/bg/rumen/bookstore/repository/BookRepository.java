package bg.rumen.bookstore.repository;

import bg.rumen.bookstore.domain.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("from Book b where b.id between :start and :end")
    List<Book> findAllBooksForCurrentPage(@Param("start") Integer start, @Param("end") Integer end);

    @Override
    Optional<Book> findById(Integer id);

    @Transactional
    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateBookTitleById(@Param("title") String title, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("update Book b set b.author = :author where b.id = :id")
    void updateBookAuthorById(@Param("author") String author, @Param("id") int id);
}
