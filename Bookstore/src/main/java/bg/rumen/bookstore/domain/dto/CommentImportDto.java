package bg.rumen.bookstore.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentImportDto {

    @NotNull
    @NotBlank
    private String comment;

    @NotNull
    @NotBlank
    private Integer bookId;
}
