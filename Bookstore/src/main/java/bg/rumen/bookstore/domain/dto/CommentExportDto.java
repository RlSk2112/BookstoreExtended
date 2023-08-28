package bg.rumen.bookstore.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentExportDto {

    private Integer commentId;

    private String comment;

    private Integer bookId;
}
