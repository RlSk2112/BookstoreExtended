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
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {

    private final BookService bookService;

    private final CommentService commentService;

    @GetMapping("/books")
    public PageResult<BookExportDto> getBooks(BookSearchParams searchParams, PageParams pageParams) {
        return this.bookService.getBooksPageResult(searchParams, pageParams);
    }

    @PostMapping("/books")
    public void addBook(@RequestBody BookImportDto book) {
        bookService.addBook(book);
    }

    @PatchMapping("/books/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable Integer id) {
        this.bookService.editBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Integer id) {
        this.bookService.deleteBookById(id);
    }

    @GetMapping("/book-comments/{id}")
    public PageResult<CommentExportDto> getComments(@PathVariable Integer id, PageParams pageParams) {
        return this.commentService.getComments(id, pageParams);
    }

    @PostMapping("/book-comments/{id}")
    public void addComment(@PathVariable Integer id, @RequestBody CommentImportDto commentImportDto) {
        this.commentService.addComment(id, commentImportDto);
    }

    @DeleteMapping("/book-comments/{id}")
    public void deleteCommentById(@PathVariable Integer id) {
        this.commentService.deleteCommentById(id);
    }
}
