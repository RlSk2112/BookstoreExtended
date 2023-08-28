package bg.rumen.bookstore.repository;

import bg.rumen.bookstore.domain.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Override
    Optional<Book> findById(Integer id);

    @Query("from Book b where " +
            "(:title is null or b.title = :title) and " +
            "(:author is null or b.author = :author)")
    Page<Book> findAllByTitleAndAuthor(@Param("title") String title, @Param("author") String author, Pageable pageable);

    @Query("select count(b) from Book b where " +
            "(:title is null or b.title = :title) and " +
            "(:author is null or b.author = :author)")
    Integer getBooksCountByTitleAndAuthor(@Param("title") String title, @Param("author") String author);

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateBookTitleById(@Param("title") String title, @Param("id") int id);

    @Modifying
    @Query("update Book b set b.author = :author where b.id = :id")
    void updateBookAuthorById(@Param("author") String author, @Param("id") int id);
}
