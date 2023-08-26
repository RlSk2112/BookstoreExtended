package bg.rumen.bookstore.service;

import bg.rumen.bookstore.domain.PageResult;
import bg.rumen.bookstore.domain.dto.CommentExportDto;
import bg.rumen.bookstore.domain.dto.CommentImportDto;
import bg.rumen.bookstore.domain.entities.Book;
import bg.rumen.bookstore.domain.entities.Comment;
import bg.rumen.bookstore.domain.params.PageParams;
import bg.rumen.bookstore.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final BookService bookService;

    private final ModelMapper modelMapper;

    public PageResult<CommentExportDto> getComments(Integer id, PageParams pageParams) {
        int start = pageParams.getPage() * pageParams.getLimit() - (pageParams.getLimit() - 1);
        int end = pageParams.getPage() * pageParams.getLimit();

        List<Comment> allByIdLessThanOrderById =
                commentRepository.findAllCommentsForBookForCurrentPage(id, start, end);

        List<CommentExportDto> exportDtos = allByIdLessThanOrderById.stream()
                .map(comment -> modelMapper.map(comment, CommentExportDto.class))
                .toList();

        PageResult<CommentExportDto> pageResult = new PageResult<>();
        pageResult.setItems(exportDtos);
        pageResult.setTotalRecords(commentRepository.countCommentsByBook(id));
        return pageResult;
    }

    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }

    public void addComment(Integer id, CommentImportDto commentImportDto) {
        if (commentImportDto == null) {
            throw new IllegalArgumentException("Comment cannot be null!");
        }
        Comment comment = modelMapper.map(commentImportDto, Comment.class);
        addCommentToBook(id, comment);
        commentRepository.save(comment);
    }

    private void addCommentToBook(Integer id, Comment comment) {
        Book bookById = bookService.getBookById(id);
        comment.setBook(bookById);
    }
}
