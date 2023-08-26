package bg.rumen.bookstore.repository;

import bg.rumen.bookstore.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("from Comment c where c.book.id = :bookId and c.id between :startCommentId and :endCommentId")
    List<Comment> findAllCommentsForBookForCurrentPage(@Param("bookId") Integer bookId,
                                                       @Param("startCommentId") Integer startCommentId,
                                                       @Param("endCommentId") Integer endCommentId);

    @Query("select count(c) from Comment c where c.book.id = :id")
    Integer countCommentsByBook(@Param("id") Integer id);
}
