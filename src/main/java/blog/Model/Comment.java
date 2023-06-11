package blog.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "comment")
@Data
public class Comment {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDate date;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = Blog.class)
    @JoinColumn(name = "blog_id")
    @JsonBackReference
    private Blog blog;
}
