package bg.rumen.bookstore.repository;

import bg.rumen.bookstore.domain.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findAllByBook_Id(Integer bookId, Pageable pageable);

    @Query("select count(c) from Comment c where c.book.id = :id")
    Integer countCommentsByBook(@Param("id") Integer id);
}
