package bg.rumen.bookstore.service;

import bg.rumen.bookstore.domain.PageResult;
import bg.rumen.bookstore.domain.dto.BookExportDto;
import bg.rumen.bookstore.domain.dto.BookImportDto;
import bg.rumen.bookstore.domain.entities.Book;
import bg.rumen.bookstore.domain.params.BookSearchParams;
import bg.rumen.bookstore.domain.params.PageParams;
import bg.rumen.bookstore.exceptions.NoSuchBookWithIdException;
import bg.rumen.bookstore.repository.BookRepository;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/json/books.json";

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;

    public PageResult<BookExportDto> getBooksPageResult(BookSearchParams bookSearchParams, PageParams pageParams) {
        if (!areInitialBooksImported()) {
            addInitialBooks();
        }
        int start = pageParams.getPage() * pageParams.getLimit() - (pageParams.getLimit() - 1);
        int end = pageParams.getPage() * pageParams.getLimit();

        List<Book> allByIdLessThanOrderById =
                bookRepository.findAllBooksForCurrentPage(start, end);

        List<BookExportDto> exportDtos = allByIdLessThanOrderById.stream()
                .map(book -> modelMapper.map(book, BookExportDto.class))
                .toList();

        PageResult<BookExportDto> pageResult = new PageResult<>();
        pageResult.setItems(exportDtos);
        pageResult.setTotalRecords((int) bookRepository.count());
        return pageResult;
    }

    public Book getBookById(Integer id) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NoSuchBookWithIdException(id);
        }
        return byId.get();
    }

    public void editBook(Integer id, Book book) {
        if (book.getTitle() != null) {
            bookRepository.updateBookTitleById(book.getTitle(), id);
        }
        if (book.getAuthor() != null) {
            bookRepository.updateBookAuthorById(book.getAuthor(), id);
        }
    }

    public void addBook(BookImportDto bookImportDto) {
        if (bookImportDto == null) {
            throw new IllegalArgumentException("Book to import cannot be null!");
        }
        Book book = modelMapper.map(bookImportDto, Book.class);
        bookRepository.save(book);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

    private boolean areInitialBooksImported() {
        return bookRepository.count() > 0;
    }

    private String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(BOOKS_FILE_PATH));
    }

    private void addInitialBooks() {
        BookImportDto[] bookImportDtos;
        try {
            bookImportDtos = gson.fromJson(readBooksFromFile(), BookImportDto[].class);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        List<Book> books = Arrays.stream(bookImportDtos)
                .map(bookImportDto -> modelMapper.map(bookImportDto, Book.class))
                .toList();
        bookRepository.saveAll(books);
    }
}
