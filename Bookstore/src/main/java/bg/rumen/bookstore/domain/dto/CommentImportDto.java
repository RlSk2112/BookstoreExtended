package bg.rumen.bookstore.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CommentImportDto implements Serializable {

    @NotNull
    @NotBlank
    private String comment;

    @NotNull
    @NotBlank
    private Integer bookId;
}
