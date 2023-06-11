package blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "blog")
    @JsonManagedReference
    List<Comment> comments;
    @ManyToMany(targetEntity = Tag.class)
    @JoinTable(
            name = "blog_tag",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private Set<Tag> tagBlog = new HashSet<>();
}