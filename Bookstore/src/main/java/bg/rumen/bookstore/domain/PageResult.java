package bg.rumen.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private List<T> items;
    private Integer totalRecords;
}