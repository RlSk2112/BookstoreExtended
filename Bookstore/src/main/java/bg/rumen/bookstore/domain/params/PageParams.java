package bg.rumen.bookstore.domain.params;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageParams {

    private static final Integer LIMIT = 5;

    @Positive
    private Integer page;

    public Integer getLimit() {
        return LIMIT;
    }
}
