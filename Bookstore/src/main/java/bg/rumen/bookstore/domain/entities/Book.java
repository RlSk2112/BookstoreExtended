package bg.rumen.bookstore.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @OneToMany(targetEntity = Comment.class, mappedBy = "book")
    @Cascade(CascadeType.ALL)
    private List<Comment> comments;

    public Book(String title, String  author) {
        this.title = title;
        this.author = author;
        this.comments = new ArrayList<>();
    }
}
