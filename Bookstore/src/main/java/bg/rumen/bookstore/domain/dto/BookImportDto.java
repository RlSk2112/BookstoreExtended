package bg.rumen.bookstore.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookImportDto implements Serializable {

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    @NotBlank
    private String author;
}
