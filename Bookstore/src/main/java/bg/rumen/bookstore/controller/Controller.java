package bg.rumen.bookstore.controller;

import bg.rumen.bookstore.domain.dto.BookExportDto;
import bg.rumen.bookstore.domain.entities.Book;
import bg.rumen.bookstore.domain.entities.PageResult;
import bg.rumen.bookstore.domain.params.BookSearchParams;
import bg.rumen.bookstore.domain.entities.Comment;
import bg.rumen.bookstore.domain.params.PageParams;
import bg.rumen.bookstore.repository.CommentRepository;
import bg.rumen.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookstore")
public class Controller {

    private final BookService bookService;

    private final CommentRepository commentsRepository;

    @Autowired
    public Controller(BookService bookService, CommentRepository commentsRepository) {
        this.bookService = bookService;
        this.commentsRepository = commentsRepository;
    }

    @GetMapping
    public PageResult<BookExportDto> getBooks(BookSearchParams searchParams, PageParams pageParams) {
        return this.bookService.getBooksPageResult(pageParams);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
//        this.bookRepository.addBook(book);
    }

    @PatchMapping("/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable String id) {
//        this.bookRepository.editBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
//        this.bookRepository.deleteBookById(id);
//        this.commentsRepository.removeAllComments(id);
    }

    @GetMapping("/comments/{id}")
    public PageResult<Comment> getComments(@PathVariable Integer id, PageParams pageParams) {
//        return this.commentsRepository.getComments(id, pageParams);
        return null;
    }

    @PostMapping("/comments/{id}")
    public void addComment(@PathVariable Integer id, @RequestBody Comment comment) {
//        comment.setBookId(id);
//        this.commentsRepository.addComment(comment);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteCommentById(@PathVariable Integer id) {
//       this.commentsRepository.deleteCommentById(id);
    }
}
