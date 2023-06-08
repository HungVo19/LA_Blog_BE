package blog.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "blog")
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String content;
    private String image;
    private LocalDate createdDate;
    @ManyToOne(targetEntity = User.class)
    private User user;
    private boolean deleteStatus;
}
