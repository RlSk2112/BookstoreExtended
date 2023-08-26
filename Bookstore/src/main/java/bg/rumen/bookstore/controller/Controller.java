package bg.rumen.bookstore.controller;

import bg.rumen.bookstore.domain.PageResult;
import bg.rumen.bookstore.domain.dto.BookExportDto;
import bg.rumen.bookstore.domain.dto.BookImportDto;
import bg.rumen.bookstore.domain.dto.CommentExportDto;
import bg.rumen.bookstore.domain.dto.CommentImportDto;
import bg.rumen.bookstore.domain.entities.Book;
import bg.rumen.bookstore.domain.params.BookSearchParams;
import bg.rumen.bookstore.domain.params.PageParams;
import bg.rumen.bookstore.service.BookService;
import bg.rumen.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookstore")
public class Controller {

    private final BookService bookService;

    private final CommentService commentService;

    @Autowired
    public Controller(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping
    public PageResult<BookExportDto> getBooks(BookSearchParams searchParams, PageParams pageParams) {
        return this.bookService.getBooksPageResult(searchParams, pageParams);
    }

    @PostMapping
    public void addBook(@RequestBody BookImportDto book) {
        bookService.addBook(book);
    }

    @PatchMapping("/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable Integer id) {
        this.bookService.editBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        this.bookService.deleteBookById(id);

    }

    @GetMapping("/comments/{id}")
    public PageResult<CommentExportDto> getComments(@PathVariable Integer id, PageParams pageParams) {
        return this.commentService.getComments(id, pageParams);
    }

    @PostMapping("/comments/{id}")
    public void addComment(@PathVariable Integer id, @RequestBody CommentImportDto commentImportDto) {
        this.commentService.addComment(id, commentImportDto);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteCommentById(@PathVariable Integer id) {
        this.commentService.deleteCommentById(id);
    }
}
