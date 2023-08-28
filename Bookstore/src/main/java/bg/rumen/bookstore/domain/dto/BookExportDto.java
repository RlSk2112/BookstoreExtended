package bg.rumen.bookstore.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookExportDto {

    private Integer id;

    private String title;

    private String author;
}
