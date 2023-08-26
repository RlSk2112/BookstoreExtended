package bg.rumen.bookstore.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    @Column(name = "comment")
    private String comment;

    @ManyToOne(targetEntity = Book.class)
    private Book book;
}
