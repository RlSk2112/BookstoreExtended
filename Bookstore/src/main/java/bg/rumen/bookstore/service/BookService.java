package bg.rumen.bookstore.service;

import bg.rumen.bookstore.domain.dto.BookExportDto;
import bg.rumen.bookstore.domain.entities.Book;
import bg.rumen.bookstore.domain.entities.PageResult;
import bg.rumen.bookstore.domain.params.PageParams;
import bg.rumen.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    private ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public PageResult<BookExportDto> getBooksPageResult(PageParams pageParams) {

        int start = pageParams.getPage() * pageParams.getLimit() - (pageParams.getLimit() - 1);
        int end = pageParams.getPage() * pageParams.getLimit();

        List<Book> allByIdLessThanOrderById =
                bookRepository.findAllLimited(start, end);

        List<BookExportDto> exportDtos = allByIdLessThanOrderById.stream()
                .map(book -> modelMapper.map(book, BookExportDto.class))
                .toList();

        PageResult<BookExportDto> pageResult = new PageResult<>();
        pageResult.setItems(exportDtos);
        pageResult.setTotalRecords((int) bookRepository.count());
        return pageResult;
    }
}
