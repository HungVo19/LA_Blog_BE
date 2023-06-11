package blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "tag")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "tagBlog")
    @JsonIgnore
    private Set<Blog> blogs = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {

    }
}
