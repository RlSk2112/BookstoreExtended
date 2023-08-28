package bg.rumen.bookstore.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookImportDto {

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    @NotBlank
    private String author;
}
