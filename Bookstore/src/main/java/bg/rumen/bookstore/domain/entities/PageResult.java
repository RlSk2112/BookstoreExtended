package bg.rumen.bookstore.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private List<T> items;
    private Integer totalRecords;
}
