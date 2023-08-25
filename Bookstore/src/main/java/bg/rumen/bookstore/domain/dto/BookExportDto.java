package bg.rumen.bookstore.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookExportDto implements Serializable {

    private String title;

    private String author;
}
